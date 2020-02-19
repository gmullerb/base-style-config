# ESLint Plugin Base Style Config Change Log

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
