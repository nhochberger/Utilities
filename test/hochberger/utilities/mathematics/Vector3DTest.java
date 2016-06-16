package hochberger.utilities.mathematics;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class Vector3DTest {

    private static final double DELTA = 0.0000001;

    @Test
    public void testAbsoluteValue() {
        Vector3D vector = new Vector3D(0, 0, 0);
        assertEquals(0, vector.absoluteValue(), DELTA);
        vector = new Vector3D(1, 1, 1);
        assertEquals(Math.sqrt(3), vector.absoluteValue(), DELTA);
        vector = new Vector3D(1, 0, 0);
        assertEquals(1, vector.absoluteValue(), DELTA);
        vector = new Vector3D(0, 1, 1);
        assertEquals(Math.sqrt(2), vector.absoluteValue(), DELTA);
        vector = new Vector3D(1, 0, 1);
        assertEquals(Math.sqrt(2), vector.absoluteValue(), DELTA);
        vector = new Vector3D(1, 1, 0);
        assertEquals(Math.sqrt(2), vector.absoluteValue(), DELTA);
    }

    @Test
    public void testVectorProduct() {
        final Vector3D vector = new Vector3D(0, 0, 0);
        assertEquals(0, vector.vectorProduct(vector), DELTA);
        final Vector3D vector1 = new Vector3D(1, 2, 3);
        final Vector3D vector2 = new Vector3D(4, 5, 6);
        assertEquals(32, vector1.vectorProduct(vector2), DELTA);
    }

    @Test
    public void testAngleTo() {
        Vector3D vector1 = new Vector3D(1, 0, 0);
        Vector3D vector2 = new Vector3D(0, 1, 0);
        assertEquals(90, vector1.angleTo(vector2), DELTA);
        vector1 = new Vector3D(0, 1, 0);
        vector2 = new Vector3D(0, 0, 1);
        assertEquals(90, vector1.angleTo(vector2), DELTA);
        vector1 = new Vector3D(1, 4, -2);
        vector2 = new Vector3D(-3, 3, 1);
        assertEquals(69.5, vector1.angleTo(vector2), 0.1);
    }
}
