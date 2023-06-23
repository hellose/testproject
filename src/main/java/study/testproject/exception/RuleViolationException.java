package study.testproject.exception;

public class RuleViolationException extends RuntimeException {

	private static final long serialVersionUID = -8054774292231352891L;

	public RuleViolationException(String message, Throwable cause) {
		super(message, cause);
	}

	public RuleViolationException(Throwable cause) {
		super(cause);
	}

	public RuleViolationException(String message) {
		super(message);
	}
}
