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
import com.github.cameltooling.netbeans.client.CamelLSPClientActivator;
import com.github.cameltooling.netbeans.client.Utilities;
import java.io.File;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;
import org.mockito.Mockito;
import org.netbeans.modules.lsp.client.spi.LanguageServerProvider.LanguageServerDescription;
import org.openide.util.Lookup;

/**
 *
 * @author fpospisi
 */
public class ClientTest {
    
    @Test
    public void testFindJar() throws IOException {
        Utilities utils = new Utilities();
        File path = utils.getLanguageServerJAR();
        assertTrue(path.exists());
    }
        
    @Test
    public void ServerStart() throws IOException{
        Utilities mockUtilities = Mockito.mock(Utilities.class);
        Mockito.when(mockUtilities.getLanguageServerJAR()).thenThrow(new IOException());
        CamelLSPClientActivator activator = new CamelLSPClientActivator();
        LanguageServerDescription result = activator.startServer(Mockito.mock(Lookup.class));
        assertNotNull(result);
    }   
}
