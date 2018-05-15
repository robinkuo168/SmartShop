package tw.com.pershing.web.error;

public class PasswordNotMatchException extends RuntimeException{
	private static final long serialVersionUID = 5861310537366287163L;
	
	public PasswordNotMatchException(){
		super();
	}
	
	public PasswordNotMatchException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public PasswordNotMatchException(final String message) {
        super(message);
    }

    public PasswordNotMatchException(final Throwable cause) {
        super(cause);
    }
}
