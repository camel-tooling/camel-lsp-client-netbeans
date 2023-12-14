/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License", destination); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.cameltooling.netbeans.client.preferences;

import com.github.cameltooling.netbeans.client.Utilities;
import com.github.cameltooling.netbeans.client.ApacheCamelPanel;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.util.Collections;
import java.util.Map;
import java.util.HashMap;
import org.openide.util.NbPreferences;
import java.util.List;
import java.util.Set;
import org.eclipse.lsp4j.DidChangeConfigurationParams;
import org.netbeans.modules.lsp.client.LSPBindings;

/**
 * Process preferences given by user in UI to format accepted by Camel language
 * server.
 *
 * @author fpospisi
 */
public class ProcessPreferences {

    // Variables for creating JSON with preferences:
    private static final String TOP_NODE_CAMEL_KEY = "camel";
    static final String CAMEL_CATALOG_VERSION_PREF_KEY = "Camel catalog version";
    static final String CAMEL_CATALOG_RUNTIME_PROVIDER_PREF_KEY = "Camel catalog runtime provider";
    static final String CAMEL_ADDITIONAL_COMPONENT_PREF_KEY = "extra-components";

    /**
     * Gets user preferences and returns them as JSON format accepted by Camel
     * Langauge Server.
     *
     * @return User preferences in format accepted by Camel Language Server.
     */
    public static Map<String, Map<String, Object>> getPreferenceAsLanguageServerFormat() {
        Map<String, Map<String, Object>> settings = new HashMap<>();
        Map<String, Object> camelSettings = new HashMap<>();
        camelSettings.put(CAMEL_CATALOG_VERSION_PREF_KEY, NbPreferences.forModule(ApacheCamelPanel.class).get(Utilities.CAMEL_CATALOG_VERSION_PREF, Utilities.CATALOG_VERSION_DEFAULT)); // catalog version
        camelSettings.put(CAMEL_CATALOG_RUNTIME_PROVIDER_PREF_KEY, getProviderAsString()); // runtime provider 
        camelSettings.put(CAMEL_ADDITIONAL_COMPONENT_PREF_KEY, getAdditionalComponentIfValid()); // additional components
        settings.put(TOP_NODE_CAMEL_KEY, camelSettings);
        return settings;
    }

    /**
     * Converts Catalog provider from preferences to String.
     *
     * @return Catalog provider as String.
     */
    private static String getProviderAsString() {
        return switch (NbPreferences.forModule(ApacheCamelPanel.class).getInt(Utilities.CAMEL_CATALOG_RUNTIME_PROVIDER_PREF, Utilities.CATALOG_PROVIDER_DEFAULT)) {
            case 1 ->
                "SPRINGBOOT";
            case 2 ->
                "QUARKUS";
            case 3 ->
                "KARAF";
            default ->
                "DEFAULT";
        };
    }

    /**
     * Gets Additional Component from user preferences and checks if they're
     * valid. If so, they're added to list.
     *
     * @return List of components.
     */
    private static List<?> getAdditionalComponentIfValid() {
        String additionalComponentAsString = NbPreferences.forModule(ApacheCamelPanel.class).get(Utilities.CAMEL_ADDITIONAL_COMPONENT_PREF, Utilities.ADDITIONAL_COMPONENTS_DEFAULT);
        if (additionalComponentAsString != null && !additionalComponentAsString.isEmpty()) {
            try {
                return new Gson().fromJson(additionalComponentAsString, List.class);
            } catch (JsonSyntaxException ex) {
                return Collections.emptyList();
            }
        }
        return Collections.emptyList();
    }

    /**
     * Send "sendDidChagneConfiguration" notification to Camel language server.
     */
    public void sendDidChangeConfiguration() {
        Map<String, Map<String, Object>> configuration = ProcessPreferences.getPreferenceAsLanguageServerFormat();
        DidChangeConfigurationParams params = new DidChangeConfigurationParams(configuration);
        Set<LSPBindings> listBindings = LSPBindings.getAllBindings();
        for (LSPBindings item : listBindings) {
            item.getWorkspaceService().didChangeConfiguration(params);
        }
        System.out.println(params.toString());
    }
}
