package tw.com.pershing.web.error;

public final class UserAlreadyExistException extends RuntimeException{
	
	public UserAlreadyExistException(final String message) {
        super(message);
    }
	
}
