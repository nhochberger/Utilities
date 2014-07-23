package hochberger.utilities.application.parameter;

public class ParameterException extends Exception {

    private static final long serialVersionUID = 5828046319625145432L;

    public ParameterException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public ParameterException(final String message) {
        super(message);
    }
}
