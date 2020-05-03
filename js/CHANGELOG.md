# ESLint Plugin Base Style Config Change Log

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
