//  Copyright (c) 2019 Gonzalo Müller Bravo.
//  Licensed under the MIT License (MIT), see LICENSE.txt
package javachecks

import util.CheckstyleTestUtils

import groovy.transform.CompileStatic
import groovy.util.slurpersupport.GPathResult
import groovy.util.slurpersupport.Node

import java.util.function.Predicate
import java.util.regex.Pattern

import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

import static org.junit.jupiter.api.Assertions.assertTrue
import static org.junit.jupiter.api.Assertions.assertFalse

@CompileStatic
class CheckstyleRootChecksTest {
  private static GPathResult checkerChildrenNodes

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
    checkerChildrenNodes = checkerNode.children()
  }

  @Nested
  private class TestingRulesTest {
    @Nested
    private class NameOfTestsMustStartWithShouldTest {
      private Predicate<String> checkPattern

      @BeforeEach
      public void beforeEach() {
        final GPathResult ruleXmlNode = CheckstyleTestUtils
          .findNodeByTypeAndId(checkerChildrenNodes, 'RegexpMultiline', 'NameOfTestsMustStartWithShould')
        checkPattern = Pattern.compile(
          CheckstyleTestUtils.obtainChildValue(ruleXmlNode, 'format'),
          Pattern.MULTILINE).asPredicate()
      }

      @Test
      public void shouldFindPatternWhenNameOfTestsDoesNotStartWithShouldForJunit() {
        assertTrue(checkPattern.test('\u0040Test public void someTest'))
      }

      @Test
      public void shouldFindPatternWhenNameOfTestsStartWithShouldForJunit() {
        assertFalse(checkPattern.test('\u0040Test public void shouldTest'))
      }

      @Test
      public void shouldFindPatternWhenNameOfTestsDoesNotStartWithShouldForJunit4() {
        assertTrue(checkPattern.test('\u0040org.junit.Test public void someTest'))
      }

      @Test
      public void shouldFindPatternWhenNameOfTestsStartWithShouldForJunit4() {
        assertFalse(checkPattern.test('\u0040org.junit.Test public void shouldTest'))
      }

      @Test
      public void shouldFindPatternWhenNameOfTestsDoesNotStartWithShouldForJunit5() {
        assertTrue(checkPattern.test('\u0040org.junit.jupiter.api.Test public void someTest'))
      }

      @Test
      public void shouldFindPatternWhenNameOfTestsStartWithShouldForJunit5() {
        assertFalse(checkPattern.test('\u0040org.junit.jupiter.api.Test public void shouldTest'))
      }

      @Test
      public void shouldFindPatternWhenNameOfTestsDoesNotStartWithShouldForTestNg() {
        assertTrue(checkPattern.test('\u0040org.testng.annotations.Test public void someTest'))
      }

      @Test
      public void shouldNotFindPatternWhenNameOfTestsStartWithShouldForTestNg() {
        assertFalse(checkPattern.test('\u0040org.testng.annotations.Test public void shouldTest'))
      }
    }

    @Nested
    private class UseDoFamilyMethodsWhenStubbingTest {
      private Predicate<String> checkPattern

      @BeforeEach
      public void beforeEach() {
        final GPathResult ruleXmlNode = CheckstyleTestUtils
          .findNodeByTypeAndId(checkerChildrenNodes, 'RegexpMultiline', 'UseDoFamilyMethodsWhenStubbing')
        checkPattern = Pattern.compile(
          CheckstyleTestUtils.obtainChildValue(ruleXmlNode, 'format'),
          Pattern.MULTILINE).asPredicate()
      }

      @Test
      public void shouldFindPatternWhenUsingMockitoWhen() {
        assertTrue(checkPattern.test('\u004Dockito.when'))
      }

      @Test
      public void shouldFindPatternWhenImportingStaticMockitoWhen() {
        assertTrue(checkPattern.test('\u006Frg.mockito.Mockito\u002Ewhen'))
      }

      @Test
      public void shouldNotFindPatternWhenUsingBddMockitoWhen() {
        assertFalse(checkPattern.test('\u0042DDMockito\u002Ewhen'))
      }

      @Test
      public void shouldNotFindPatternWhenImportingStaticBddMockitoWhen() {
        assertFalse(checkPattern.test('\u006Frg.mockito.BDDMockito\u002Ewhen'))
      }

      @Test
      public void shouldNotFindPatternWhenUsingMockitoDoReturn() {
        assertFalse(checkPattern.test('\u004Dockito.doReturn'))
      }
    }

    @Nested
    private class UseOnlyWillFamilyMethodsWhenStubbingTest {
      private Predicate<String> checkPattern

      @BeforeEach
      public void beforeEach() {
        final GPathResult ruleXmlNode = CheckstyleTestUtils
          .findNodeByTypeAndId(checkerChildrenNodes, 'RegexpMultiline', 'UseOnlyWillFamilyMethodsWhenStubbing')
        checkPattern = Pattern.compile(
          CheckstyleTestUtils.obtainChildValue(ruleXmlNode, 'format'),
          Pattern.MULTILINE).asPredicate()
      }

      @Test
      public void shouldFindPatternWhenUsingBddMockitoGiven() {
        assertTrue(checkPattern.test('\u0042DDMockito\u002Egiven'))
      }

      @Test
      public void shouldFindPatternWhenImportingStaticBddMockitoGiven() {
        assertTrue(checkPattern.test('\u006Frg.mockito.BDDMockito\u002Egiven'))
      }

      @Test
      public void shouldFindPatternWhenUsingBddMockitoWhen() {
        assertTrue(checkPattern.test('\u0042DDMockito\u002Ewhen'))
      }

      @Test
      public void shouldFindPatternWhenImportingStaticBddMockitoWhen() {
        assertTrue(checkPattern.test('\u006Frg.mockito.BDDMockito\u002Ewhen'))
      }

      @Test
      public void shouldNotFindPatternWhenUsingMockitoWhen() {
        assertFalse(checkPattern.test('\u004Dockito.when'))
      }

      @Test
      public void shouldNotFindPatternWhenImportingStaticMockitoWhen() {
        assertFalse(checkPattern.test('\u006Frg.mockito.Mockito\u002Ewhen'))
      }

      @Test
      public void shouldNotFindPatternWhenUsingBddMockitoWillReturn() {
        assertFalse(checkPattern.test('\u004Dockito.willReturn'))
      }
    }

    @Nested
    private class UseOnlyThenFamilyMethodsWhenMockingTest {
      private Predicate<String> checkPattern

      @BeforeEach
      public void beforeEach() {
        final GPathResult ruleXmlNode = CheckstyleTestUtils
          .findNodeByTypeAndId(checkerChildrenNodes, 'RegexpMultiline', 'UseOnlyThenFamilyMethodsWhenMocking')
        checkPattern = Pattern.compile(
          CheckstyleTestUtils.obtainChildValue(ruleXmlNode, 'format'),
          Pattern.MULTILINE).asPredicate()
      }

      @Test
      public void shouldFindPatternWhenUsingBddMockitoVerify() {
        assertTrue(checkPattern.test('\u0042DDMockito.verify'))
      }

      @Test
      public void shouldFindPatternWhenImportingStaticBddMockitoVerify() {
        assertTrue(checkPattern.test('\u006Frg.mockito.BDDMockito\u002Everify'))
      }

      @Test
      public void shouldNotFindPatternWhenUsingBddMockitoThen() {
        assertFalse(checkPattern.test('\u0042DDockito.then'))
      }
    }

    @Nested
    private class DoNotMixBddandTddTest {
      private Predicate<String> checkPattern

      @BeforeEach
      public void beforeEach() {
        final GPathResult ruleXmlNode = CheckstyleTestUtils
          .findNodeByTypeAndId(checkerChildrenNodes, 'RegexpMultiline', 'DoNotMixBDDandTDD')
        checkPattern = Pattern.compile(
          CheckstyleTestUtils.obtainChildValue(ruleXmlNode, 'format'),
          Pattern.MULTILINE).asPredicate()
      }

      @Test
      public void shouldFindPatternWhenUsingBoth() {
        assertTrue(checkPattern.test('\u006Frg.mockito.BDDMockito org.mockito\u002EMockito'))
      }

      @Test
      public void shouldFindPatternWhenUsingBothReverse() {
        assertTrue(checkPattern.test('\u006Frg.mockito.Mockito org.mockito\u002EBDDMockito'))
      }

      @Test
      public void shouldFindPatternWhenUsingBothImport() {
        assertTrue(checkPattern.test('''

        import \u006Frg.mockito.Mockito;
        import org.mockito\u002EBDDMockito;

        '''))
      }

      @Test
      public void shouldFindPatternWhenUsingBothStaticImport() {
        assertTrue(checkPattern.test('''

        import static \u006Frg.mockito.Mockito.some;
        import static org.mockito\u002EBDDMockito.some;

        '''))
      }

      @Test
      public void shouldNotFindPatternWhenUsingOnlyBddMockito() {
        assertFalse(checkPattern.test('\u006Frg.mockito.BDDMockito'))
      }

      @Test
      public void shouldNotFindPatternWhenUsingOnlyMockito() {
        assertFalse(checkPattern.test('\u006Frg.mockito.Mockito'))
      }
    }

    @Nested
    private class PreferBddTestingTest {
      private Predicate<String> checkPattern

      @BeforeEach
      public void beforeEach() {
        final GPathResult ruleXmlNode = CheckstyleTestUtils
          .findNodeByTypeAndId(checkerChildrenNodes, 'RegexpMultiline', 'PreferBDDTesting')
        checkPattern = Pattern.compile(
          CheckstyleTestUtils.obtainChildValue(ruleXmlNode, 'format'),
          Pattern.MULTILINE).asPredicate()
      }

      @Test
      public void shouldFindPatternWhenImportingMockito() {
        assertTrue(checkPattern.test('\u006Frg.mockito.Mockito'))
      }

      @Test
      public void shouldFindPatternWhenUsingMockitoDoReturn() {
        assertTrue(checkPattern.test('\u004Dockito\u002EdoReturn('))
      }

      @Test
      public void shouldFindPatternWhenUsingDoReturn() {
        assertTrue(checkPattern.test('\u0064oReturn('))
      }

      @Test
      public void shouldFindPatternWhenUsingDoReturnWithSpace() {
        assertTrue(checkPattern.test('\u0064oReturn ('))
      }

      @Test
      public void shouldFindPatternWhenUsingDoAnswer() {
        assertTrue(checkPattern.test('\u0064oAnswer('))
      }

      @Test
      public void shouldFindPatternWhenUsingDoAnswerWithSpace() {
        assertTrue(checkPattern.test('\u0064oAnswer ('))
      }

      @Test
      public void shouldFindPatternWhenUsingDoNothing() {
        assertTrue(checkPattern.test('\u0064oNothing('))
      }

      @Test
      public void shouldFindPatternWhenUsingDoNothingWithSpace() {
        assertTrue(checkPattern.test('\u0064oNothing ('))
      }

      @Test
      public void shouldFindPatternWhenUsingDoThrow() {
        assertTrue(checkPattern.test('\u0064oThrow('))
      }

      @Test
      public void shouldFindPatternWhenUsingDoThrowWithSpace() {
        assertTrue(checkPattern.test('\u0064oThrow ('))
      }

      @Test
      public void shouldFindPatternWhenUsingDoCallRealMethod() {
        assertTrue(checkPattern.test('\u0064oCallRealMethod('))
      }

      @Test
      public void shouldFindPatternWhenUsingDoCallRealMethodWithSpace() {
        assertTrue(checkPattern.test('\u0064oCallRealMethod ('))
      }

      @Test
      public void shouldFindPatternWhenUsingVerifyMethod() {
        assertTrue(checkPattern.test('\u0076erify('))
      }

      @Test
      public void shouldFindPatternWhenUsingVerifyWithSpace() {
        assertTrue(checkPattern.test('\u0076erify ('))
      }

      @Test
      public void shouldNotFindPatternWhenBddMockito() {
        assertFalse(checkPattern.test('\u006Frg.mockito.BDDMockito'))
      }
    }
  }
}
