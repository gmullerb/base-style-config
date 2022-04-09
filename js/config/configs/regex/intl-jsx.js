//  Copyright (c) 2020 Gonzalo Müller Bravo.
//  Licensed under the MIT License (MIT), see LICENSE.txt
const { addRegexRuleName } = require('eslint-plugin-regex')

addRegexRuleName('disuse-mixing-formatMessage-&-FormattedMessage')

module.exports = {
  plugins: [ 'regex' ],
  rules: {
    'regex/disuse-mixing-formatMessage-&-FormattedMessage': [
      'error', [
        {
          message: 'Don\'t mixed formatMessage and FormattedMessage',
          regex: '((\\b|\\.?)formatMessage(\\(|\\b)[\\s\\S]*\\<FormattedMessage\\b)|((\\<FormattedMessage\\b)[\\s\\S]*(\\b|\\.?)formatMessage(\\(|\\b))',
          files: {
            inspect: '\\.[jt]sx'
          }
        }
      ]
    ]
  }
}
