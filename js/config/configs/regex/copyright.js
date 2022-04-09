//  Copyright (c) 2020 Gonzalo Müller Bravo.
//  Licensed under the MIT License (MIT), see LICENSE.txt
const { addRegexRuleName } = require('eslint-plugin-regex')

addRegexRuleName('copyright-required')

module.exports = {
  plugins: [ 'regex' ],
  rules: {
    'regex/copyright-required': [
      'error', [
        {
          message: 'Copyright (c) must be defined in every file',
          regex: 'Copyright \\(c\\)'
        }
      ]
    ]
  }
}
