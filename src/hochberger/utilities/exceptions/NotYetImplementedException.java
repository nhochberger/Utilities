package hochberger.utilities.exceptions;

public class NotYetImplementedException extends RuntimeException {

    private static final long serialVersionUID = 1574167603614900509L;

    public NotYetImplementedException() {
        super("The desired method \""
                + new Throwable().getStackTrace()[1].getClassName() + "."
                + new Throwable().getStackTrace()[1].getMethodName()
                + "\" is not yet implemented.");
    }

}
