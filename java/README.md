<p align="center">
  <img src="https://assets.gitlab-static.net/uploads/-/system/project/avatar/10888432/base-style-config.png"/>
</p>

<h1 align="center">A Set of Essential ESLint rules for Java, Groovy and Gradle</h1>

<p align="center">
It's part of a Set of Essential Configuration Files for Backend/Frontend/Build code style checking: <a href="https://github.com/gmullerb/base-style-config">base-style-config</a>
</p>

__________________

[![license](https://img.shields.io/github/license/mashape/apistatus.svg)](/LICENSE.txt) [![download](https://api.bintray.com/packages/gmullerb/all.shared.quality/base-style-config/images/download.svg)](https://bintray.com/gmullerb/all.shared.quality/base-style-config/_latestVersion)


This project is licensed under the terms of the [MIT license](/LICENSE.txt).
__________________

## Quick Start

1 . Use the [base-style-config-wrapper Gradle plugin](https://github.com/gmullerb/base-style-config-wrapper).

or

1 . Download the Jar in [Direct Downloads](https://bintray.com/gmullerb/all.shared.quality/base-style-config/_latestVersion).

2 . And point every tool to the respective configuration file [Using/Configuration](#Using/Configuration).
__________________

## Goals

The idea is to have a common and "single" source of code styling rules, which can be used across different projects:

* Provides a **similar set of rules** for Backend's, Frontend's and Build's code.
* Provides a "single" source of configuration files.

## Code Style Checking

Code Style Checking tries to catch differences on coding style.

* The idea is to guaranteed the code looks similar beyond developers preference.
* It will allowed to force some best practices and standard of coding across team development.
* It is then recommend should be used before code is submitted to repository by developers or is approved.

There are different development tools for doing this.

This project use **[Checkstyle](http://checkstyle.sourceforge.net)**, **[PMD](https://pmd.github.io)**, **[ESlint](https://eslint.org)** and **[CodeNarc](http://codenarc.sourceforge.net)**.

> Checkstyle is easy to use and more "light", and PMD is easy for adding new "powerful" rules.

## Defined Code Style Checking

The Defined Sets of rules are in:

* **Common Checkstyle** rules are in [`src/config/common/common-checks.xml`](src/config/common/common-checks.xml).

* **Checkstyle** checks are in [`src/config/java/java-checks.xml`](src/config/java/java-checks.xml).
  * Java Test files have some checks removed. ([`src/config/java/checks-suppressions.xml`](src/config/java/checks-suppressions.xml))
* **PMD** rules are in [`src/config/java/java-rules.xml`](src/config/java/java-rules.xml).
  * Java Test files have some rules ignore.

* **Codenarc** rules are in [`src/config/groovy/groovy-rules.groovy`](src/config/groovy/groovy-rules.groovy).

### Code Style Checking Common rules

#### Common rules [common-checks.xml](src/config/common/common-checks.xml)

Will check all the files with extension: **c, cpp, css, conf, flowconfig, gitignore, gradle, groovy, h, hpp, java, js, jsx, json, html, md, mjs, pri, pro, properties, qml, sh, sql, ts, tsx, txt, xml, yml**[1].

* No tab characters on files.
* Requires a new line break at the end of the file[2].

> [1] Can be extended at [common-checks.xml](src/config/common/common-checks.xml).
> [2] In order to be friendly with Code Versioning tools.

#### Common Backend's, Frontend's and Build's code rules

To highlights:

* Naming convention:
  * CamelCase.
  * All in uppercase for constants.
    * Non-consecutive underscore (`_`) for Backend.
* Fields/Variables/Parameters/Methods names must have a length between 3 and 23 [1].
  * With some exceptions for Fields/Names/Parameters: `id`, `k` & `K` (useful for loops), some prepositions (`at`, `by`, `in`, `of`, `on`, `to` & `up`).
  * For Java/Groovy methods allows `of`, specially useful for factories.
  * Java/Groovy Test Code [3]:
    * Test instance methods names should start with `should`.
    * Other instance methods valid names are: `afterAll`, `afterEach`, `beforeAll` or `beforeEach`. [5]
      * Groovy: other instance methods must have a length between 3 and 23.
      * Java: static methods must have a length between 3 and 23.
* Classes/Constants names must have a length between 3 and 32 [1].
  * Test Class names will not have length limit.
* Type parameters names must be in uppercase, have a length between 1 and 10 or following the pattern `T_` up to 32 characters.
* Brackets style: Stroustrup.
* Indentation: "Tab" character = 2 spaces.
* Line length limit: 144 characters per line.
  * Line with a long string are ignored.
* Method length limit: 30 lines per method [2].
  * Java/Groovy Code [3] will have a limit of 25 [4] public/protected/private methods (being a reasonable limit for functional implementations, which tend to have several small functions/methods).[5]
* Method parameters number limit: 7 parameters per method [6].
* Java Code [7] will have a limit of 7 fields by class.
* Class/File lines limit: 300 lines per class/file.
* Class count limit: 1 Class per file.
* Public fields must be `final`.
* Imports should be order alphabetically.
* Cyclomatic Complexity limit: 8 paths.
* Some spacing rules are the same.
* When wrapping operators, the operator must be at the beginning of a new line.

Combination of Method length limit and Class/File lines limit allows to:

  * Have at most around of 10 big methods: ~10 methods of max ~30 lines or ~25 methods of max ~12 lines.
  * Be functional, having small simple methods with single responsibility, and only a few "complex" methods of max 30 lines.
    * Several small private methods and few public methods.

> Some of these rules are ignored for Test files in order to give some flexibility, but its encourage in Source files, may also help to reduce the complexity of Test files.  
> [1] This 3/23/32 rule, allows for concatenated words, still readable, and it is easy to remember.  
> [2] Having 30 lines allows for seen all the code of a function/method in same screen page.  
> [3] ESLint does not yet provide a way of doing this.  
> [4] Although there exists public APIs that more than this amount of methods, e.g. `java.util.stream.Stream`.  
> [5] Although this rule can be circumvented using static function fields.  
> [6] Exception may be required in constructor , when building of complex inheritance with more than 7 fields as total, for special cases, e.g. using some bad designed but required API, in this case use a @SuppressWarnings("checkstyle:ParameterNumber"). In app own code, Composition should be used over Inheritance.  
> [7] CodeNarc and ESLint does not yet provide a way of doing this.

### Code Style Checking Custom rules

#### Checkstyle custom rules

##### Customized rules

* **`AbbreviationAsWordInName`**: allows abbreviation only for `final` fields.
  * Then these `final` fields abbreviations are check by the Customized PMD's `FieldNamingConventions` rule.

* **`LineLength`**: Line length limit is ignored for lines with some patterns:
  * With only a string.
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
* **`LongMethodName`**: Checks methods' names length.

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
  * tip: when having operators inside a string and some false-positive may arise, use unicode code for the operator, e.g. `".. version=1.0"` -> `"..  version\u003D1.0"`.

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

* **`UseMultilineTernaryOperator`**: Ternary operator is forced to be in multiple lines in order to increase Readability [1]:

  ```java
    condition
      ? expression
      : expression
   ```

Test Rules

* **`DoNotMixBDDandTDD`**: Checks that TDD and BDD are not mixed, to keep Test style consistency.
* **`NameOfTestsMustStartWithShould`**: Checks that the test methods names begin with '`should`', e.g. `shouldExtractSomeValue`.
* **`PreferBDDTesting`**:  Warns that BDD should be prefer over TDD.
* **`UseDoFamilyMethodsWhenStubbing`**: Checks that family methods '`do*`' are use when stubbing: `doAnswer`, `doCallRealMethod`, `doNothing`, `doReturn` & `doThrow`, instead of '`when`' method:
  * `Mockito.when` have some ["issues"](http://javadoc.io/page/org.mockito/mockito-core/latest/org/mockito/Mockito.html#spy) basically when spying.
    * `doAnswer`, `doCallRealMethod`, `doNothing`, `doReturn` & `doThrow` do not present those issues.
  * `BDDMockito.given` is an alias for `Mockito.when`.
* **`UseOnlyMockOrSpyPrefixOnTestFiles`**: Checks that the '`mock`' or '`spy`' prefixes are used only on test's files' code.
* **`UseOnlyWillFamilyMethodsWhenStubbing`**: Checks that family methods '`will*`' are the only ones use when stubbing: `will`, `willAnswer`, `willCallRealMethod`, `willDoNothing`, `willReturn` & `willThrow`, instead of '`given`' method:
  * `Mockito.when` have some ["issues"](http://javadoc.io/page/org.mockito/mockito-core/latest/org/mockito/Mockito.html#spy) basically when spying.
  * `BDDMockito.given` is an alias for `Mockito.when`.
    * `doAnswer`, `doCallRealMethod`, `doNothing`, `doReturn` & `doThrow` do not present those issues.
    * `willAnswer`, `willCallRealMethod`, `willDoNothing`, `willReturn` & `willThrow` are alias for `doAnswer`, `doCallRealMethod`, `doNothing`, `doReturn` & `doThrow`, respectively.
      * `will` is an alias for `willAnswer`.
* **`UseOnlyThenFamilyMethodsWhenMocking`**: Checks that family methods '`then`' are the only ones use when mocking, instead of '`verify`', to keep BDD style consistency.

> [1] Some cases are not detected by CodeNarc.

#### Suppressions

For test files, some rules/checks[1] are disabled in order to allow some freedom:

* No restriction on the number of methods or size.
* Duplication of literals is allowed, to increase test Maintainability.
* Throwing `Exception` is allowed.
* Field Injection is allowed.
* `UseOnlyMockOrSpyPrefixOnTestFiles`.
* Javadoc, obviously, is not required or checked.

> [1] For Checkstyle, PMD and CodeNarc.

#### Required Conventions

* Unit/Integration test files name should be suffix with "**`Test`**" in order to some rules/checks to be suppressed.

#### Code Style convention/tips

When adding indentation for parameters of a constructor/method/function add an extra 2 spaces, which will differentiate it from the inner code:

e.g.:

Given:

```java
  public SomeConstructor(final SomeType parameter1, final @SomeAnnotation SomeTypeN parameterN) {
    this.field1 = parameter1;
    this.fieldN = parameterN;
  }
```

Make it:

```java
  public SomeConstructor(
      final SomeType parameter1,
      final @SomeAnnotation SomeTypeN parameterN) {
    this.field1 = parameter1;
    this.fieldN = parameterN;
  }
```

instead of:

```java
  public SomeConstructor(
    final SomeType parameter1,
    final @SomeAnnotation SomeTypeN parameterN) {
    this.field1 = parameter1;
    this.fieldN = parameterN;
  }
```
__________________

## Using/Configuration

Following is a detailed description of how to use configuration files from a JAR. Take in account that is not necessary to use all the configurations.

### Prerequisites for using it

#### Common

* [Checkstyle plugin](https://docs.gradle.org/current/dsl/org.gradle.api.plugins.quality.Checkstyle.html)

```gradle
 plugins {
   id 'checkstyle'
 }
```

#### Java

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

> Or use the [base-style-config-wrapper Gradle plugin](https://github.com/gmullerb/base-style-config-wrapper) for a quick and easy configuration.

### Java configuration

```gradle
  checkstyle {
    config = resources.text.fromArchiveEntry(configurations.styleConfig, 'java/java-checks.xml')
    configProperties.suppressionFile = resources.text.fromArchiveEntry(configurations.styleConfig, 'java/checks-suppressions.xml').asFile().path
  }

  pmd {
    ruleSets = [] // required with new PMD version
    ruleSetConfig = resources.text.fromUri(configurations.styleConfig, 'java/java-rules.xml')
  }
```

> Or use the [base-style-config-wrapper Gradle plugin](https://github.com/gmullerb/base-style-config-wrapper) for a quick and easy configuration.
> If using both, PMD should be run after Checkstyle, since Checkstyle is "lighter".  
> A complete example in [basecode project - back project](https://github.com/gmullerb/basecode/tree/master/back).

### Groovy configuration

```gradle
  codenarc {
    config = resources.text.fromArchiveEntry(configurations.styleConfig, 'groovy/groovy-rules.groovy')
  }
```

> Or use the [base-style-config-wrapper Gradle plugin](https://github.com/gmullerb/base-style-config-wrapper) for a quick and easy configuration.

### Gradle configuration

1 . Configure plugin:

```gradle
  codenarc {
    config = resources.text.fromArchiveEntry(configurations.styleConfig, 'groovy/groovy-rules.groovy')
  }
```

2 . Define a Codenarc task to check all gradle files in the project tree, e.g.: [basecode project - build.gradle file - assessGradle task](https://github.com/gmullerb/basecode/blob/master/build.gradle).

> Or use the [base-style-config-wrapper Gradle plugin](https://github.com/gmullerb/base-style-config-wrapper) for a quick and easy configuration.
__________________

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
    /java
    /gradle
```

- `src/config/common`: Global Common Checkstyle configuration files.
- `src/config/java`: Checkstyle and PMD configuration files.
- `src/config/gradle`: CodeNarc configuration files.

## Documentation

* [`CHANGELOG.md`](CHANGELOG.md): add information of notable changes for each version here, chronologically ordered [1].

> [1] [Keep a Changelog](http://keepachangelog.com)

## License

[MIT License](/LICENSE.txt)
__________________

## Remember

* Use code style verification tools => Encourages Best Practices, Efficiency, Readability and Learnability.
* Start testing early => Encourages Reliability and Maintainability.
* Code Review everything => Encourages Functional suitability, Performance Efficiency and Teamwork.

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
* Aim to simplicity not intellectualism.
