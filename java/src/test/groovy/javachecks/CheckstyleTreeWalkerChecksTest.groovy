//  Copyright (c) 2019 Gonzalo Müller Bravo.
//  Licensed under the MIT License (MIT), see LICENSE.txt
package javachecks

import util.CheckstyleTestUtils

import groovy.transform.CompileStatic
import groovy.util.slurpersupport.GPathResult
import groovy.util.slurpersupport.Node
import groovy.util.slurpersupport.NodeChild

import java.util.function.Predicate
import java.util.regex.Pattern
import java.util.stream.Stream

import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.ParameterizedTest

import static org.junit.jupiter.api.Assertions.assertTrue
import static org.junit.jupiter.api.Assertions.assertFalse

@CompileStatic
class CheckstyleTreeWalkerChecksTest {
  private static GPathResult treeWalkerChildrenNodes

  @BeforeAll
  public static void beforeAll() {
    final XmlSlurper reader = new XmlSlurper()
    reader.setFeature('http://apache.org/xml/features/disallow-doctype-decl', false)
    reader.setProperty(javax.xml.XMLConstants.ACCESS_EXTERNAL_DTD, 'all')
    final GPathResult rootNodes = reader.parse('src/config/java/java-checks.xml')
    final GPathResult checkerNode = rootNodes.find { Node node ->
      node
        .attributes()
        .get('name') == 'Checker'
    }
    final GPathResult checkerChildrenNodes = checkerNode.children()
    final GPathResult treeWalkerNode = checkerChildrenNodes.find { NodeChild node ->
      node
        .attributes()
        .get('name') == 'TreeWalker'
    }
    treeWalkerChildrenNodes = treeWalkerNode.children()
  }

  @Nested
  private class CallOnlyOneMethodPerLineForChainedCallTest {
    private Predicate<String> checkPattern

    @BeforeEach
    public void beforeEach() {
      final GPathResult ruleXmlNode = CheckstyleTestUtils
        .findNodeByTypeAndId(treeWalkerChildrenNodes, 'RegexpSinglelineJava', 'CallOnlyOneMethodPerLineForChainedCall')
      checkPattern = Pattern
        .compile(CheckstyleTestUtils.obtainChildValue(ruleXmlNode, 'format'))
        .asPredicate()
    }

    @Test
    public void shouldFindPatternWhenInstantiatingAndCallingAnyMethod() {
      assertTrue(checkPattern.test('new Class()\u002E.some()'))
    }

    @Test
    public void shouldFindPatternWhenChainingMethods() {
      assertTrue(checkPattern.test('object.some()\u002Esome()'))
    }

    @Test
    public void shouldFindPatternWhenCastAtBeginning() {
      assertTrue(checkPattern.test('((Cast) object)\u002Esome()\u002Esome()'))
    }

    @Test
    public void shouldFindPatternWhenCastInside() {
      assertTrue(checkPattern.test('object.some((Cast) some)\u002Esome()'))
    }

    @Test
    public void shouldNotFindPatternWhenInstantiating() {
      assertFalse(checkPattern.test('new Class()'))
    }

    @Test
    public void shouldNotFindPatternWhenNotChaining() {
      assertFalse(checkPattern.test('object\u002Esome()'))
    }

    @Test
    public void shouldNotFindPatternWhenCallsAreSplit() {
      assertFalse(checkPattern.test(')\u002Esome()'))
    }

    @Test
    public void shouldNotFindPatternWhenCallsAreSplitWithArgument() {
      assertFalse(checkPattern.test('object)\u002Esome()'))
    }

    @Test
    public void shouldNotFindPatternWhenContainsAnyCast() {
      assertFalse(checkPattern.test('object)\u002Esome((Cast) some)'))
    }
  }

  @Nested
  private class UseMultilineTernaryOperatorTest {
    private Predicate<String> checkPattern

    @BeforeEach
    public void beforeEach() {
      final GPathResult ruleXmlNode = CheckstyleTestUtils
        .findNodeByTypeAndId(treeWalkerChildrenNodes, 'RegexpSinglelineJava', 'UseMultilineTernaryOperator')
      checkPattern = Pattern
        .compile(CheckstyleTestUtils.obtainChildValue(ruleXmlNode, 'format'))
        .asPredicate()
    }

