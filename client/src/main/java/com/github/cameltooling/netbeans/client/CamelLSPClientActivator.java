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

import java.io.IOException;
import org.netbeans.api.editor.mimelookup.MimeRegistration;
import org.netbeans.api.editor.mimelookup.MimeRegistrations;
import org.netbeans.modules.lsp.client.spi.LanguageServerProvider;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;

/**
 *
 * @author fpospisi
 */
@MimeRegistrations({
    @MimeRegistration(service = LanguageServerProvider.class,
            mimeType = Utilities.XML_MIME),
    @MimeRegistration(service = LanguageServerProvider.class,
            mimeType = Utilities.YAML_MIME),
    @MimeRegistration(service = LanguageServerProvider.class,
            mimeType = Utilities.JAVA_MIME)
})
public class CamelLSPClientActivator implements LanguageServerProvider {

    @Override
    public LanguageServerDescription startServer(Lookup lkp) {
        try {
            var jarFile = new Utilities().getLanguageServerJAR();
            Process p = new ProcessBuilder("java", "-jar", jarFile.getAbsolutePath()).start();
            return LanguageServerProvider.LanguageServerDescription.create(p.getInputStream(), p.getOutputStream(), p);
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
            return null;
        }
    }
}
