# venus-sample-i18n

This is a sample project configured using [fj-doc-maven-plugin init plugin](https://venusdocs.fugerit.org/guide/#maven-plugin-goal-init).

[![Keep a Changelog v1.1.0 badge](https://img.shields.io/badge/changelog-Keep%20a%20Changelog%20v1.1.0-%23E05735)](CHANGELOG.md)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=fugerit79_venus-sample-i18n&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=fugerit79_venus-sample-i18n)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=fugerit79_venus-sample-i18n&metric=coverage)](https://sonarcloud.io/summary/new_code?id=fugerit79_venus-sample-i18n)
[![License: MIT](https://img.shields.io/badge/License-MIT-teal.svg)](https://opensource.org/licenses/MIT)
[![code of conduct](https://img.shields.io/badge/conduct-Contributor%20Covenant-purple.svg)](https://github.com/fugerit-org/fj-universe/blob/main/CODE_OF_CONDUCT.md)

This project is part of a series of mini tutorial on [Venus Fugerit Doc](https://github.com/fugerit-org/fj-doc),
here you can find the [other tutorials](https://github.com/fugerit79/venus-sample-index).

## Requirement

* JDK 8+ (*)
* Maven 3.8+

(*) Currently FOP not working on [JDK 25, See bug JDK-8368356](https://bugs.openjdk.org/browse/JDK-8368356).

## Project initialization

This project was created with [Venus Maven plugin](https://venusdocs.fugerit.org/guide/#maven-plugin-goal-init)

```shell
mvn org.fugerit.java:fj-doc-maven-plugin:8.16.9:init \
-DgroupId=org.fugerit.java.demo \
-DartifactId=venus-sample-i18n \
-Dextensions=base,freemarker,mod-fop \
-DaddJacoco=true \
-DprojectVersion=1.0.0
```

## i18n document

Let's create a [labels](src/main/resources/venus-sample-i18n/i18n/labels.properties) bundle for various languages, this is the default one : 

```properties
# default i18n labels

document.title = Sample i18n document

document.language.code = en

document.generation.info = Document generated on {0}, java {1} with Venus Fugerit Doc (https://github.com/fugerit-org/fj-doc).

table.people.header.name = Name
table.people.header.surname = Surname
table.people.header.title = Title
```

Create the label bundle and add it to data model for our 
[DocHelper](src/main/java/org/fugerit/java/demo/venussamplei18n/DocHelper.java),

```java
public void generateDocument( String handlerId, Locale language, OutputStream os) {
        // ...
        ResourceBundle labels = ResourceBundle.getBundle( PATH_BUNDLE_LABELS, language );
        // ...
        docHelper.getDocProcessConfig().fullProcess( chainId,
                DocProcessContext.newContext( "listPeople", listPeople )
                        .withAtt( "osName", System.getProperty("os.name") )
                        .withAtt( "javaVersion", System.getProperty("java.version") )
                        .withAtt( "labels", labels ), // adding labels
                handlerId, os );
        // ...
    } );
}
```

User it in the [document.ftl](src/main/resources/venus-sample-i18n/template/document.ftl) template : 

```ftl
    <h head-level="1">${labels['document.title']}</h>
    <para>${messageFormat(labels['document.generation.info'], osName, javaVersion)}</para>
    <table columns="3" colwidths="30;30;40"  width="100" id="data-table" padding="2">
        <row header="true">
            <cell align="center"><para>${labels['table.people.header.name']}</para></cell>
            <cell align="center"><para>${labels['table.people.header.surname']}</para></cell>
            <cell align="center"><para>${labels['table.people.header.title']}</para></cell>
        </row>
```

This will be the output in english : 

```md
# **Sample i18n document**

Document generated on Mac OS X, java 21.0.2 with Venus Fugerit Doc (https://github.com/fugerit-org/fj-doc).


| Name | Surname | Title  |
|---------------|---------------|---------------|
| Luthien | Tinuviel | Queen  |
| Thorin | Oakshield | King  |
```

And italian : 

```md
# **Documento di esempio per l'i18n**

Documento generato su Mac OS X, java 21.0.2 con Venus Fugerit Doc (https://github.com/fugerit-org/fj-doc).


| Nome | Cognome | Titolo  |
|---------------|---------------|---------------|
| Luthien | Tinuviel | Queen  |
| Thorin | Oakshield | King  |
```