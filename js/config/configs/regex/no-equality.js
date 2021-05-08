//  Copyright (c) 2021 Gonzalo Müller Bravo.
//  Licensed under the MIT License (MIT), see LICENSE.txt

module.exports = {
  plugins: [ 'regex' ],
  rules: {
    'regex/invalid': [
      'warning', [
        {
          message: 'Avoid using \'===\' or \'==\', use \'!==\' or \'!(!==)\' instead',
          regex: '(?<![\!=])={2,3}(?!=)'
        }
      ]
    ]
  }
}