    @Test
    public void shouldFindPatternWhenTernaryOperatorInSameLine() {
      assertTrue(checkPattern.test('? some() : some()'))
    }

    @Test
    public void shouldNotFindPatternWhenTernaryOperatorNotInSameLine() {
      assertFalse(checkPattern.test('? some() '))
    }

    @Test
    public void shouldNotFindPatternWhenUsingGenericMethods() {
      assertFalse(checkPattern.test('<?> type some() {'))
    }

    @Test
    public void shouldNotFindPatternWhenUsingGenericClasses() {
      assertFalse(checkPattern.test('class Some<?> {'))
    }

    @Test
    public void shouldNotFindPatternWhenTernaryOperatorInsideAnyString() {
      assertFalse(checkPattern.test('"? some() : some() "'))
    }

    @Test
    public void shouldNotFindPatternWhenUsingMethodReference() {
      assertFalse(checkPattern.test('Some::method'))
    }

    @Test
    public void shouldNotFindPatternWhenColonInsideJsonString() {
      assertFalse(checkPattern.test('{\"identifier\":\"theId\",\"modified\":\"2019-05-01T15:12:12.123142Z\",\"uri\":\"theUri\"}'))
    }
  }

  @Nested
  private class NamingConvetionTest {

    @Nested
    private class NameCommonTest {
      public static Stream<Predicate<String>> patternsToCheck() {
        return Stream.of(
          Pattern
            .compile(CheckstyleTestUtils.obtainChildValue(
              CheckstyleTestUtils
                .findNodeByTypeAndId(treeWalkerChildrenNodes, 'ConstantName', 'ConstantName'),
              'format'))
            .asPredicate(),
          Pattern
            .compile(CheckstyleTestUtils.obtainChildValue(
              CheckstyleTestUtils
                .findNodeByType(treeWalkerChildrenNodes, 'LambdaParameterName'),
              'format'))
            .asPredicate(),
          Pattern
            .compile(CheckstyleTestUtils.obtainChildValue(
              CheckstyleTestUtils
                .findNodeByType(treeWalkerChildrenNodes, 'LocalFinalVariableName'),
              'format'))
            .asPredicate(),
          Pattern
            .compile(CheckstyleTestUtils.obtainChildValue(
              CheckstyleTestUtils
                .findNodeByType(treeWalkerChildrenNodes, 'LocalVariableName'),
              'format'))
            .asPredicate(),
          Pattern
            .compile(CheckstyleTestUtils.obtainChildValue(
              CheckstyleTestUtils
                .findNodeByType(treeWalkerChildrenNodes, 'MethodName'),
              'format'))
            .asPredicate(),
          Pattern
            .compile(CheckstyleTestUtils.obtainChildValue(
              CheckstyleTestUtils
                .findNodeByType(treeWalkerChildrenNodes, 'ParameterName'),
              'format'))
            .asPredicate()
        )
      }

      @ParameterizedTest
      @MethodSource('patternsToCheck')
      public void shouldFindPatternWhenSmall(final Predicate<String> checkPattern) {
        assertTrue(checkPattern.test('any'))
      }

      @ParameterizedTest
      @MethodSource('patternsToCheck')
      public void shouldFindPatternWhenSmallEndingWithUppercase(final Predicate<String> checkPattern) {
        assertTrue(checkPattern.test('anY'))
      }

      @ParameterizedTest
      @MethodSource('patternsToCheck')
      public void shouldFindPatternWhenUsingNonConsecutiveUppercases(final Predicate<String> checkPattern) {
        assertTrue(checkPattern.test('someTestTrue'))
      }

      @ParameterizedTest
      @MethodSource('patternsToCheck')
      public void shouldFindPatternWhenUsingUppercasesAt2(final Predicate<String> checkPattern) {
        assertTrue(checkPattern.test('sOmeTestTrue'))
      }

      @ParameterizedTest
      @MethodSource('patternsToCheck')
      public void shouldFindPatternWhenUsingUppercasesAt3(final Predicate<String> checkPattern) {
        assertTrue(checkPattern.test('soMeTestTrue'))
      }

