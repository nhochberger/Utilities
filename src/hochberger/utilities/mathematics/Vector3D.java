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

    /**
     * @param alpha
     *            - angle by which the vector is rotated around the x-axis
     * @return new vector which is a rotated version of the current one
     */
    public Vector3D rotateVectorX(final double alpha) {
        final double newX = this.x;
        final double newY = this.y * Math.cos(alpha) - this.z * Math.sin(alpha);
        final double newZ = this.y * Math.sin(alpha) + this.z * Math.cos(alpha);
        return new Vector3D(newX, newY, newZ);
    }

    /**
     * @param beta
     *            - angle by which the vector is rotated around the y-axis
     * @return new vector which is a rotated version of the current one
     */
    public Vector3D rotateVectorY(final double beta) {
        final double newX = this.x * Math.cos(beta) + this.z * Math.sin(beta);
        final double newY = this.y;
        final double newZ = -this.x * Math.sin(beta) + this.z * Math.cos(beta);
        return new Vector3D(newX, newY, newZ);
    }

    /**
     * @param gamma
     *            - angle by which the vector is rotated around the z-axis
     * @return new vector which is a rotated version of the current one
     */
    public Vector3D rotateVectorZ(final double gamma) {
        final double newX = this.x * Math.cos(gamma) - this.y * Math.sin(gamma);
        final double newY = this.x * Math.sin(gamma) + this.y * Math.cos(gamma);
        final double newZ = this.z;
        return new Vector3D(newX, newY, newZ);
    }
}
