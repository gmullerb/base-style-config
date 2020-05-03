//  Copyright (c) 2020 Gonzalo Müller Bravo.
//  Licensed under the MIT License (MIT), see LICENSE.txt

module.exports = {
  plugins: [ 'regex' ],
  rules: {
    'regex/required': [
      'error', [
        {
          message: 'Copyright (c) must be defined in every file',
          regex: 'Copyright \\(c\\)'
        }
      ]
    ]
  }
}
