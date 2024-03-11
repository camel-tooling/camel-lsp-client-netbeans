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
package com.github.cameltooling.netbeans.client;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

/**
 *
 * @author fpospisi
 */
public class Utilities {

    public static final String LANGUAGE_SERVER_JAR = "camel-lsp-server-1.18.0.jar";

    // MIMEs.
    public static final String XML_MIME = "text/xml";
    public static final String JAVA_MIME = "text/x-java";
    public static final String YAML_MIME = "text/x-yaml";
    
    // Default values for settings.
    public static final String CATALOG_VERSION_DEFAULT = null; // Default version
    public static final Integer CATALOG_PROVIDER_DEFAULT = 0; // Default
    public static final String ADDITIONAL_COMPONENTS_DEFAULT = null; // Empty
    
    // Constants used in settings storage.
    public static final String CAMEL_CATALOG_VERSION_PREF = "CatalogVersionPreference";
    public static final String CAMEL_CATALOG_RUNTIME_PROVIDER_PREF = "RuntimeProviderPreference";
    public static final String CAMEL_ADDITIONAL_COMPONENT_PREF = "AdditionalComponentPreference";

    public File getLanguageServerJAR() throws IOException {

        String resourcePath = "/" + LANGUAGE_SERVER_JAR;

        try (InputStream is = getClass().getResourceAsStream(resourcePath)) {
            File languageServerJAR = File.createTempFile(LANGUAGE_SERVER_JAR.substring(0, LANGUAGE_SERVER_JAR.length() - 4) + "-", ".jar"); // this will result into instance with name eg. "camel-lsp-server-1.13.0-123456789.jar" 
            Files.copy(is, languageServerJAR.toPath(), StandardCopyOption.REPLACE_EXISTING);
            return languageServerJAR;
        }
    }
}
