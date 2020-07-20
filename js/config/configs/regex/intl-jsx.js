//  Copyright (c) 2020 Gonzalo Müller Bravo.
//  Licensed under the MIT License (MIT), see LICENSE.txt

module.exports = {
  plugins: [ 'regex' ],
  rules: {
    'regex/invalid': [
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
