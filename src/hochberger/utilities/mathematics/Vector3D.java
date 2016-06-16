package hochberger.utilities.mathematics;

import hochberger.utilities.text.Text;

public class Vector3D {

    private final double x;
    private final double y;
    private final double z;

    public Vector3D(final double x, final double y, final double z) {
        super();
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public double getZ() {
        return this.z;
    }

    public Vector3D multiply(final double multiplicator) {
        return new Vector3D(getX() * multiplicator, getY() * multiplicator,
                getZ() * multiplicator);
    }

    public Vector3D normalizedVector() {
        return createNormalized(getX(), getY(), getZ());
    }

    public static Vector3D createNormalized(final double x, final double y,
            final double z) {
        final double normalizationFactor = 1d
                / (Math.sqrt(x * x + y * y + z * z));
        return new Vector3D(normalizationFactor * x, normalizationFactor * y,
                normalizationFactor * z);
    }

    @Override
    public String toString() {
        return "[" + getX() + Text.space() + getY() + Text.space() + getZ()
                + "]";
    }

    public double vectorProduct(final Vector3D other) {
        return getX() * other.getX() + getY() * other.getY()
                + getZ() * other.getZ();
    }

    public double absoluteValue() {
        return Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
    }

    public double angleTo(final Vector3D other) {
        return Math.toDegrees(Math.acos(vectorProduct(other)
                / (absoluteValue() * other.absoluteValue())));
    }
}
