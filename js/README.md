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
[![Github repo](https://badgen.net/badge/icon/github?icon=github&label)](https://github.com/gmullerb/eslint-plugin-base-style-config)
[![Gitlab repo](https://badgen.net/badge/icon/gitlab?icon=gitlab&label)](https://gitlab.com/gmullerb/eslint-plugin-base-style-config)
__________________

## Quick Start

1 . Add dependencies:

`package.json`:

```json
  ..
  "devDependencies": {
    "eslint": "^6.3.0",
    "eslint-plugin-base-style-config": "2.1.0",
    "eslint-plugin-import": "^2.18.2",
    "@typescript-eslint/eslint-plugin": "^1.13.0",
    "@typescript-eslint/parser": "^1.9.0",
    "eslint-plugin-react": "^7.14.3",
    "eslint-plugin-react-hooks": "^2.0.1",
    "eslint-plugin-regex": "^1.1.0",
    "eslint-plugin-unused-imports": "0.1.2",
    ..
```

2 . Configure eslint to use rules from `base-style-config`:

`eslintrc.json`:

```json
{
  "extends": [
    "plugin:base-style-config/js-rules, import-rules, typescript-rules, react-with-hooks-rules, regex[copyright], regex[jsx], regex[quotes.jsx]",
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

> Remove the rule sets that are not necessary according to your needs.  
> Take a look to the set of rules:  
[js-rules: Set of Eslint Rules for JS](config/configs/eslintrc.js),  
[import-rules: Set of Eslint Rules for Import](config/configs/import-eslintrc.js),  
[unused-imports-rules: Set of Eslint Rules for Unused imports](config/configs/unused-import-eslintrc.js),  
[typescript-rule: Set of Eslint Rules for Typescript](config/configs/typescript-eslintrc.js),  
[react-rules: Set of Eslint Rules for React](config/configs/react-eslintrc.js),  
[react-with-hooks-rules: Set of Eslint Rules for React with Hooks](config/configs/react-with-hooks-eslintrc.js),  
[regex[copyright]: Set of Eslint Regex Rules for Copyright](config/configs/regex/copyright.js),  
[regex[immutable-js]: Set of Eslint Regex Rules for Immutable Typescript](config/configs/regex/immutable-ts.js),  
[regex[jsx]: Set of Eslint Regex Rules for JSX](config/configs/regex/jsx.js),  
[regex[quotes.jsx]: Set of Eslint Regex Rules for Quotes in JSX](config/configs/regex/quotes-jsx.js),  
[regex[test]: Set of Eslint Regex Rules for Test](config/configs/regex/test.js).  
> Take a look to Regex rules:  
[Custom Eslint Regex rules](#custom-regex)  
[Mixing rules](#mixing-regexrules)
__________________

## Goals

The idea is to have a common and "single" source of code styling rules, which can be used across different projects:

* Provides a **similar set of rules** for Backend's, Frontend's and Build's code.
* Provides a "single" source of configuration files.
* Allow to merge different Eslint Regex Rules.

> In the future, Set of rules will be extracted to an `eslint-config` and Mechanism for Merging Eslint configurations will be extracted to an `eslint-plugin`.

## Background

[Background](readme/background.md)

__________________

## Using/Configuration

### JS Rules

[Set of Eslint Rules for JS](config/configs/eslintrc.js).

1 . Add dependencies:

`package.json`:

```json
  ..
  "devDependencies": {
    "eslint": "^6.3.0",
    "eslint-plugin-base-style-config": "2.1.0",
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

> Remember order of sets in `extends` is important since each new set will override rules of the previous ones.  

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

### Import Rules

[Set of Eslint Rules for Import](config/configs/import-eslintrc.js).

1 . Add dependencies:

`package.json`:

```json
  ..
  "devDependencies": {
    "eslint": "^6.3.0",
    "eslint-plugin-base-style-config": "2.1.0",
    "eslint-plugin-import": "^2.18.2",
    ..
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
  "rules": {
    ..
  }
}
```

> Remember order of sets in `extends` is important since each new set will override rules of the previous ones.  
> There is no need to add `import` plugin, it will be automatically added by `base-style-config` plugin.

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

### Unused Import Rules

[unused-imports-rules: Set of Eslint Rules for Unused imports](config/configs/unused-import-eslintrc.js).

1 . Add dependencies:

`package.json`:

```json
  ..
  "devDependencies": {
    "eslint": "^6.3.0",
    "eslint-plugin-base-style-config": "2.1.0",
    "eslint-plugin-unused-imports": "0.1.2",
    ..
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
  "rules": {
    ..
  }
}
```

> Remember order of sets in `extends` is important since each new set will override rules of the previous ones.  
> There is no need to add `unused-import` plugin, it will be automatically added by `base-style-config` plugin.

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

### TS Rules

[Set of Eslint Rules for Typescript](config/configs/typescript-eslintrc.js).

1 . Add dependencies:

`package.json`:

```json
  ..
  "devDependencies": {
    "eslint": "^6.3.0",
    "eslint-plugin-base-style-config": "2.1.0",
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

> Remember order of sets in `extends` is important since each new set will override rules of the previous ones.  
> There is no need to add `@typescript-eslint` plugin, it will be automatically added by `base-style-config` plugin.  
> Must add parser: `"parser": "@typescript-eslint/parser"`.  
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

### React Rules

[Set of Eslint Rules for React](config/configs/react-eslintrc.js).

1 . Add dependencies:

`package.json`:

```json
  ..
  "devDependencies": {
    "eslint": "^6.3.0",
    "eslint-plugin-base-style-config": "2.1.0",
    "eslint-plugin-react": "^7.14.3"
    ..
```

with hooks:


```json
  ..
  "devDependencies": {
    "eslint": "^6.3.0",
    "eslint-plugin-base-style-config": "2.1.0",
    "eslint-plugin-react": "^7.14.3",
    "eslint-plugin-react-hooks": "^2.0.1",
    ..
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
  "rules": {
    ..
  }
}
```

with hooks:

[Set of Eslint Rules for React with Hooks](config/configs/react-with-hooks-eslintrc.js).

`eslintrc.json`:

```json
{
  "extends": [
    "plugin:base-style-config/react-with-hook-rules"
  ],
  "plugins": [
    "base-style-config"
  ],
  "rules": {
    ..
  }
}
```

> Remember order of sets in `extends` is important since each new set will override rules of the previous ones.  
> There is no need to add `react` plugin and respective configuration, it will be automatically added by `base-style-config` plugin.

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

### Regex Rules

1 . Add dependencies:

`package.json`:

```json
  ..
  "devDependencies": {
    "eslint": "^6.3.0",
    "eslint-plugin-base-style-config": "2.1.0",
    "eslint-plugin-regex": "^1.1.0",
    ..
```

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
  "rules": {
    ..
  }
}
```

> Remember order of sets in `extends` is important since each new set will override rules of the previous ones.  

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

#### Custom Regex

[regex[copyright]: Set of Eslint Regex Rules for Copyright](config/configs/regex/copyright.js):

* For the moment, it has only 1 rule to check that "`Copyright (c)`" is present in the file.

[regex[immutable-js]: Set of Eslint Regex Rules for Immutable Typescript](config/configs/regex/immutable-ts.js): inspects only `.ts` and `.tsx` files:

* For the moment, it has only 1 rule to check that `public` fields are `readonly`.

[regex[jsx]: Set of Eslint Regex Rules for JSX](config/configs/regex/jsx.js): inspect only `jsx` and `tsx` files, has the following rules:

* JSX code should start in its own line,
* JSX code should end at its own line,
* Only One JSX tag per line.

[regex[quotes.jsx]: Set of Eslint Regex Rules for Quotes in JSX](config/configs/regex/quotes-jsx.js): inspects only `.jsx` and `.tsx` files:

* It has only 1 rules to check that `"` are not use in jsx.

[regex[test]: Set of Eslint Regex Rules for Test](config/configs/regex/test.js).  

* For the moment, it has only 1 rule to check that name of variables create with `jasmine.createSpy()` or `jest.fn()` are prefixed with `mock` or `stub`.

#### Mixing Regex/Rules

Due to the way eslint merge rules, it's not possible to merge [Eslint Regex rules](https://www.npmjs.com/package/eslint-plugin-regex). this plugin provide [1] a mechanism for merging any set of rules (not just Regex rules [2]) => **just use commas to separate rules names** after `plugin:base-style-config`:

e.g. Mixing:

`eslintrc.json`:

```json
{
  "extends": [
    "plugin:base-style-config/typescript-rules",
    "plugin:base-style-config/regex[jsx], regex[quotes-jsx]"
  ],
  "plugins": [
    "base-style-config"
  ],
  "rules": {
    ..
  }
}
```

or

```json
{
  "extends": [
    "plugin:base-style-config/typescript-rules, regex[jsx], regex[quotes-jsx]"
  ],
  "plugins": [
    "base-style-config"
  ],
  "rules": {
    ..
  }
}
```

When using only one regex rule:

```json
{
  "extends": [
    "plugin:base-style-config/typescript-rules",
    "plugin:base-style-config/regex[jsx]"
  ],
  "plugins": [
    "base-style-config"
  ],
  "rules": {
    ..
  }
}
```

When using more than one regex rule, **must be mixed**:

```json
{
  "extends": [
    "plugin:base-style-config/typescript-rules",
    "plugin:base-style-config/regex[jsx], regex[quotes-jsx]"
  ],
  "plugins": [
    "base-style-config"
  ],
  "rules": {
    ..
  }
}
```

Be aware that:

* If some extension has `regex/required` and/or `regex/invalid` rules will override any merging for those.
  * If extending locally, re-add "plugin:base-style-config/..", e.g. `.eslintrc.js` has `"plugin:base-style-config/regex[jsx], regex[quotes-jsx]"`, and `src/.eslintrc.js` extends `.eslintrc.js` and add `regex[immutable-ts]`, then `src/.eslintrc.js` should use `"plugin:base-style-config/regex[jsx], regex[quotes-jsx], regex[immutable-ts]"`.
* having local `regex/required` and/or `regex/invalid` rules will override any merging for those.

> [1] In the future, the Mechanism for Merging Eslint configurations will be extracted to its own `eslint-plugin`.  
> [2] For the moment, only `base-style-config` rules.

### Errors

When using the plugin, if the following error appears for some rule:

```bash
1:1  error  Definition for rule 'some...rule' was not found   some..rule

```

It means the rule is not found in the version of the respective plugin.

Check `devDependencies` version for the set of rules using in the project, i.e. check version for `eslint`, `eslint-plugin-import`, `eslint-plugin-react`, `eslint-plugin-react-hooks` and/or `@typescript-eslint/eslint-plugin`

__________________

## Extending/Developing

[Developing](readme/developing.md)

## Contributing

* **Use it**.
* **Share it**.
* **Fork it**.
* [Give it a Star :star:](https://github.com/gmullerb/base-style-config).
* [Propose changes or improvements](https://github.com/gmullerb/base-style-config/issues).
* [Report bugs](https://github.com/gmullerb/base-style-config/issues).

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
