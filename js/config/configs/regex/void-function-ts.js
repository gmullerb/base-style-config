//  Copyright (c) 2020 Gonzalo Müller Bravo.
//  Licensed under the MIT License (MIT), see LICENSE.txt

module.exports = {
  plugins: [ 'regex' ],
  rules: {
    'regex/invalid': [
      'error', [
        {
          message: 'Use VoidFunction, instead of () => void',
          regex: '\\([ ]*\\)[ ]*=>[ ]*void',
          replacement: 'VoidFunction',
          files: {
            inspect: '\\.tsx?'
          }
        }
      ]
    ]
  }
}
