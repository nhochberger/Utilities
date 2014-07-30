package hochberger.utilities.text;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.google.common.collect.Iterables;

public class TextTest {

    @Test
    public void toIterable() {
        String text = "a, b, c";
        Iterable<String> result = Text.toIterable(text, ", ");
        assertEquals(3, Iterables.size(result));
        result = Text.toIterable(text, " , ");
        assertEquals(1, Iterables.size(result));
    }

}
