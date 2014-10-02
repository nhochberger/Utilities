package hochberger.utilities.text.i18n;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class I18NFrameworkTest {

    @Test
    public void testDirectI18NSimple() {
        DirectI18N toTest = new DirectI18N("test");
        assertEquals("test", toTest.toString());
    }

    @Test
    public void testDirectI18NWithArguments() {
        DirectI18N toTest = new DirectI18N("test", "a", "b", "c");
        assertEquals("test", toTest.toString());
    }

    @Test
    public void testDirectI18NWithArgumentsResolved() {
        DirectI18N toTest = new DirectI18N("test${0}${1}${2}", "a", "b", "c");
        assertEquals("testabc", toTest.toString());
    }

    @Test
    public void testDirectI18NWithOneArgumentResolved() {
        DirectI18N toTest = new DirectI18N("test${0}${0}", "a", "b");
        assertEquals("testaa", toTest.toString());
    }

    @Test
    public void testDirectI18NWithNoArgumentResolved() {
        DirectI18N toTest = new DirectI18N("test${2}", "a", "b");
        assertEquals("test${2}", toTest.toString());
    }
}
