# Background

## Code Style Checking

Code Style Checking tries to catch differences on coding style.

* The idea is to guaranteed the code looks similar beyond developers preference.
* It will allowed to force some best practices and standard of coding across team development.
* It is then recommend should be used before code is submitted to repository by developers or is approved.

There are different development tools for doing this.

[base-style-config](https://github.com/gmullerb/base-style-config) uses **[Checkstyle](http://checkstyle.sourceforge.net)**, **[PMD](https://pmd.github.io)**, **[ESlint](https://eslint.org)** and **[CodeNarc](http://codenarc.sourceforge.net)**.

### Code Style Checking Common rules

#### Common Backend's, Frontend's and Build's code rules - [base-style-config](https://github.com/gmullerb/base-style-config)

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

#### Code Style convention and Voids

Although these set of rules are opinionated, some voids were left in order to make sets more flexible.

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

##### Parameters

When adding indentation for parameters of a constructor/method/function add an extra 2 spaces, which will differentiate it from the inner code:

e.g.:

Given:

```js
  public SomeConstructor(parameter1, @SomeAnnotation parameterN) {
    this.field1 = parameter1;
    this.fieldN = parameterN;
  }
```

Make it:

```js
  public SomeConstructor(
      parameter1,
      @SomeAnnotation parameterN) {
    this.field1 = parameter1;
    this.fieldN = parameterN;
  }
```

instead of:

```js
  public SomeConstructor(
    parameter1,
    @SomeAnnotation parameterN) {
    this.field1 = parameter1;
    this.fieldN = parameterN;
  }
```

##### Import

To enforce exports only as a group:

```json
  "rules": {
    "import/group-exports": "error"
  }
```

To enforce that the exports be placed at the end of the file:

```json
  "rules": {
    "import/exports-last": "error"
  }
```

To avoid importing namespaces (which is usually bad):

```json
  "rules": {
    "import/no-namespace": "error"
  }
```

##### Typescript

To avoid the type `any` in the code (which is really bad):

```json
  "rules": {
    "@typescript-eslint/no-explicit-any": "error"
  }
```

Avoid unused variables is a must, but in some special cases (e.g. in React when properties are dynamically injected) the use `@typescript-eslint/no-unused-vars` + `argsIgnorePattern` is useful:

e.g.

```json
  "rules": {
    "@typescript-eslint/no-unused-vars": ["error", {
      "argsIgnorePattern": "^someInjectedProp$"
    }],
  }
```

##### React

If only Function Components are desired add the following rule:

```json
  "rules": {
    "react/prefer-es6-class": [
      "error",
      "never"
    ]
  }
```

## Main documentation

[Back](../README.md)
