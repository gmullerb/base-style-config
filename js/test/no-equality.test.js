const config = require('../config/configs/regex/no-equality')

const invalidRegex = new RegExp(config.rules['regex/avoid-equality'][1][0].regex)

it('should not allow ===', () => {
  expect(invalidRegex.test('some===some')).toBe(true)
})

it('should not allow  ==', () => {
  expect(invalidRegex.test('some==some')).toBe(true)
})

it('should not allow === with spaces, case 1', () => {
  expect(invalidRegex.test('some === some')).toBe(true)
})

it('should not allow  == with spaces, case 1', () => {
  expect(invalidRegex.test('some == some')).toBe(true)
})

it('should not allow === with spaces, case 2', () => {
  expect(invalidRegex.test('some ===some')).toBe(true)
})

it('should not allow  == with spaces, case 2', () => {
  expect(invalidRegex.test('some ==some')).toBe(true)
})

it('should not allow === with spaces, case 3', () => {
  expect(invalidRegex.test('some=== some')).toBe(true)
})

it('should not allow  == with spaces, case 3', () => {
  expect(invalidRegex.test('some== some')).toBe(true)
})

it('should not allow === at beginning', () => {
  expect(invalidRegex.test('===some')).toBe(true)
})

it('should not allow  == at beginning', () => {
  expect(invalidRegex.test('==some')).toBe(true)
})

it('should not allow === at end', () => {
  expect(invalidRegex.test('some===')).toBe(true)
})

it('should not allow  == at end', () => {
  expect(invalidRegex.test('some==')).toBe(true)
})

it('should allow !==', () => {
  expect(invalidRegex.test('some!==some')).toBe(false)
})

it('should allow ====', () => {
  expect(invalidRegex.test('//==== some')).toBe(false)
})
