# How to start development

First Time Setup
--------------
1. Fork and clone the repository.

2. Install [Apache NetBeans IDE](https://netbeans.apache.org/front/main/download/).

3. Once installed use `File > Open Projects...` and select `client` from  `camel-lsp-client-netbeans` folder and NetBeans should automatically
detect the projects and import it properly.

Building Maven package from command line
----------------------------

1. Install [Apache Maven](https://maven.apache.org/).

2. This command will build the npm package:
```bash    
    $ mvn clean verify
````
