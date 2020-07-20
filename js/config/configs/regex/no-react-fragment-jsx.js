//  Copyright (c) 2020 Gonzalo Müller Bravo.
//  Licensed under the MIT License (MIT), see LICENSE.txt

module.exports = {
  plugins: [ 'regex' ],
  rules: {
    'regex/invalid': [
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
