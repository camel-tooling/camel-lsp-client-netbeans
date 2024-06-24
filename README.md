<h1 align="center">
  <img width="125" height="125" src="./docs/images/logo.png">
  <br>Language Support for Apache Camel</br>
</h1>

<p align="center">
  <a href="./CHANGELOG.md"><img src="./docs/images/badges/version.svg" alt="Version"/></a>
  <a href="./LICENSE"><img src="./docs/images/badges/licence.svg" alt="License"/></a>
  <a href="https://plugins.netbeans.apache.org/"><img src="./docs/images/badges/download.svg" alt="Download"/></a>  
</p>

<p align="center">
  <a href="#features">Features</a> •
  <a href="#requirements">Requirements</a> •
  <a href="#how-to-use">How To Use</a> •
  <a href="#issues">Issues</a>
</p>

<p align="center">
This is the <a href="https://netbeans.apache.org/">Apache NetBeans</a> extension that adds language support for <a href="http://camel.apache.org/">Apache Camel</a> for XML, Java and YAML DSL code.
</p><br/>

<p align="center"><img src="./docs/images/completion.gif" alt="Completion for XML DSL" width="100%"/></p>

### Features
- Language service support for Apache Camel URIs.
- Diagnostics for Camel URIs for Java nad XML.
- Camel K modelines support.
- Use a specific Camel Catalog version.
- Use a specific Runtime provider for the Camel catalog.
- Additional Camel components can be provided.

### Requirements

**Apache NetBeans 22** is minimal required version of [Apache NetBeans IDE](https://netbeans.apache.org/).

**Java 17+** is currently required to launch the [Apache Camel Language Server](https://github.com/camel-tooling/camel-language-server).

### How to use
#### Language service support for Apache Camel URIs
This feature is supported in XML DSL, Java DSL and YAML DSL. Auto-completion for Camel components, attributes, and the list of attribute values

**XML DSL**
<p><img src="./docs/images/xml.gif" alt="Language service support for XML DSL" width="100%"/></p>

**Java DSL**
<p><img src="./docs/images/java.gif" alt="Language service support for Java DSL" width="100%"/></p>

**YAML DSL**
<p><img src="./docs/images/yaml.gif" alt="Language service support for YAML DSL" width="100%"/></p>


#### Diagnostics for Camel URIs for Java and XML
When invalid value is entered, diagnostic shows error. Not available for YAML DSLs.
<p><img src="./docs/images/diagnostic_xml.gif" alt="URI diagnostic for XML" width="100%"/></p>
<p><img src="./docs/images/diagnostic_java.gif" alt="URI diagnostic for Java" width="100%"/></p>

#### Camel K modelines support
- completion for:
    - option names
    - trait definition names
    - trait property names
    - Camel artifact id for dependency
    - mvn dependency
    - Camel properties
<p><img src="./docs/images/modelines.gif" alt="Camel K modelines support" width="100%"/></p>

#### Use a specific Camel Catalog version
It is possible to use a specific Camel Catalog version. This can be specified in `NetBeans > Settings > Apache Camel > Catalog version`.

Please note that the first time a version is used, it can take several seconds/minutes to have it available depending on the time to download the dependencies in the background.

#### Use a specific Runtime provider for the Camel catalog
It is possible to use a specific Camel Catalog version. This can be specified in `NetBeans > Settings > Apache Camel > Runtime provider`.

#### Additional Camel components can be provided
Additional Camel components can be provided. This can be specified in `NetBeans > Settings > Apache Camel > Additional components`.
<p><img src="./docs/images/additional_components.gif" alt="Additional Camel components support" width="100%"/></p>

You can add a list of Camel components definition. This json definition can be found in jar of the Camel component. You will end up with something like:
```json
[{
      "component": {
        ...
      },
      "componentProperties": {
        ...
      },
      "properties": {
        ...
      }
   }]
```
**Note**: While new file is opened, all custom settings may take few seconds to apply or even require reopening content assistant.

### Issues
Something is not working properly? In that case, feel free to [open issues, add feature requests, report bugs, etc.](https://github.com/camel-tooling/camel-lsp-client-netbeans/issues)
