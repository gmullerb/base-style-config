//  Copyright (c) 2020 Gonzalo Müller Bravo.
//  Licensed under the MIT License (MIT), see LICENSE.txt

module.exports = {
  plugins: [ 'regex' ],
  rules: {
    'regex/invalid': [
      'error', [
        {
          message: 'Stroustrup opening brace',
          regex: '\\>[\\r\\n\\v\\f]+[ \\t]*\\{\\s*$',
          files: {
            inspect: '\\.[jt]sx'
          }
        },
        {
          message: 'Stroustrup closing brace',
          regex: '^[ \\t]*\\}[ \\t]*[\\r\\n\\v\\f]+[ \\t]*\\<',
          files: {
            inspect: '\\.[jt]sx'
          }
        }
      ]
    ]
  }
}
