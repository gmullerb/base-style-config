//  Copyright (c) 2019 Gonzalo Müller Bravo.
//  Licensed under the MIT License (MIT), see LICENSE.txt
const merge = require('deepmerge')

const rules = new Map([
  [ 'js-rules', './configs/eslintrc.js' ],
  [ 'import-rules', './configs/import-eslintrc.js' ],
  [ 'react-rules', './configs/react-eslintrc.js' ],
  [ 'react-with-hooks-rules', './configs/react-with-hooks-eslintrc.js' ],
  [ 'regex[copyright]', './configs/regex/copyright.js' ],
  [ 'regex[immutable.ts]', './configs/regex/immutable-ts.js' ],
  [ 'regex[void.function.ts]', './configs/regex/void-function-ts.js' ],
  [ 'regex[jsx]', './configs/regex/jsx.js' ],
  [ 'regex[quotes.jsx]', './configs/regex/quotes-jsx.js' ],
  [ 'regex[allman-braces.jsx]', './configs/regex/allman-braces-jsx.js' ],
  [ 'regex[stroustrup-braces.jsx]', './configs/regex/stroustrup-braces-jsx.js' ],
  [ 'regex[no-html-entities.jsx]', './configs/regex/no-html-entities-jsx.js' ],
  [ 'regex[react.import]', './configs/regex/react-import.js' ],
  [ 'regex[test]', './configs/regex/test.js' ],
  [ 'typescript-rules', './configs/typescript-eslintrc.js' ],
  [ 'unused-imports-rules', './configs/unused-import-eslintrc.js' ]
])

function isErrorLevel(value) {
  return value === 'off' || value === 'warn' || value === 'error'
}

function obtainTail(source1, source2) {
  return source1.length < source2.length
    ? {
        inverted: true,
        max: source1.length,
        tail: new Set(source2.slice(source1.length))
      }
    : {
        inverted: false,
        max: source2.length,
        tail: new Set(source1.slice(source2.length))
      }
}

function setMerge(...sources) {
  const result = new Set()
  sources.forEach(set => set.forEach(value => result.add(value)))
  return result
}

function arrayMerge(source1, source2) { // eslint-disable-line complexity
  const result = new Set()
  const remaining = new Set()
  const { inverted, max, tail } = obtainTail(source1, source2)
  for (let k = 0;k !== max;k++) {
    const type = typeof source1[k]
    if (type === typeof source2[k]) {
      if (source1[k] instanceof Array) {
        result.add(arrayMerge(source1[k], source2[k]))
      }
      else {
        if (isErrorLevel(source1[k])
          || type === 'boolean'
          || ((type === 'number' || type === 'string') &&
          source1[k] === source2[k])) {
          result.add(source2[k])
        }
        else {
          result.add(source1[k])
          remaining.add(source2[k])
        }
      }
    }
    else {
      result.add(source1[k])
      remaining.add(source2[k])
    }
  }
  return inverted
    ? Array.from(setMerge(result, remaining, tail))
    : Array.from(setMerge(result, tail, remaining))
}

const configs = new Proxy({}, {
  get: function (target, property) {
    return merge.all(
      property.split(',')
        .map(name => name.trim())
        .filter(name => name.length !== 0 && rules.has(name))
        .map(name => {
          try {
            return require(rules.get(name))
          }
          catch(error) {
            return null
          }
        })
        .filter(rule => !!rule),
      {
        arrayMerge
      })
  }
})

module.exports = {
  configs
}
