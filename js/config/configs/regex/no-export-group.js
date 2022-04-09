//  Copyright (c) 2020 Gonzalo Müller Bravo.
//  Licensed under the MIT License (MIT), see LICENSE.txt
const { addRegexRuleName } = require('eslint-plugin-regex')

addRegexRuleName('disuse-export-group')

module.exports = {
  plugins: [ 'regex' ],
  rules: {
    'regex/disuse-export-group': [
      'error', [
        {
          message: 'Use individual exports',
          regex: '^[ \\t]*export\\s*\\{'
        }
      ]
    ]
  }
}
