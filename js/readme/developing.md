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
      eslintrc.js
      import-eslintrc.js
      typescript-eslintrc.js
      react-eslintrc.js
      react-with-hooks-eslintrc.js
```

### Defined Code Style Checking

The Defined Sets of rules are in:

* **ESLint** rules are in [`config/configs/eslintrc.js`](./config/configs/eslintrc.js).
* ESLint rules for Import are in [`config/configs/import-eslintrc.js`](./config/configs/import-eslintrc.js).
* ESLint rules for Typescript are in [`config/configs/typescript-eslintrc.js`](./config/configs/typescript-eslintrc.js).
* ESLint rules for React are in [`config/configs/react-eslintrc.js`](./config/configs/react-eslintrc.js).
* ESLint rules for React with Hooks are in [`config/configs/react-with-hooks-eslintrc.js`](./config/configs/react-with-hooks-eslintrc.js).

## Main documentation

[Back](../README.md)