      @ParameterizedTest
      @MethodSource('patternsToCheck')
      public void shouldFindPatternWhenEndingWithUppercases(final Predicate<String> checkPattern) {
        assertTrue(checkPattern.test('soMeTestTruE'))
      }

      @ParameterizedTest
      @MethodSource('patternsToCheck')
      public void shouldFindPatternWhenUsingAtLimitLength(final Predicate<String> checkPattern) {
        assertTrue(checkPattern.test('a2345678901234567890123'))
      }

      @ParameterizedTest
      @MethodSource('patternsToCheck')
      public void shouldNotFindPatternWhenUsingConsecutiveUppercases(final Predicate<String> checkPattern) {
        assertFalse(checkPattern.test('someTTrue'))
      }

      @ParameterizedTest
      @MethodSource('patternsToCheck')
      public void shouldNotFindPatternWhenUsingConsecutiveUppercasesAtTheEnd(final Predicate<String> checkPattern) {
        assertFalse(checkPattern.test('someTestTrUE'))
      }

      @ParameterizedTest
      @MethodSource('patternsToCheck')
      public void shouldNotFindPatternWhenSmall(final Predicate<String> checkPattern) {
        assertFalse(checkPattern.test('sm'))
      }

      @ParameterizedTest
      @MethodSource('patternsToCheck')
      public void shouldNotFindPatternWhenTooSmall(final Predicate<String> checkPattern) {
        assertFalse(checkPattern.test('s'))
      }

      @ParameterizedTest
      @MethodSource('patternsToCheck')
      public void shouldNotFindPatternWhenBig(final Predicate<String> checkPattern) {
        assertFalse(checkPattern.test('a23456789012345678901234'))
      }
    }

    @Nested
    private class ConstantNameTest {
      private Predicate<String> checkPattern

      @BeforeEach
      public void beforeEach() {
        final GPathResult ruleXmlNode = CheckstyleTestUtils
          .findNodeByTypeAndId(treeWalkerChildrenNodes, 'ConstantName', 'ConstantName')
        checkPattern = Pattern
          .compile(CheckstyleTestUtils.obtainChildValue(ruleXmlNode, 'format'))
          .asPredicate()
      }

      @Test
      public void shouldFindPatternWhenSmall() {
        assertTrue(checkPattern.test('ANY'))
      }

      @Test
      public void shouldFindPatternWhenSmallWithUnderScore() {
        assertTrue(checkPattern.test('ANY_1'))
      }
    }

    @Nested
    private class ConstantNameLengthTest {
      private Predicate<String> checkPattern

      @BeforeEach
      public void beforeEach() {
        final GPathResult ruleXmlNode = CheckstyleTestUtils
          .findNodeByTypeAndId(treeWalkerChildrenNodes, 'ConstantName', 'ConstantNameLength')
        checkPattern = Pattern
          .compile(CheckstyleTestUtils.obtainChildValue(ruleXmlNode, 'format'))
          .asPredicate()
      }

      @Test
      public void shouldFindPatternWhenLengthIsOk() {
        assertTrue(checkPattern.test('id1'))
      }

      @Test
      public void shouldFindPatternWhenLengthIsAtLimit() {
        assertTrue(checkPattern.test('id345678901234567890123'))
      }

      @Test
      public void shouldFindPatternWhenLengthIsAtLimitUppercase() {
        assertTrue(checkPattern.test('ID345678901234567890123456789012'))
      }

      @Test
      public void shouldFindPatternWhenLengthIsSmall() {
        assertFalse(checkPattern.test('id'))
      }

      @Test
      public void shouldFindPatternWhenLengthIsBig() {
        assertFalse(checkPattern.test('id3456789012345678901234'))
      }

      @Test
      public void shouldFindPatternWhenLengthIsBigUppercase() {
        assertFalse(checkPattern.test('ID3456789012345678901234567890123'))
      }
    }

    @Nested
    private class LambdaParameterNameTest {
      private Predicate<String> checkPattern

