package au.com.dius.computerstore.exception;

public class EmptyCartException extends Exception {

	private static final long serialVersionUID = 1L;

	public EmptyCartException(String message) {
		super(message);
	}

	public EmptyCartException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
