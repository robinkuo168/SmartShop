package tw.com.pershing.web.error;

public final class PositionAlreadyExistException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public PositionAlreadyExistException(final String message) {
        super(message);
    }
	
}
