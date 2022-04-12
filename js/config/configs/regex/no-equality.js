//  Copyright (c) 2021 Gonzalo Müller Bravo.
//  Licensed under the MIT License (MIT), see LICENSE.txt
const { addRegexRuleName } = require('eslint-plugin-regex')

addRegexRuleName('avoid-equality')

module.exports = {
  plugins: [ 'regex' ],
  rules: {
    'regex/avoid-equality': [
      'warn', [
        {
          message: 'Avoid using \'===\' or \'==\', use \'!==\' or \'!(!==)\' instead',
          regex: '(?<![\!=])={2,3}(?!=)'
        }
      ]
    ]
  }
}
