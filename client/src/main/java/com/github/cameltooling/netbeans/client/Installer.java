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

import com.github.cameltooling.netbeans.client.preferences.ApplyPreferencesOnFileOpen;
import javax.swing.SwingUtilities;
import org.openide.modules.ModuleInstall;
import org.openide.windows.TopComponent;

public class Installer extends ModuleInstall {

    private ApplyPreferencesOnFileOpen fileOpenedListener;

    @Override
    public void restored() {
        SwingUtilities.invokeLater(() -> {
            fileOpenedListener = new ApplyPreferencesOnFileOpen();
            TopComponent.getRegistry().addPropertyChangeListener(fileOpenedListener);
        });
    }

    @Override
    public void close() {
        TopComponent.getRegistry().removePropertyChangeListener(fileOpenedListener);
    }
}
