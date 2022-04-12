<p align="center">
  <img src="https://assets.gitlab-static.net/uploads/-/system/project/avatar/10888432/base-style-config.png"/>
</p>

<h1 align="center">Base coding style check configuration</h1>

<p align="center">
  <b>A Set of Essential Configuration Files for Backend/Frontend/Build code style checking.</b>
</p>

__________________

[![license](https://img.shields.io/github/license/mashape/apistatus.svg)](/LICENSE.txt)

This project is licensed under the terms of the [MIT license](/LICENSE.txt).
__________________

## Quick Start

* [Java style checking - Quick Start](./java/README.md#quick-start)
* [JS style checking - Quick Start](./js/README.md#quick-start)

__________________

## Goals

The idea is to have a common and "single" source of code styling rules, which can be used across different projects:

* Provides a **similar set of rules** for Backend's, Frontend's and Build's code.
* Provides a "single" source of configuration files.

## Features

* Provides a set of configuration files for **[Checkstyle](http://checkstyle.sourceforge.net)**, **[PMD](https://pmd.github.io)**, **[ESlint](https://eslint.org)** and **[CodeNarc](http://codenarc.sourceforge.net)**, bundle in a JAR:
  * A [Common Checkstyle's set](/config/common/common-checks.xml), for "all" files (.java, .js, .css, .gradle, etc.).
  * A [Checkstyle's set](/config/java/java-checks.xml), for Java's code.
  * A [PMD's set](/config/java/java-rules.xml), for Java's code.
  * A [ESLint's set](/config/js/.eslintrc.json), for JS's code.
    * A [ESLint's set for Typescript](/config/js/.typescript-eslintrc.json), for JS's code.
  * A [Codenarc's set](/config/groovy/groovy-rules.groovy) for Build's Gradle code and Groovy's code.
  * All sets define a similar set of rules in order to get the homogeneity in code style between Backend's, Frontend's and Build's code.
* Adds some new custom rules to Checkstyle, PMD adn Codenarc.

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

* [Java style checking](./java/README.md)
* [JS style checking](./js/README.md)

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
* Type parameters names must be in uppercase, have a length between 3 and 23, starting with a letter.
* Brackets style: Stroustrup.
* Indentation: "Tab" character = 2 spaces.
* Line length limit: 160 characters per line.
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

##### Semicolon

Semicolon is required by Java[1], but no by Groovy, then when having JS code, the use of the semicolon could be enforced based on the Backend language, in order to get some consistency between Backend and Frontend.

To enforce Java style, set the respective project's eslint configuration (`package.json` or `.eslintrc.*`) to:

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

Although this rule is not set, the project should always set `semi` rule in order to guarantee consistency in the code:

```json
  "rules": {
    "semi": ["error", ".."]
  }
```

> [1] Although new programming languages don't require semicolon, may be forcing JS to use of semicolon can be a waste of typing.  

##### Quotes

The use of single `'` or double `"` quote string could be enforced based on the Backend language, in order to get some consistency between Backend and Frontend.

To enforce Java style, set the respective project's eslint configuration (`package.json` or `.eslintrc.*`) to:

```json
  "rules": {
    "quotes":  ["error", "double"]
  }
```

To enforce Groovy style, set the respective project's eslint configuration (`package.json` or `.eslintrc.*`) to:

```json
  "rules": {
    "quotes":  ["error", "single"]
  }
```

Although this rule is not set, the project should always set `quotes` rule in order to guarantee consistency in the code:

```json
  "rules": {
    "quotes":  ["error", ".."]
  }
```

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

## Contributing

* **Use it**.
* **Share it**.
* **Fork it**.
* [Give it a Star](https://github.com/gmullerb/base-style-config).
* [Propose changes or improvements](https://github.com/gmullerb/base-style-config/issues).
* [Report bugs](https://github.com/gmullerb/base-style-config/issues).

## Evolution

[`CHANGELOG`](CHANGELOG.html): contains the information about changes in each version, chronologically ordered ([Keep a Changelog](http://keepachangelog.com)).

## License

[MIT License](/LICENSE.txt)
__________________

## Remember

* Use code style verification tools => Encourages Best Practices, Efficiency, Readability and Learnability.
* Code Review everything => Encourages Functional suitability, Performance Efficiency and Teamwork.
* If viable, Start testing early => Encourages Reliability and Maintainability.

## Additional words

Don't forget:

* **Love what you do**.
* **Learn everyday**.
* **Learn yourself**.
* **Share your knowledge**.
* **Think different!**.
* **Learn from the past, dream on the future, live and enjoy the present to the max!**.
* **Enjoy and Value the Quest** (It's where you learn and grow).

At life:

* Let's act, not complain.
* Be flexible.

At work:

* Let's give solutions, not questions.
* Aim to simplicity not intellectualism.
