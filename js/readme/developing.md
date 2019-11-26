# Extending/Developing

## Prerequisites

* [Git](https://git-scm.com/downloads) (only if you are going to clone the project).

* [Java](http://www.oracle.com/technetwork/java/javase/downloads) [1].
or
* Node/Npm or Node/Yarn can be used.

## Getting it

Clone or download the project[1], in the desired folder execute:

```sh
git clone https://github.com/gmullerb/react-reducer-context
```

> [1] [Cloning a repository](https://help.github.com/articles/cloning-a-repository/)

## Set up

* **No need**, only download and run (It's Gradle! Yes!).

> Gradle will allow to have different really isolate Node/Npm environments for different projects, but `npm` or `yarn` can be used.

## Folders structure

```
  /config
    /configs
      eslintrc.js
      typescript-eslintrc.js
```

`eslintrc.js`
typescript-eslintrc.js`

### Defined Code Style Checking

The Defined Sets of rules are in:

* **ESLint** rules are in [`config/configs/eslintrc.js`](./config/configs/eslintrc.js).
* ESLint rules for Typescript are in [`config/configs/typescript-eslintrc.js`](./config/configs/typescript-eslintrc.js).

## Building it

### Gradle

Gradle tasks, [`build.gradle`](../build.gradle):

* `lint`: checks eslint style of js files.

> Recommendation: First time run `gradlew` to start from an "ok" code.

### Npm

Npm scripts, [`package.json`](../package.json):

* `lint`: checks eslint style of js files.

## Main documentation

[Back](../README.md)