      @BeforeEach
      public void beforeEach() {
        final GPathResult ruleXmlNode = CheckstyleTestUtils
          .findNodeByType(treeWalkerChildrenNodes, 'LambdaParameterName')
        checkPattern = Pattern
          .compile(CheckstyleTestUtils.obtainChildValue(ruleXmlNode, 'format'))
          .asPredicate()
      }

      @Test
      public void shouldFindPatternWhenUsingId() {
        assertTrue(checkPattern.test('id'))
      }
    }

    @Nested
    private class LocalFinalVariableNameTest {
      private Predicate<String> checkPattern

      @BeforeEach
      public void beforeEach() {
        final GPathResult ruleXmlNode = CheckstyleTestUtils
          .findNodeByType(treeWalkerChildrenNodes, 'LocalFinalVariableName')
        checkPattern = Pattern
          .compile(CheckstyleTestUtils.obtainChildValue(ruleXmlNode, 'format'))
          .asPredicate()
      }

      @Test
      public void shouldFindPatternWhenUsingK() {
        assertTrue(checkPattern.test('K'))
      }
    }

    @Nested
    private class LocalVariableNameTest {
      private Predicate<String> checkPattern

      @BeforeEach
      public void beforeEach() {
        final GPathResult ruleXmlNode = CheckstyleTestUtils
          .findNodeByType(treeWalkerChildrenNodes, 'LocalVariableName')
        checkPattern = Pattern
          .compile(CheckstyleTestUtils.obtainChildValue(ruleXmlNode, 'format'))
          .asPredicate()
      }

      @Test
      public void shouldFindPatternWhenUsingId() {
        assertTrue(checkPattern.test('id'))
      }

      @Test
      public void shouldFindPatternWhenUsingk() {
        assertTrue(checkPattern.test('k'))
      }
    }

    @Nested
    private class MemberNameTest {
      private Predicate<String> checkPattern

      @BeforeEach
      public void beforeEach() {
        final GPathResult ruleXmlNode = CheckstyleTestUtils
          .findNodeByType(treeWalkerChildrenNodes, 'MemberName')
        checkPattern = Pattern
          .compile(CheckstyleTestUtils.obtainChildValue(ruleXmlNode, 'format'))
          .asPredicate()
      }

      @Test
      public void shouldFindPatternWhenUsingId() {
        assertTrue(checkPattern.test('id'))
      }
    }

    @Nested
    private class MethodNameTest {
      private Predicate<String> checkPattern

      @BeforeEach
      public void beforeEach() {
        final GPathResult ruleXmlNode = CheckstyleTestUtils
          .findNodeByType(treeWalkerChildrenNodes, 'MethodName')
        checkPattern = Pattern
          .compile(CheckstyleTestUtils.obtainChildValue(ruleXmlNode, 'format'))
          .asPredicate()
      }

      @Test
      public void shouldFindPatternWhenUsingOf() {
        assertTrue(checkPattern.test('of'))
      }
    }

    @Nested
    private class ParameterNameTest {
      private Predicate<String> checkPattern

      @BeforeEach
      public void beforeEach() {
        final GPathResult ruleXmlNode = CheckstyleTestUtils
          .findNodeByType(treeWalkerChildrenNodes, 'ParameterName')
        checkPattern = Pattern
          .compile(CheckstyleTestUtils.obtainChildValue(ruleXmlNode, 'format'))
          .asPredicate()
      }

      @Test
      public void shouldFindPatternWhenUsingId() {
        assertTrue(checkPattern.test('id'))
      }
    }

    @Nested
    private class TypeNameTest {
      private Predicate<String> checkPattern

      @BeforeEach
      public void beforeEach() {
        final GPathResult ruleXmlNode = CheckstyleTestUtils
          .findNodeByTypeAndId(treeWalkerChildrenNodes, 'TypeName', 'TypeName')
        checkPattern = Pattern
          .compile(CheckstyleTestUtils.obtainChildValue(ruleXmlNode, 'format'))
          .asPredicate()
      }

      @Test
      public void shouldFindPatternWhenSmall() {
        assertTrue(checkPattern.test('Any'))
      }

