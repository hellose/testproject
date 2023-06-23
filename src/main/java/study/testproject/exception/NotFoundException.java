package study.testproject.exception;

public class NotFoundException extends RuntimeException {

	private static final long serialVersionUID = -9156520366072437524L;

	public NotFoundException() {
		super();
	}

	protected NotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public NotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public NotFoundException(String message) {
		super(message);
	}

	public NotFoundException(Throwable cause) {
		super(cause);
	}

}