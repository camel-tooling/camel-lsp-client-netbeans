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
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;
import org.netbeans.api.editor.mimelookup.MimeRegistration;
import org.netbeans.api.editor.mimelookup.MimeRegistrations;
import org.netbeans.spi.editor.completion.CompletionProvider;
import org.netbeans.spi.editor.completion.CompletionResultSet;
import org.netbeans.spi.editor.completion.CompletionTask;
import org.netbeans.spi.editor.completion.support.AsyncCompletionQuery;
import org.netbeans.spi.editor.completion.support.AsyncCompletionTask;

/**
 *
 * @author fpospisi
 */
@MimeRegistrations({
    @MimeRegistration(service = CompletionProvider.class,
            mimeType = Utilities.XML_MIME),
    @MimeRegistration(service = CompletionProvider.class,
            mimeType = Utilities.YAML_MIME),
    @MimeRegistration(service = CompletionProvider.class,
            mimeType = Utilities.JAVA_MIME)
})
public class ApplyPreferencesOnDemand implements CompletionProvider {

    @Override
    public CompletionTask createTask(int queryType, JTextComponent jtc) {
        if (queryType != CompletionProvider.COMPLETION_QUERY_TYPE) {
            return null;
        }

        return new AsyncCompletionTask(new AsyncCompletionQuery() {
            @Override
            protected void query(CompletionResultSet crs, Document dcmnt, int i) {
                new ProcessPreferences().sendDidChangeConfiguration();
                crs.finish();
            }
        });
    }

    @Override
    public int getAutoQueryTypes(JTextComponent jtc, String string) {
        return CompletionProvider.COMPLETION_QUERY_TYPE;
    }
}
