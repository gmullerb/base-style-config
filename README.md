# Base coding style check configuration

[![license](https://img.shields.io/github/license/mashape/apistatus.svg)](/LICENSE.txt) [![download](https://api.bintray.com/packages/gmullerb/all.shared.quality/base-style-config/images/download.svg)](https://bintray.com/gmullerb/all.shared.quality/base-style-config/_latestVersion)

**This project provides a set of essential configuration files for code style checking.**

This project is licensed under the terms of the [MIT license](/LICENSE.txt).

## Goals

The idea is to have a common and single source of code styling rules, which can be used across different projects:

* Provides a similar set of rules for Backend's, Frontend's and Build's code.
* Provides a single source of configuration files.

## Features

* Provides a set of configuration files for **[Checkstyle](http://checkstyle.sourceforge.net)**, **[PMD](https://pmd.github.io)**, **[ESlint](https://eslint.org)**, **[StyleLint](https://github.com/stylelint/stylelint)** and **[CodeNarc](http://codenarc.sourceforge.net)**, bundle in a JAR:
  * A [Common Checkstyle's set](/config/common/common-checks.xml), for all files (.java, .js, .css, .gradle, etc.).
  * A [Checkstyle's set](/config/back/coding-checks.xml), for Backend's Java code.
  * A [PMD's set](/config/back/coding-rules.xml), for Backend's Java code.
  * A [ESLint's set](/config/front/.eslintrc.json), for Frontend's JS code.
  * A [StyleLint's set](/config/front/.stylelintrc.json), for Frontend's CSS code.
  * A [Codenarc's set](/config/gradle/gradle-rules.groovy) for Build's Gradle code and Groovy's code.
  * All sets define a similar set of rules in order to get the homogeneity in code style between Backend's, Frontend's and Build's code.
* Adds some new custom rules to Checkstyle and PMD.

## Code Style Checking

Code Style Checking tries to catch differences on coding style.

* The idea is to guaranteed the code looks similar beyond developers preference.
* It will allowed to force some best practices and standard of coding across team development.
* It is then recommend should be used before code is submitted to repository by developers or is approved.

There are different development tools for doing this.

This project use **[Checkstyle](http://checkstyle.sourceforge.net)**, **[PMD](https://pmd.github.io)**, **[ESlint](https://eslint.org)**, **[StyleLint](https://github.com/stylelint/stylelint)** and **[CodeNarc](http://codenarc.sourceforge.net)**.

## Defined Code Style Checking

The Defined Sets of rules are in:

* **Common Checkstyle** rules are in [`config/common/common-checks.xml`](config/common/common-checks.xml).

* **Checkstyle** checks are in [`config/back/coding-checks.xml`](config/back/coding-checks.xml).
  * Java Test files have some checks removed. ([`config/back/checks-suppressions.xml`](config/back/checks-suppressions.xml))
* **PMD** rules are in [`config/back/coding-rules.xml`](config/back/coding-rules.xml).
  * Java Test files have some rules ignore.
* **ESLint** rules are in [`config/front/.eslintrc.json`](config/front/.eslintrc.json).

* **Stylelint** rules are in [`config/front/.stylelintrc.json`](config/front/.stylelintrc.json).

* **Codenarc** rules are in [`config/gradle/gradle-rules.groovy`](config/gradle/gradle-rules.groovy).

### Code Style Checking Common rules

#### Common rules [common-checks.xml](config/common/common-checks.xml) [1]

* No tab characters on files.
* Requires a new line break at the end of the file[2].

> [1] Extensions: css, conf, gitignore, gradle, groovy, java, js, json, html, md, properties, sh, sql, ts, txt, xml, yml.  
> [2] In order to be friendly with Code Versioning tools.

#### Common Backend's, Frontend's and Build's code rules

To highlights:

* Naming convention:
  * CamelCase.
  * All in uppercase for constants.
    * Non-consecutive underscore (`_`) for Backend.
* Brackets style: Stroustrup.
* Indentation: "Tab" character = 2 spaces.
* Line length limit: 144 characters per line.
* Method length limit: 25 lines per method.
* Method parameters number limit: 5 parameters per method.
  * Except for Constructor where no limit is set (just to allow Immutables class with a lot of fields).
* Class/File lines limit: 300 lines per class/file.
  * This will allow having at most around of 11 big methods: 11 methods of max 25 lines.
  * this will allow to be functional, having small simple methods with single responsibility, and only a few "complex" methods of 25 lines.
    * Several small private methods and few public methods.
    * Java Code [1] will have a a limit of 12 public methods and 6 fields (for a class having only fields, this will be 6 getters and 6 setters).
* Class count limit: 1 Class per file.
* Cyclomatic Complexity limit: 8 paths.
* Some spacing rules are the same.

> Some of these rules are ignored for Test files in order to give some flexibility, but its encourage in Source files, may also help to reduce the complexity of Test files.  
> [1] CodeNarc and ESLint does not yet provide a way of doing this.

##### Semicolon

Semicolon is required by Java[1], but no by Groovy, then when having JS code, the use of the semicolon could be enforced based on the Backend language, in order to get some consistency between Backend and Frontend.

To enforce Java style, set the respective project's eslint configuration (`package.json` or `.eslintrc.*`) to [2]:

```json
  "rules": {
    "semi": ["error", "always"]
  }
```

To enforce Groovy style, set the respective project's eslint configuration (`package.json` or `.eslintrc.*`) to:

```json
  "rules": {
    "semi": ["error", "never"]
  }
```

> [1] Although new programming languages don't required semicolon, may be forcing JS to use of semicolon could be a waste of typing.  
> [2] or don't do any configuration, since, unfortunately,the default that comes from the `semi` rule is `always`.

### Code Style Checking Custom rules

#### Checkstyle custom rules

##### Customized rules

* **`AbbreviationAsWordInName`**: allows abbreviation only for `final` fields.
  * Then these `final` fields abbreviations are check by the Customized PMD's `FieldNamingConventions` rule.

* **`LineLength`**: Line length limit is ignored for lines with some patterns:
  * Starting with `package`.
  * Starting with `import`.
  * Containing Test methods: `public void should*()`.
  * Containing static Resource URI:
    * `file:///`.
    * `http://`.
    * `https://`.
    * `ftp://`.
    * `classpath:`.
    * `jar:`.
    * `zip:`.
  * Containing Spring Queries:
    * `find*By*`.
    * `read*By*`.
    * `query*By*`.
    * `get*By*`.
    * `count*By*`.

* **`CustomImportOrder`**: Import order is forced to have some structure[1]:
  * First, same package's non-static imports, to increase Readability (Allows to easily locate the classes of same package, usually classes created in the project, that current class depends on).
  * Followed by the rest of non-static imports.
  * Finally, static imports.

* **`BeanMembersShouldSerialize`**: ignores this rule when the class does not implement `java.io.Serializable` interface or does not have the `javax.persistence.Entity` Annotation (i.e. checks for `transient` only when the class implements `java.io.Serializable` interface or has the `javax.persistence.Entity` Annotation).

* **`VariableDeclarationUsageDistance`**: is set to allow a maximum distance of 6 effective statements between declaration and first used of the variable [2].

> [1] Although forcing this is sometime harmful since different team members can use different IDEs, but IDEs should not rule the development.  
> [2] CodeNarc and ESLint does not yet provide a way of doing this.

#### PMD custom rules

##### Customized rules

* **`FieldNamingConventions`**: will do custom validation for `final` fields:
  * `static final` fields may represent constants, then this rule check for:
    * All in uppercase [1], or
    * CamelCase [1].
  * non `static final` fields should use CamelCase.
    * Additionally, fields are checked with Customized Checkstyle's `AbbreviationAsWordInName` rule.
* **`ClassNamingConventions`**: Utility classes should end on `Factory`, `Util`, `Utils` or `Helper` [2].
* **`TooManyFields`**: is set to allow a maximum of 6 fields per class [3].

> [1] All in uppercase should be used for "real" constants, fields that are Immutable, and CamelCase for Non Immutable values.  
> [2] Although utility classes should be avoided.  
> [3] CodeNarc and ESLint does not yet provide a way of doing this.

##### New rules

* **`AvoidFieldInjection`**: Checks that no Field Injection is used in the code with either `javax.inject.Inject`, `com.google.inject.Inject` or `org.springframework.beans.factory.annotation.Autowired`.

#### Codenarc custom rules

##### Customized rules

* **`CouldBeElvis`**: Has priority = `0` [1], is very useful but cause some false-positive, e.g. `if (!object.field) object.field = value` value is required as `object.field = object.field ?: value`.
* **`FieldName`**:
  * `static final` fields may represent constants, then this rule check for:
    * All in uppercase [2], or
    * CamelCase [2].
  * no `static final` must Camel Case always ending in a lower case.
* **`DuplicateNumberLiteral`** and **`DuplicateStringLiteral`**: Have priority = `0` [1], these rules don't allow to configure allowed duplications' limit, which make them hard to use.
* **`GStringExpressionWithinString`**: Has priority = `0` [1], these rules don't allow to build string that has `${..}` inside a `String`, which is sometimes required, e.g.: `'--file ${npm_config_configFile}'` is not allowed.
* **`SpaceAroundMapEntryColon`**: Requires a space after the colon in a map entry.

> [1] Violations are only reported, but should be checked since some case it has good reason to be a violation, but priority was reduce to avoid false-positive.  
> [2] All in uppercase should be used for "real" constants, fields that are Immutable, and CamelCase for Non Immutable values.

##### New rules

* **`AnnotateClassesWith@CompileStaticOr@TypeChecked`**: Check if principal class, interface or trait in the file is annotated with `@CompileStatic` or `@TypeChecked`.
  * [@TypeChecked](http://docs.groovy-lang.org/latest/html/gapi/groovy/transform/TypeChecked.html) will do check of type during compilation, this will lead to a more Reliable program.
  * [@CompileStatic](http://docs.groovy-lang.org/latest/html/gapi/groovy/transform/CompileStatic.html) will do check of type during compilation and perform static compilation, this will increase performance of the resulting program.

* **`SpaceBeforeOperator`**, **`SpaceAfterOperator`**, **`Exactly1SpaceBeforeOperator`** & **`Exactly1SpaceAfterOperator`**: Check spaces around operators.
  * Substitutes `SpaceAroundOperator` rule, it is unstable for some patterns (produces some false-positive).
  * These rules may not check some cases, but should not give false-positives, e.g. `>>` is not validated.

##### Gradle & Groovy

Disabled rules for Gradle, still enabled for Groovy:

* `AnnotateClassesWith@CompileStaticOr@TypeChecked`.
* `Indentation`.
* `MethodParameterTypeRequired`.
* `NoDef`.
* `UseOnlyMockOrSpyPrefixOnTestFiles`.
* `VariableTypeRequired`.

#### Common New rules

Checkstyle/CodeNarc Rules:

* **`CallOnlyOneMethodPerLineForChainedCall`**: Checks that only one method is called per line in order to increase Readability:

  ```java
    new SomeObject(..)
      .method2(..)
      .method3(..);
   ```

  or

  ```java
    method1(..)
      .method2(..)
      .method3(..);
   ```

  or

  ```java
    object.method1(..)
      .method2(..)
      .method3(..);
   ```

* **`NameOfTestsMustStartWithShould`**: Checks that the test methods names begin with `should`, e.g. `shouldExtractSomeValue`.
* **`UseMultilineTernaryOperator`**: Ternary operator is forced to be in multiple lines in order to increase Readability [1]:

  ```java
    condition
      ? expression
      : expression
   ```

* **`UseOnlyDoFamilyMethodsWhenMocking`**: Checks that only `do*`'s family methods are use when mocking: `doAnswer`, `doCallRealMethod`, `doNothing`, `doReturn` & `doThrow`:
  * `Mockito.when` have some ["issues"](http://javadoc.io/page/org.mockito/mockito-core/latest/org/mockito/Mockito.html#spy) basically when spying.
    * `doAnswer`, `doCallRealMethod`, `doNothing`, `doReturn` & `doThrow` do not present those issues.
  * `BDDMockito.given` is an alias for `Mockito.when`.
* **`UseOnlyMockOrSpyPrefixOnTestFiles`**: Checks that the '`mock`' or '`spy`' prefixes are used only on test's files' code.

> [1] Some cases are not detected by CodeNarc.

#### Suppressions

For test files, some rules/checks[1] are disabled in order to allow some freedom:

* No restriction on the number of methods or size.
* Duplication of literals is allowed, to increase test Maintainability.
* Throwing `Exception` is allowed.
* Field Injection is allowed.
* `UseOnlyMockOrSpyPrefixOnTestFiles`.
* Javadoc, obviously, is not required or checked [2].

> [1] For Checkstyle, PMD and CodeNarc.
> [2] Same as in JS.

#### Conventions

* Unit/Integration test files name should be suffix with "**`Test`**" in order to some rules/checks to be suppressed.

## Using/Configuration

Following is a detailed description of how to use configuration files from a JAR. Take in account that is not necessary to use all the configurations, e.g. If the project do not require Frontend's validations, then don't use the frontend configuration files.

### Prerequisites for using it

#### Common

* [Checkstyle plugin](https://docs.gradle.org/current/dsl/org.gradle.api.plugins.quality.Checkstyle.html)

```gradle
 plugins {
   id 'checkstyle'
 }
```

#### Backend

* [Checkstyle plugin](https://docs.gradle.org/current/dsl/org.gradle.api.plugins.quality.Checkstyle.html)

```gradle
 plugins {
   id 'checkstyle'
 }
```

* [PMD plugin](https://docs.gradle.org/current/dsl/org.gradle.api.plugins.quality.Pmd.html)

```gradle
 plugins {
   id 'pmd'
 }
```

#### Frontend

* [Node plugin](https://github.com/srs/gradle-node-plugin)

```gradle
 plugins {
   id 'com.moowork.node' version '1.2.0'
 }
```

* [ESlint package](https://www.npmjs.com/package/eslint)

```json
  "devDependencies": {
    "eslint": "*",
  },
```

* [StyleLint package](https://www.npmjs.com/package/stylelint)

```json
  "devDependencies": {
    "stylelint": "*",
    "stylelint-config-standard": "*"
  },
```

#### Gradle

* [Codenarc plugin](https://docs.gradle.org/current/dsl/org.gradle.api.plugins.quality.CodeNarc.html)

```gradle
 plugins {
   id 'codenarc'
 }
```

### Dependency configuration

1. Add the respective Bintray repository:

```gradle
  repositories { maven { url "https://dl.bintray.com/gmullerb/all.shared.quality" } }

```

2. Define a configuration and the associated dependency:

```gradle
  configurations {
    styleConfig
  }
  dependencies {
    styleConfig 'all.shared.quality:base-style-config:+'
  }
```

### Common checkstyle configuration

1. Configure plugin:

```gradle
  checkstyle {
    config = resources.text.fromArchiveEntry(configurations.styleConfig, 'common/common-checks.xml')
  }
```

2. Define a Checkstyle task to check "all" files in the project tree, e.g.: [basecode project - build.gradle file - assessCommon task](https://github.com/gmullerb/basecode/blob/master/build.gradle).

### Backend configuration

#### Java

```gradle
  checkstyle {
    config = resources.text.fromArchiveEntry(configurations.styleConfig, 'back/coding-checks.xml')
    configProperties.suppressionFile = resources.text.fromArchiveEntry(configurations.styleConfig, 'back/checks-suppressions.xml').asFile().path
  }

  pmd {
    ruleSets = [] // required with new PMD version
    ruleSetConfig = resources.text.fromUri(configurations.styleConfig, 'back/coding-rules.xml')
  }
```

> If using both, PMD should be run after Checkstyle, since Checkstyle is "lighter".  
> A complete example in [basecode project - back project](https://github.com/gmullerb/basecode/tree/master/back).

#### Groovy

```gradle
  codenarc {
    config = resources.text.fromArchiveEntry(configurations.styleConfig, 'gradle/gradle-rules.groovy')
  }
```

### Frontend configuration

1. Add a config parameter to the respective ESLint script task, e.g.:
- eslint script task: `someEslintTask`.
- config parameter: `eslintConfigFile`

```json
  "scripts": {
    "someESlintTask": "eslint --config ${npm_config_eslintConfigFile} ..",
  },
```

2. Set the config parameter in the respective gradle NpmTask task:

```gradle
  task assessSomeESLint(type: NpmTask) {
    // NpmTask task settings
    args = ['someESlintTask', "--eslintConfigFile=${resources.text.fromArchiveEntry(configurations.styleConfig, 'front/.eslintrc.json').asFile().path}"]
  }
```


 q3. Same for Stylelint:

`package.json`:

```json
  "scripts": {
    "someStylelintTask": "stylelint --config ${npm_config_stylelintConfigFile} ..",
  },
```

`build.gradle`:

```gradle
  task assessSomeStylelint(type: NpmTask) {
    // NpmTask task settings
    args = ['someStylelintTask'] + "--stylelintConfigFile=${resources.text.fromArchiveEntry(configurations.styleConfig, 'front/.stylelintrc.json').asFile().path}"]
  }
```

> A complete example in [basecode project - front project](https://github.com/gmullerb/basecode/tree/master/front).

### Gradle configuration

1. Configure plugin:

```gradle
  codenarc {
    config = resources.text.fromArchiveEntry(configurations.styleConfig, 'gradle/gradle-rules.groovy')
  }
```

2. Define a Codenarc task to check all gradle files in the project tree, e.g.: [basecode project - build.gradle file - assessGradle task](https://github.com/gmullerb/basecode/blob/master/build.gradle).

## Extending/Developing

### Prerequisites for building

* [Java](https://www.java.com/en/download/help/download_options.xml) (for running Gradle), which basically comes with every operating system, so this should not be a problem.
* [Git](https://git-scm.com/downloads) (only if the project is going to be cloned).

### Getting it

Clone or download the project[1], in the desired folder execute:

```sh
git clone https://gitlab.com/gmullerb/base-style-config
```

or

```sh
git clone https://github.com/gmullerb/base-style-config
```

> [1] [Cloning a repository](https://help.github.com/articles/cloning-a-repository/)

### Set up

+ **No need**, only download and run (It's Gradle! Yes!).

### Building

* To check it:
  * `gradlew assess`: will check the style of common code.

* To build it:
  * `gradlew`: this will run default task, or
  * `gradlew build`.
    * `build` task depends on `assess` task.

* To get all the tasks for the project: `gradlew tasks --all`

## Folders structure

```
  /config
    /common
    /back
    /front
    /gradle
```

- `config/common`: Global Common Checkstyle configuration files.
- `config/back`: Checkstyle and PMD configuration files.
- `config/front`: EsLint and StyleLint configuration files.
- `config/gradle`: CodeNarc configuration files.

## Documentation

* [`CHANGELOG.md`](CHANGELOG.md): add information of notable changes for each version here, chronologically ordered [1].

> [1] [Keep a Changelog](http://keepachangelog.com)

## License

[MIT License](/LICENSE.txt)

## Additional words

Don't forget:

* **Love what you do**.
* **Learn everyday**.
* **Learn yourself**.
* **Share your knowledge**.
* **Learn from the past, dream on the future, live and enjoy the present to the max!**.

At life:

* Let's act, not complain.
* Be flexible.

At work:

* Let's give solutions, not questions.
