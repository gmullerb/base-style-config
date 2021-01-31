//  Copyright (c) 2020 Gonzalo Müller Bravo.
//  Licensed under the MIT License (MIT), see LICENSE.txt

module.exports = {
  plugins: [ 'regex' ],
  rules: {
    'regex/invalid': [
      'error', [
        {
          message: 'Do not use ", Use \' instead',
          regex: '"(?!.*\')',
          replacement: '\'',
          files: {
            inspect: '\\.[jt]sx'
          }
        }
      ]
    ]
  }
}
