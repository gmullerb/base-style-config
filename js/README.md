# A Set of Essential ESLint rules for JS and TS

[![license](https://img.shields.io/github/license/mashape/apistatus.svg)](LICENSE.txt) ![GitHub package.json version](https://img.shields.io/github/package-json/v/gmullerb/base-style-config/js.svg?logo=npm) [![eslint-plugin-base-style-config](https://img.shields.io/badge/-eslint--plugin--base--style--config-green?logo=npm)](https://www.npmjs.com/package/eslint-plugin-base-style-config)

**It's part of a Set of Essential Configuration Files for Backend/Frontend/Build code style checking: [base-style-config](https://github.com/gmullerb/base-style-config).**

This project is licensed under the terms of the [MIT license](LICENSE.txt).
__________________

## Quick Start

### JS configuration

1 . Add dependencies:

`package.json`:

```json
  ..
  "devDependencies": {
    "eslint": "^6.3.0",
    "eslint-plugin-base-style-config": "1.0.0",
    ..
```

2 . Configure eslint:

`eslintrc.json`:

```json
{
  "extends": [
    "plugin:base-style-config/typescript-rules"
  ],
  "plugins": [
    "base-style-config"
  ],
  "rules": {
    ..
  }
}
```

3 . Take a look to the set of rules: [Set of Eslint Rules for JS](./config/configs/eslintrc.js)

#### TS configuration

1 . Add dependencies:

`package.json`:

```json
  ..
  "devDependencies": {
    "eslint": "^6.3.0",
    "eslint-plugin-base-style-config": "1.0.0",
    "@typescript-eslint/eslint-plugin": "~1.13.0",
    "@typescript-eslint/parser": "^1.9.0",
    ..
```

2 . Configure eslint:

`eslintrc.json`:

```json
{
  "extends": [
    "plugin:base-style-config/typescript-rules"
  ],
  "parser": "@typescript-eslint/parser",
  "plugins": [
    "base-style-config"
  ],
  "rules": {
    ..
  }
}
```

3 . Take a look to the set of rules: [Set of Eslint Rules for Typescript](./config/configs/typescript-eslintrc.js)

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

## Defined Code Style Checking

The Defined Sets of rules are in:

* **ESLint** rules are in [`config/configs/eslintrc.js`](./config/configs/eslintrc.js).
* ESLint rules for Typescript are in [`config/configs/typescript-eslintrc.js`](./config/configs/typescript-eslintrc.js).

### Code Style Checking Common rules

#### Common Backend's, Frontend's and Build's code rules

To highlights:

* Naming convention:
  * CamelCase.
  * All in uppercase for constants.
    * Non-consecutive underscore (`_`) for Backend.
* Fields/Variables/Methods names must have a length between 3 and 23, except for `id`, `of`(for factories), `k` & `K`(for loops) [1].
  * Java/Groovy Test Code [3]:
    * Test instance methods names should start with `should`.
    * Other instance methods valid names are: `afterAll`, `afterEach`, `beforeAll` or `beforeEach`. [5]
      * Groovy: other instance methods must have a length between 3 and 23.
      * Java: static methods must have a length between 3 and 23.
* Classes/Constants names must have a length between 3 and 32 [1].
  * Test Class names will not have length limit.
* Type parameters names must have a length between 1 and 10, all in uppercase.
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
  public SomeConstructor(final parameter1, final @SomeAnnotation parameterN) {
    this.field1 = parameter1;
    this.fieldN = parameterN;
  }
```

Make it:

```java
  public SomeConstructor(
      final parameter1,
      final @SomeAnnotation parameterN) {
    this.field1 = parameter1;
    this.fieldN = parameterN;
  }
```

instead of:

```java
  public SomeConstructor(
    final parameter1,
    final @SomeAnnotation parameterN) {
    this.field1 = parameter1;
    this.fieldN = parameterN;
  }
```
__________________

## Using/Configuration

### JS

1 . Add dependencies:

`package.json`:

```json
  ..
  "devDependencies": {
    "eslint": "^6.3.0",
    "eslint-plugin-base-style-config": "1.0.0",
    ..
```

2 . Configure eslint:

`eslintrc.json`:

```json
{
  "extends": [
    "plugin:base-style-config/js-rules"
  ],
  "plugins": [
    "base-style-config"
  ],
  "rules": {
    ..
  }
}
```

> `@typescript-eslint/parser` will have a default project: `./tsconfig.json`.  

3 . Add to the respective ESLint script task:

`package.json`:

```json
  "scripts": {
    "someESlintTask": "eslint ..",
  },
```

if using Gradle:

```gradle
  task assessSomeESLint(type: NpmTask) {
    args = [
      'run',
      'someESlintTask'
    ]
  }
```

### TS

1 . Add dependencies:

`package.json`:

```json
  ..
  "devDependencies": {
    "eslint": "^6.3.0",
    "eslint-plugin-base-style-config": "1.0.0",
    "@typescript-eslint/eslint-plugin": "~1.13.0",
    "@typescript-eslint/parser": "^1.9.0",
    ..
```

2 . Configure eslint:

`eslintrc.json`:

```json
{
  "extends": [
    "plugin:base-style-config/typescript-rules"
  ],
  "parser": "@typescript-eslint/parser",
  "plugins": [
    "base-style-config"
  ],
  "rules": {
    ..
  }
}
```

3 . Add to the respective ESLint script task:

`package.json`:

```json
  "scripts": {
    "someESlintTask": "eslint ..",
  },
```

if using Gradle:

```gradle
  task assessSomeESLint(type: NpmTask) {
    args = [
      'run',
      'someESlintTask'
    ]
  }
```
__________________

## Extending/Developing

[Developing](readme/developing.md)

## Documentation

* [`CHANGELOG.md`](CHANGELOG.md): add information of notable changes for each version here, chronologically ordered [1].

> [1] [Keep a Changelog](http://keepachangelog.com)

## License

[MIT License](LICENSE.txt)
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
