package study.testproject.exception;

public class NotValidDataException extends RuntimeException {

	private static final long serialVersionUID = -8054774292231352891L;

	public NotValidDataException(String message, Throwable cause) {
		super(message, cause);
	}

	public NotValidDataException(Throwable cause) {
		super(cause);
	}

	public NotValidDataException(String message) {
		super(message);
	}

	public NotValidDataException() {
		super("유효하지 않은 데이터입니다.");
	}

}
