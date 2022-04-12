<p align="center">
  <img src="https://assets.gitlab-static.net/uploads/-/system/project/avatar/10888432/base-style-config.png"/>
</p>

<h1 align="center">A Set of Essential ESLint rules for JS, TS and React</h1>

<p align="center">
It's part of a Set of Essential Configuration Files for Backend/Frontend/Build code style checking: <a href="https://github.com/gmullerb/base-style-config">base-style-config</a>
</p>

__________________

[![eslint-plugin-base-style-config](https://img.shields.io/badge/npm-eslint--plugin--base--style--config-blue?logo=npm)](https://www.npmjs.com/package/eslint-plugin-base-style-config)
[![ ](https://badgen.net/npm/v/eslint-plugin-base-style-config)](https://www.npmjs.com/package/eslint-plugin-base-style-config)
[![ ](https://badgen.net/npm/node/eslint-plugin-base-style-config)](https://www.npmjs.com/package/eslint-plugin-base-style-config)
[![license](https://img.shields.io/github/license/mashape/apistatus.svg)](LICENSE.txt)
[![Github repo](https://badgen.net/badge/icon/github?icon=github&label)](https://github.com/gmullerb/base-style-config/tree/master/js)
[![Gitlab repo](https://badgen.net/badge/icon/gitlab?icon=gitlab&label)](https://gitlab.com/gmullerb/base-style-config/tree/master/js)
__________________

## Quick Start

1 . Add dependencies:

`package.json`:

```json
  "devDependencies": {
    "eslint": "^7.1.0",
    "eslint-plugin-base-style-config": "2.9.2",
    "eslint-plugin-import": "^2.18.2",
    "@typescript-eslint/eslint-plugin": "^4.2.0",
    "@typescript-eslint/parser": "^4.2.0",
    "eslint-plugin-react": "^7.14.3",
    "eslint-plugin-react-hooks": "^2.0.1",
    "eslint-plugin-regex": "^1.9.0",
    "eslint-plugin-unused-imports": "0.1.2",
```

2 . Configure eslint to use rules from `base-style-config`:

`eslintrc.json`:

```json
{
  "extends": [ "plugin:base-style-config/js-rules, import-rules, typescript-rules, react-with-hooks-rules, regex[copyright], regex[jsx], regex[quotes.jsx]" ],
  "parser": "@typescript-eslint/parser",
  "plugins": [ "base-style-config" ],
```

> Remove the rule sets that are not necessary according to your needs.  

__________________

## Goals

The idea is to have a common and "single" source of code styling rules, which can be used across different projects:

* Provides a **similar set of rules** for Backend's, Frontend's and Build's code.
* Provides a "single" source of configuration files.
* Allow to merge different Eslint Regex Rules.

> In the future, Set of rules will be extracted to an `eslint-config` and Mechanism for Merging Eslint configurations will be extracted to an `eslint-plugin`.

__________________

## Using/Configuration

### Selecting Rules

Due to the way eslint merge rules, it's not possible to select a subset of the rules from a package of rules, this plugin provides [1] a mechanism for doing that[2] => **just use commas to separate rules names** after `plugin:base-style-config`:

e.g. Selecting typescript-rules, regex[jsx] and regex[quotes.jsx] rules:

`eslintrc.json`:

```json
{
  "extends": [
    "plugin:base-style-config/typescript-rules, regex[jsx], regex[quotes.jsx]"
  ],
  "plugins": [
    "base-style-config"
  ],
```

or

```json
{
  "extends": [
    "plugin:base-style-config/typescript-rules",
    "plugin:base-style-config/regex[jsx], regex[quotes.jsx]"
  ],
  "plugins": [
    "base-style-config"
  ],
```

or

```json
{
  "extends": [
    "plugin:base-style-config/typescript-rules",
    "plugin:base-style-config/regex[jsx]",
    "plugin:base-style-config/regex[quotes.jsx]"
  ],
  "plugins": [
    "base-style-config"
  ],
```

> [1] In the future, the Mechanism for Selecting and Merging Eslint configurations will be extracted to its own `eslint-plugin`.  
> [2] For the moment, only `base-style-config` rules.

### `common-rules`

[Set of Common Eslint Rules for using in "any" type of file](https://github.com/gmullerb/base-style-config/blob/HEAD/js/config/configs/common-eslintrc.js).

* To be used in conjunction with [any-eslint-parser](https://any-eslint-parser.github.io/).

1 . Add dependencies:

`package.json`:

```json
  "devDependencies": {
    "any-eslint-parser": "1.0.1",
    "eslint": "^6.3.0",
    "eslint-plugin-base-style-config": "2.9.2",
```

2 . Configure eslint:

`eslintrc.json`:

```json
{
  "extends": [ "plugin:base-style-config/common-rules" ],
  "plugins": [ "base-style-config" ],
  "parser": "any-eslint-parser"
}
```

> Remember order of sets in `extends` is important since each new set will override rules of the previous ones.  

3 . Add to the respective ESLint script task:

`package.json`:

```json
  "scripts": {
    "someESlintTask": "eslint --config .eslintrc-any.json \"**/[\\.a-zA-Z]*.+(js|json|yml|txt|md|svg)\" \"**/.+(gitignore|npmignore)\"",
```

> Can be complemented with [id-rules](https://github.com/gmullerb/base-style-config/blob/HEAD/js/config/configs/id-eslintrc.js).

### `js-rules`

[Set of Eslint Rules for JS](https://github.com/gmullerb/base-style-config/blob/HEAD/js/config/configs/eslintrc.js).

1 . Add dependencies:

`package.json`:

```json
  "devDependencies": {
    "eslint": "^6.3.0",
    "eslint-plugin-base-style-config": "2.9.2",
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
```

> Remember order of sets in `extends` is important since each new set will override rules of the previous ones.  

3 . Add to the respective ESLint script task:

`package.json`:

```json
  "scripts": {
    "someESlintTask": "eslint ..",
```

> Can be complemented with [id-rules](https://github.com/gmullerb/base-style-config/blob/HEAD/js/config/configs/id-eslintrc.js).

### `import-rules`

[Set of Eslint Rules for Import](https://github.com/gmullerb/base-style-config/blob/HEAD/js/config/configs/import-eslintrc.js).

1 . Add dependencies:

`package.json`:

```json
  "devDependencies": {
    "eslint": "^6.3.0",
    "eslint-plugin-base-style-config": "2.9.2",
    "eslint-plugin-import": "^2.18.2",
```

2 . Configure eslint:

`eslintrc.json`:

```json
{
  "extends": [
    "plugin:base-style-config/import-rules"
  ],
  "plugins": [
    "base-style-config"
  ],
```

> Remember order of sets in `extends` is important since each new set will override rules of the previous ones.  
> There is no need to add `import` plugin, it will be automatically added by `base-style-config` plugin.

3 . Add to the respective ESLint script task:

`package.json`:

```json
  "scripts": {
    "someESlintTask": "eslint ..",
```

### `unused-import-rules`

[Set of Eslint Rules for Unused imports](https://github.com/gmullerb/base-style-config/blob/HEAD/js/config/configs/unused-import-eslintrc.js).

1 . Add dependencies:

`package.json`:

```json
  "devDependencies": {
    "eslint": "^6.3.0",
    "eslint-plugin-base-style-config": "2.9.2",
    "eslint-plugin-unused-imports": "0.1.2",
```

2 . Configure eslint:

`eslintrc.json`:

```json
{
  "extends": [
    "plugin:base-style-config/unused-import-rules"
  ],
  "plugins": [
    "base-style-config"
  ],
```

> Remember order of sets in `extends` is important since each new set will override rules of the previous ones.  
> There is no need to add `unused-import` plugin, it will be automatically added by `base-style-config` plugin.

3 . Add to the respective ESLint script task:

`package.json`:

```json
  "scripts": {
    "someESlintTask": "eslint ..",
```

### `typescript-rules`

[Set of Eslint Rules for Typescript](https://github.com/gmullerb/base-style-config/blob/HEAD/js/config/configs/typescript-eslintrc.js).

1 . Add dependencies:

`package.json`:

```json
  "devDependencies": {
    "eslint": "^6.3.0",
    "eslint-plugin-base-style-config": "2.9.2",
    "@typescript-eslint/eslint-plugin": "~1.13.0",
    "@typescript-eslint/parser": "^1.9.0",
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
```

> Remember order of sets in `extends` is important since each new set will override rules of the previous ones.  
> There is no need to add `@typescript-eslint` plugin, it will be automatically added by `base-style-config` plugin.  
> Must add parser: `"parser": "@typescript-eslint/parser"`.  
> `@typescript-eslint/parser` will have a default project: `./tsconfig.json`.  

3 . Add to the respective ESLint script task:

`package.json`:

```json
  "scripts": {
    "someESlintTask": "eslint ..",
```

### `react-rules`

[Set of Eslint Rules for React](https://github.com/gmullerb/base-style-config/blob/HEAD/js/config/configs/react-eslintrc.js).

1 . Add dependencies:

`package.json`:

```json
  "devDependencies": {
    "eslint": "^6.3.0",
    "eslint-plugin-base-style-config": "2.9.2",
    "eslint-plugin-react": "^7.14.3",
```

with hooks:

```json
  "devDependencies": {
    "eslint": "^6.3.0",
    "eslint-plugin-base-style-config": "2.9.2",
    "eslint-plugin-react": "^7.14.3",
    "eslint-plugin-react-hooks": "^2.0.1",
```

2 . Configure eslint:

`eslintrc.json`:

```json
{
  "extends": [
    "plugin:base-style-config/react-rules"
  ],
  "plugins": [
    "base-style-config"
  ],
```

with hooks:

[Set of Eslint Rules for React with Hooks](https://github.com/gmullerb/base-style-config/blob/HEAD/js/config/configs/react-with-hooks-eslintrc.js).

`eslintrc.json`:

```json
{
  "extends": [
    "plugin:base-style-config/react-with-hook-rules"
  ],
  "plugins": [
    "base-style-config"
  ],
```

> Remember order of sets in `extends` is important since each new set will override rules of the previous ones.  
> There is no need to add `react` plugin and respective configuration, it will be automatically added by `base-style-config` plugin.

3 . Add to the respective ESLint script task:

`package.json`:

```json
  "scripts": {
    "someESlintTask": "eslint ..",
```

### Regex Rules

1 . Add dependencies:

`package.json`:

```json
  "devDependencies": {
    "eslint": "^6.3.0",
    "eslint-plugin-base-style-config": "2.9.2",
    "eslint-plugin-regex": "^1.9.0",
```

> `eslint-plugin-regex >= 1.9.0` is required.

2 . Configure eslint:

`eslintrc.json`:

```json
{
  "extends": [
    "plugin:base-style-config/regex[jsx]"
  ],
  "plugins": [
    "base-style-config"
  ],
```

> Remember order of sets in `extends` is important since each new set will override rules of the previous ones.  

3 . Add to the respective ESLint script task:

`package.json`:

```json
  "scripts": {
    "someESlintTask": "eslint ..",
```

#### Custom Regex

[**`regex[copyright]`**: Eslint Regex Rules for Copyright](https://github.com/gmullerb/base-style-config/blob/HEAD/js/config/configs/regex/copyright.js):

* `regex/copyright-required`: checks that "`Copyright (c)`" is present in the file.
  * default error level: **error**.

[**`regex[no-equality]`**: Eslint Regex Rules to avoid using `===` or `==`](https://github.com/gmullerb/base-style-config/blob/HEAD/js/config/configs/regex/no-equality.js):

* `regex/avoid-equality`: checks that `===` or `==` is used, prefer inequalities.
  * default error level: **warn**.

[**`regex[no-export-group]`**: Eslint Regex Rules for Exports](https://github.com/gmullerb/base-style-config/blob/HEAD/js/config/configs/regex/no-export-group.js):

* `regex/disuse-export-group`: checks that export group is not used.
  * default error level: **error**.
  * Using `export` group makes maintainability harder, because adding/removing requires two points of modification. (Although single source of truth can be broke, more than 1 export sentence)

[**`regex[immutable.ts]`**: Eslint Regex Rules for Immutable Typescript](https://github.com/gmullerb/base-style-config/blob/HEAD/js/config/configs/regex/immutable-ts.js):

* regex/disuse-mutable-public-fields: checks that `public` fields are `readonly`.
  * default error level: **error**.
  * Inspects only `.ts` and `.tsx` files.

[**`regex[void.function.ts]`**: Eslint Regex Rules for Void Function Typings](https://github.com/gmullerb/base-style-config/blob/HEAD/js/config/configs/regex/void-function-ts.js):

* `regex/disuse () => void`: checks that `VoidFunction` is used instead of `() => void`.
  * default error level: **error**.
  * This rule is fixable and it will substituted `() => void` with `VoidFunction`.
  * inspects only `.ts` and `.tsx` files

[**`regex[jsx]`**: Set of Eslint Regex Rules for JSX](https://github.com/gmullerb/base-style-config/blob/HEAD/js/config/configs/regex/jsx.js): inspect only `jsx` and `tsx` files:

* `regex/disuse starting jsx with no-jsx`: checks that JSX code should start in its own line.
  * default error level: **error**.
* `regex/disuse ending jsx with no-jsx`: JSX code should end at its own line.
  * default error level: **error**.
* `regex/disuse-several-tags-per-line-in-jsx`: checks that Only One JSX tag per line is use, , except for `<span>`, `<a>` or `<i>`.
  * default error level: **error**.

[**`regex[quotes.jsx]`**: Eslint Regex Rules for Quotes in JSX](https://github.com/gmullerb/base-style-config/blob/HEAD/js/config/configs/regex/quotes-jsx.js):

* `regex/disuse-double-quotes-in-jsx`: checks that `"` are not use in jsx.
  * default error level: **error**.
  * This rule is fixable and it will substituted `"` with `'`.
  * Inspects only `.jsx` and `.tsx` files.

[**`regex[no-react-fragment.jsx]`**: Eslint Regex Rules for JSX Fragments](https://github.com/gmullerb/base-style-config/blob/HEAD/js/config/configs/regex/no-react-fragment-jsx.js):

* `regex/disuse React.Fragment`: checks that `React.Fragment` is not used in jsx, instead use `<></>`.
  * default error level: **error**.
  * Inspects only `.jsx` and `.tsx` files.

[**`regex[allman-braces.jsx]`**: Set of Eslint Regex Rules for Allman braces](https://github.com/gmullerb/base-style-config/blob/HEAD/js/config/configs/regex/allman-braces-jsx.js):

* `regex/disuse-same-line-opening-brace-in-jsx`: checks that Opening Brace in its own line.
  * default error level: **error**.
  * Inspects only `.jsx` and `.tsx` files.

[**`regex[stroustrup-braces.jsx]`**: Set of Eslint Regex Rules for Allman braces](https://github.com/gmullerb/base-style-config/blob/HEAD/js/config/configs/regex/stroustrup-braces-jsx.js):

* `regex/disuse-opening-brace-in-new-line-in-jsx`: checks that Braces should follow Stroustrup (this will reduce Verbosity without loosing Readability).
  * default error level: **error**.
  * Inspects only `.jsx` and `.tsx` files.

[**`regex[no-html-entities.jsx]`**: Eslint Regex Rules that disallow the use of HTML entities](https://github.com/gmullerb/base-style-config/blob/HEAD/js/config/configs/regex/no-html-entities-jsx.js):

* `regex/disuse-html-entities`: checks that `&abc123;` or `&#123;`are not use in jsx.
  * default error level: **error**.
  * Use UTF-8 characters which are "universal" and more Readable.
  * Inspects only `.jsx` and `.tsx` files.

[**`regex[intl.jsx]`**: Eslint Regex Rules for FormatJS in JSX](https://github.com/gmullerb/base-style-config/blob/HEAD/js/config/configs/regex/intl-jsx.js):

* `regex/disuse-mixing-formatMessage-&-FormattedMessage`: checks that `formatMessage` and `FormattedMessage` are not mixed in the same file.
  * default error level: **error**.
  * Inspects only `.jsx` and `.tsx` files.

[**`regex[react.import]`**: Eslint Regex Rules for React Import](https://github.com/gmullerb/base-style-config/blob/HEAD/js/config/configs/regex/react-import.js):

* `regex/disuse 'import React from "react"'`: checks that `import * as React from 'react'` instead of `import React from 'react'`.
  * default error level: **error**.
  * This rule is fixable and it will substituted `import * as React from 'react'` with `import React from 'react'`.
  * React exports a namespace, not a Module.

[**`regex[test]`**: Set of Eslint Regex Rules for Test](https://github.com/gmullerb/base-style-config/blob/HEAD/js/config/configs/regex/test.js).

* It has only 1 **error** rule to check that name of variables create with `jasmine.createSpy()` or `jest.fn()` are prefixed with `mock` or `stub`.

> Some of these rule may be obsolete in the future as other "core" lint rules arise.  

### Errors

When using the plugin, if the following error appears for some rule:

```bash
1:1  error  Definition for rule 'some...rule' was not found   some..rule

```

It means the rule is not found in the version of the respective plugin.

Check `devDependencies` version for the set of rules using in the project, i.e. check version for `eslint`, `eslint-plugin-import`, `eslint-plugin-react`, `eslint-plugin-react-hooks` and/or `@typescript-eslint/eslint-plugin`

### When also developing with Java or Groovy

[Conventions/Voids](https://github.com/gmullerb/base-style-config/blob/HEAD/js/readme/background.md)

__________________

## Evolution

[`CHANGELOG`](CHANGELOG.html): contains the information about changes in each version, chronologically ordered ([Keep a Changelog](http://keepachangelog.com)).

## Extending/Developing

[Developing](https://github.com/gmullerb/base-style-config/blob/HEAD/js/readme/developing.md)

## Contributing

* **Use it**.
* **Share it**.
* **Fork it**.
* [Give it a Star :star:](https://github.com/gmullerb/base-style-config).
* [Propose changes or improvements](https://github.com/gmullerb/base-style-config/issues).
* [Report bugs](https://github.com/gmullerb/base-style-config/issues).

## License

[MIT License](LICENSE.txt)

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
