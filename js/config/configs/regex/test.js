//  Copyright (c) 2020 Gonzalo Müller Bravo.
//  Licensed under the MIT License (MIT), see LICENSE.txt

module.exports = {
  plugins: [ 'regex' ],
  rules: {
    'regex/invalid': [
      'error', [
        {
          message: 'Must prefix "variables" with mock or stub when using jasmine.createSpy() or jest.fn()',
          regex: '(let|const|var)\\s*(\\b(?!(mock[[\\$_A-Z1-9]|stub[\\$_A-Z1-9])))\\w*\\b\\s*=\\s*(jasmine\\.createSpy|jest\\.fn)'
        }
      ]
    ]
  }
}
