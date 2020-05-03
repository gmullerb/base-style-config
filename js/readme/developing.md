# Extending/Developing

## Prerequisites

* Node/Npm, Node/Yarn or failing [Java](http://www.oracle.com/technetwork/java/javase/downloads) & [Gradle](https://gradle.org/).
* [Git](https://git-scm.com/downloads) (only if you are going to clone the project).

> Gradle will allow for quick isolate Node/Npm environments for different projects.

## Getting it

Clone or download the project[1], in the desired folder execute:

```sh
git clone https://github.com/gmullerb/react-reducer-context
```

> [1] [Cloning a repository](https://help.github.com/articles/cloning-a-repository/)

## Set up

### Npm

Run:

```sh
npm install
```

> Recommendation: Immediately after installation, run `npm run check` to be sure that initial code is "ok".  

### Gradle

Run:

```sh
./gradlew
```

This command will install `node` (`npm install`) and run `npm run check`.

### Npm scripts

Npm scripts, [`package.json`](../package.json):

* `lint.common`: checks common style of "all" files.
* `lint.config`: checks eslint style js files.
* `check`: will run all lints + `pack`.

Additionally:

* `npm run`: will list all available script/task for the project.

#### From Gradle

Run any scripts using `/gradlew npm_run_.name.`, where `.name.` is the name of the npm script, e.g.:

`lint.common` => `./gradlew npm_run_lint.common`

## Folders structure

```
  /config
    /configs
      /regex
```

### Defined Code Style Checking

[js-rules: Set of Eslint Rules for JS](../config/configs/eslintrc.js),  
[import-rules: Set of Eslint Rules for Import](../config/configs/import-eslintrc.js),  
[unused-imports-rules: Set of Eslint Rules for Unused imports](../config/configs/unused-import-eslintrc.js),  
[typescript-rule: Set of Eslint Rules for Typescript](../config/configs/typescript-eslintrc.js),  
[react-rules: Set of Eslint Rules for React](../config/configs/react-eslintrc.js),  
[react-with-hooks-rules: Set of Eslint Rules for React with Hooks](../config/configs/react-with-hooks-eslintrc.js),  
[regex[copyright]: Set of Eslint Regex Rules for Copyright](../config/configs/regex/copyright.js),  
[regex[immutable-js]: Set of Eslint Regex Rules for Immutable Typescript](../config/configs/regex/immutable-ts.js),  
[regex[jsx]: Set of Eslint Regex Rules for JSX](../config/configs/regex/jsx.js),  
[regex[quotes.jsx]: Set of Eslint Regex Rules for Quotes in JSX](../config/configs/regex/quotes-jsx.js),  
[regex[test]: Set of Eslint Regex Rules for Test](../config/configs/regex/test.js).  

## Main documentation

[Back](../README.md)