      @Test
      public void shouldFindPatternWhenSmallEndingWithUppercase() {
        assertTrue(checkPattern.test('AnY'))
      }

      @Test
      public void shouldFindPatternWhenUsingNonConsecutiveUppercases() {
        assertTrue(checkPattern.test('SomeTestTrue'))
      }

      @Test
      public void shouldFindPatternWhenUsingUppercasesAt3() {
        assertTrue(checkPattern.test('SoMeTestTrue'))
      }

      @Test
      public void shouldFindPatternWhenEndingWithUppercases() {
        assertTrue(checkPattern.test('SoMeTestTruE'))
      }

      @Test
      public void shouldFindPatternWhenUsingAtLimitLength() {
        assertTrue(checkPattern.test('S23456789012345678901234567890123'))
      }

      @Test
      public void shouldNotFindPatternWhenUsingConsecutiveUppercases() {
        assertFalse(checkPattern.test('SomeTTrue'))
      }

      @Test
      public void shouldNotFindPatternWhenUsingConsecutiveUppercasesAtTheEnd() {
        assertFalse(checkPattern.test('SomeTestTrUE'))
      }

      @Test
      public void shouldNotFindPatternWhenUsingUppercasesAt2() {
        assertFalse(checkPattern.test('SOmeTestTrue'))
      }

      @Test
      public void shouldNotFindPatternWhenSmall() {
        assertFalse(checkPattern.test('Sm'))
      }

      @Test
      public void shouldNotFindPatternWhenTooSmall() {
        assertFalse(checkPattern.test('S'))
      }

      @Test
      public void shouldNotFindPatternWhenBig() {
        assertFalse(checkPattern.test('S234567890123456789012345678901234'))
      }
    }

    @Nested
    private class TestTypeNameTest {
      private Predicate<String> checkPattern

      @BeforeEach
      public void beforeEach() {
        final GPathResult ruleXmlNode = CheckstyleTestUtils
          .findNodeByTypeAndId(treeWalkerChildrenNodes, 'TypeName', 'TestTypeName')
        checkPattern = Pattern
          .compile(CheckstyleTestUtils.obtainChildValue(ruleXmlNode, 'format'))
          .asPredicate()
      }

      @Test
      public void shouldFindPatternWhenTestType() {
        assertTrue(checkPattern.test('S2345678901234567890123Test'))
      }

      @Test
      public void shouldFindPatternWhenSmall() {
        assertTrue(checkPattern.test('SmTest'))
      }

      @Test
      public void shouldNotFindPatternWhenUsingConsecutiveUppercases() {
        assertFalse(checkPattern.test('SomeTTrueTest'))
      }

      @Test
      public void shouldNotFindPatternWhenUsingUppercasesAt2() {
        assertFalse(checkPattern.test('SOmeTestTrueTest'))
      }

      @Test
      public void shouldNotFindPatternWhenTooSmall() {
        assertFalse(checkPattern.test('STest'))
      }
    }

    @Nested
    private class UseOnlyMockOrSpyPrefixOnTestFilesTest {
      private Predicate<String> checkPattern

      @BeforeEach
      public void beforeEach() {
        final GPathResult ruleXmlNode = CheckstyleTestUtils
          .findNodeByTypeAndId(treeWalkerChildrenNodes, 'RegexpSinglelineJava', 'UseOnlyMockOrSpyPrefixOnTestFiles')
        checkPattern = Pattern
          .compile(CheckstyleTestUtils.obtainChildValue(ruleXmlNode, 'format'))
          .asPredicate()
      }

      @Test
      public void shouldFindPatternWhenUsingMockPrefix() {
        assertTrue(checkPattern.test(' mockSome'))
      }

      @Test
      public void shouldFindPatternWhenUsingSpyPrefix() {
        assertTrue(checkPattern.test(' spySome'))
      }

      @Test
      public void shouldNotFindPatternWhenUsingMockInsideAnyWord() {
        assertFalse(checkPattern.test(' theMock '))
      }

      @Test
      public void shouldNotFindPatternWhenUsingSpyInsideAnyWord() {
        assertFalse(checkPattern.test(' theSpy '))
      }
    }
  }
}
