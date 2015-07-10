package hochberger.utilities.application;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class VersionComparatorTest {

    @Test
    public void test() {
        final VersionComparator comparator = new VersionComparator();
        assertEquals(comparator.compare("1.1.1", "1.1.1"), 0);
        assertTrue(comparator.compare("2.1.1", "1.1.1") > 0);
        assertTrue(comparator.compare("1.2.1", "1.1.1") > 0);
        assertTrue(comparator.compare("1.1.2", "1.1.1") > 0);
        assertTrue(comparator.compare("1.1.1", "2.1.1") < 0);
        assertTrue(comparator.compare("1.1.1", "1.2.1") < 0);
        assertTrue(comparator.compare("1.1.1", "1.1.2") < 0);
    }
}
