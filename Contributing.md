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
# How to release
- Update [pom](client/pom.xml) to not use snapshot version.
- Provide Pull Request (PR).
- Merge PR if OK.
- Check that a [GitHub Actions build](https://https://github.com/pospisilf/camel-lsp-client-netbeans/actions) is triggered and successful.
- Create corresponding tag.
- Check that the artifact is available on https://repo1.maven.org/maven2/com/github/camel-tooling/netbeans/camel-lsp-client-netbeans/. It usually takes up to 30 minutes.
- Update [pom](client/pom.xml) to use next snapshot version.
- Provide PR.
- Merge PR if OK.