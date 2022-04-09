//  Copyright (c) 2020 Gonzalo Müller Bravo.
//  Licensed under the MIT License (MIT), see LICENSE.txt
const { addRegexRuleName } = require('eslint-plugin-regex')

addRegexRuleName('disuse React.Fragment')

module.exports = {
  plugins: [ 'regex' ],
  rules: {
    'regex/disuse React.Fragment': [
      'error', [
        {
          message: 'Use \'<></>\', instead of \'<React.Fragment></React.Fragment>\'',
          regex: '(React\\.|<|\\/)Fragment>',
          files: {
            inspect: '\\.[jt]sx'
          }
        }
      ]
    ]
  }
}
