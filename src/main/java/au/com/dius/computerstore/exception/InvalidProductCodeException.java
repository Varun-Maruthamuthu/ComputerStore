package au.com.dius.computerstore.exception;

public class InvalidProductCodeException extends IllegalArgumentException {

	private static final long serialVersionUID = 1L;

	public InvalidProductCodeException(String message) {
		super(message);
	}

	public InvalidProductCodeException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
