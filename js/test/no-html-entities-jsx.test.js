const config = require('../config/configs/regex/no-html-entities-jsx')

const invalidRegex = new RegExp(config.rules['regex/disuse-html-entities'][1][0].regex)

it('should not allow html entity by name', () => {
  expect(invalidRegex.test('&lt;')).toBe(true)
})

it('should not allow html entity by number', () => {
  expect(invalidRegex.test('&#60;')).toBe(true)
})

it('should not allow html entity by hexadecimal number', () => {
  expect(invalidRegex.test('&#x0003C;')).toBe(true)
})
