# ESLint Plugin Base Style Config Change Log

## 2.9.4 - October 2022

* Adds `one-var` rule, with `never` option to avoid "hidden" variables declaration, i.e. enforce each variable to be declare on its own line.

## 2.9.3 - August 2022

* Removes `init-declarations` rule.
  * Sometimes makes no sense to make an initialization and "impacts" the performance.
* Removes `quote-props` rule.
  * Maintainability was being affected since if a new "string" key was required when there were "non-string" keys, then all other keys needed the quotes.
  * `as-needed` seemed like a good choice, but but still too strict, so lets quotes for props be free.
* Removes `valid-jsdoc` rule (It's deprecated).
* Improves project configuration.
  * Renames `readme` folder to `.readme` in order to prevent it to be packed.
  * Removes `.npmignore`.
  * Changes CI pipeline configuration to reduce free time consumption.
* Updates Documentation.

## 2.9.2 - April 2022

* Fixes `regex/avoid-equality`'s default error level.
* Updates Documentation.

## 2.9.1 - April 2022

* Fixes peerDependencies.
* Updates Documentation.

## 2.9.0 - April 2022

* Removes rules `array-element-newline`, `array-bracket-spacing`, `object-curly-spacing` this should be now locally set.
* Changes `complexity` `max` to 15.
* Changes `max-len` `ignoreRegExpLiterals` to `true`.
* Updates `eslint-plugin-regex` to new version `1.9.0` and start using new Name Regex Rules.
* Updates Documentation.

## 2.8.1 - March 2022

* Fixes peerDependencies.

## 2.8.0 - May 2021

* Adds `common-rules` for using in "any" type of file in conjunction with [any-eslint-parser](https://any-eslint-parser.github.io/).

## 2.7.0 - May 2021

* Increase line size.
* Adds warning regex rule `regex[no-equality]` to avoid using `===` or `==`, recommendation is to substitute with `!(!==)`.

## 2.6.0 - January 2020

* Makes rules `regex[void.function.ts]`, `regex[quotes.jsx]` and `regex[react.import]` fixable.
* Improves project configuration.

## 2.5.2 - November 2020

* Fixes `immutable.ts` rule, now consider `static` fields.
* Adds `ignoreComments` to `indent` rule.
* Updates `no-html-entities.jsx` rule to check Hexadecimal values.
* Removes length check from `id-match` rule, for length use `id-length`.
* Allows `object` in `@typescript-eslint/ban-types` rule.

## 2.5.1 - July 2020

* Fixes `One JSX tag per line`.
* Removes `ignoreComments` from `max-len` rule, it was giving issues when working with JSDoc.

## 2.5.0 - July 2020

* Adds new REGEX rule `no-export-group.js`.
* Adds new REGEX rule `no-react-fragment-jsx.js`.
* Adds new REGEX rule `intl-jsx.js`.
* Extracts `id-match` to its own file `id-eslintrc.js`, it seems too opinionated and variable.
* Improves `One JSX tag per line`.
* Removes `Stroustrup closing brace` rule.

## 2.4.1 - July 2020

* Actually removes `sort-imports`.

## 2.4.0 - July 2020

* Adds new REGEX file `stroustrup-braces-jsx.js`
* Extracts `Opening brace should be in a new line`to its own file `allman-braces-jsx.js`.
* Fixes `immutable.ts` for `async` functions.

## 2.3.1 - July 2020

* Fixes rule `Opening brace should be in a new line`, should ignore lambdas.

## 2.3.0 - July 2020

* Adds a new rule to `jsx` rules: Opening brace should be in a new line.
* Adds exception for `<span>`,`<a>` & `<i>` to `Only One JSX tag per line` rule.
* Adds Regex rule `regex[no-html-entities.jsx]` to disallow the use of HTML entities.
* Disables `react/no-unescaped-entities` (which is part of react/recommended).
* Removes `sort-imports`.
* Adds `import/order`.
* Adjusts `@typescript-eslint/ban-types` for `Object` and `{}`.
* Adds `@typescript-eslint/explicit-member-accessibility`.
* Adjusts `react/jsx-first-prop-new-line` to `never`, why? right now there is no rule like jsx-min-props-per-line, having only 1 prop per line with an small amount of properties increase the verbosity.

## 2.2.0 - June 2020

* Adds Regex rule `regex[react.import]` to encourage the use of `import * as React from 'react'`, React doesn't really export a module, export a namespace.
* Adds Regex rule `regex[void.function.ts]`.
* Adds `offsetTernaryExpressions` to `indent` rule in order to increase code Readability.
* Updates `object-property-newline` settings to `allowAllPropertiesOnSameLine`. sometimes this removes verbosity from the code.
* Updates `operator-linebreak` settings to change requirement for `&&` to have the line break after, which helps a lot in JSX.
* Updates `react/jsx-max-props-per-line` to have `maximum`, this will allow to inline props to reduce verbosity of the code.
* Turn off `"@typescript-eslint/prefer-readonly-parameter-types"` hoping in the future  it may have more options, is an excellent rule but difficult to use in the practice.
* Removes `@typescript-eslint/explicit-module-boundary-types`, is was giving hard time with small callbacks functions which are use a lot,just keeping `explicit-function-return-type`.
* Removes `@typescript-eslint/generic-type-naming` was deprecated and removed from `@typescript-eslint`.

## 2.1.2 - May 2020

* Adds DOM types to `id-match`.
* Fixes `One JSX tag per line` rule for `jsx`.

## 2.1.1 - May 2020

* Updates Type parameters names regular expression.
* Updates README files.

## 2.1.0 - May 2020

* Adds Regex rules: `regex[copyright]`, `regex[immutable.ts]`, `regex[jsx]`, `regex[quotes.jsx]` & `regex[test]`.
* Adds `unused-imports` rules.
* Adds Mechanism for Merging Eslint configurations.
* Updates README files.

## 2.0.0 - May 2020

* Adds `$` and `$$` to `id-match`, useful when using `jquery` or `protractor`.
* Adds some prepositions to `id-match`: `at`, `by`, `in`, `on`, `to` & `up`.
* Adds `array-bracket-spacing`, to add consistency with `object-curly-spacing`.
* Adds `import/no-named-as-default-member`.
* Removes `import/no-namespace`, why? Some libraries are defined with namespaces, and using some TS options as `allowSyntheticDefaultImports` can be helpful, but can also be misleading, so Just avoid creating confusions in the team. (besides, importing as namespace are pretty helpful when testing).
* Adds `import/namespace`, to do some checking when using namespaces.
* Adds `@typescript-eslint/no-namespace` to avoid creating namespaces.
* Adds `allowExpressions: true` to `@typescript-eslint/explicit-function-return-type`, useful for quick callback functions.
* Adds `@typescript-eslint/explicit-module-boundary-types`.
* Adds `@typescript-eslint/consistent-type-definitions` with `interface`.
* Adds `@typescript-eslint/no-misused-promises`.
* Adds `@typescript-eslint/no-inferrable-types`.
* Adds `@typescript-eslint/no-misused-new`.
* Adds `@typescript-eslint/prefer-as-const`.
* Adds `@typescript-eslint/prefer-readonly`.
* Adds `@typescript-eslint/prefer-readonly-parameter-types`.
* Adds `@typescript-eslint/prefer-reduce-type-parameter`.
* Improves project configuration.
* Updates README files.

## 1.0.2 - February 2020

* Updates the Type Parameters names rule with an additional pattern to allow to given more context in the name.
* Updates README files.

## 1.0.1 - December 2019

* Adds new rules `array-bracket-newline`, `array-element-newline` and `object-curly-newline`.
* Adds `allowWithDecorator` option to `@typescript-eslint/no-extraneous-class` rule, to allows empty classes when they have decorators.
* Adds `capIsNew: false` option to `new-cap` rule to allow Decorators and other special cases.
* Updates `id-match` rule:
  * Allows Type Variable.
  * Adds options to check only Declarations and Object properties, and to ignore Destructurings.
* Adds new set of rules: [import-eslintrc.js](js/config/configs/import-eslintrc.js).
* Adds new set of rules: [react-eslintrc.js](js/config/configs/react-eslintrc.js).
* Removes `extends: '..eslintrc.js'` to allow more freedom on selection of set of rules.
* Removes `parser` from [typescript-eslintrc.js](js/config/configs/typescript-eslintrc.js), was creating confusions, since it must be explicitly added when using this set of rules.
* Updates README file.
