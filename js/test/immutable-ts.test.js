const config = require('../config/configs/regex/immutable-ts')

const invalidRegex = new RegExp(config.rules['regex/disuse-mutable-public-fields'][1][0].regex)

it('should not allow public not readonly', () => {
  expect(invalidRegex.test('public FIELD=value')).toBe(true)
})

it('should not allow static public not readonly', () => {
  expect(invalidRegex.test('public static FIELD=value')).toBe(true)
})

it('should allow public readonly', () => {
  expect(invalidRegex.test('public readonly FIELD=value')).toBe(false)
})

it('should allow static public readonly', () => {
  expect(invalidRegex.test('public static readonly FIELD=value')).toBe(false)
})

it('should allow private not readonly', () => {
  expect(invalidRegex.test('private FIELD=value')).toBe(false)
})

it('should allow static private not readonly', () => {
  expect(invalidRegex.test('private static FIELD=value')).toBe(false)
})

it('should allow async method', () => {
  expect(invalidRegex.test('public async method(')).toBe(false)
})

it('should allow static async method', () => {
  expect(invalidRegex.test('public static async method(param1: string, paramN: number): Promise<void> {')).toBe(false)
})
