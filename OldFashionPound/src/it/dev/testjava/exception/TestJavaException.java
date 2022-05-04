package it.dev.testjava.exception;

public class TestJavaException extends Exception {

	private static final long serialVersionUID = 1L;

	public TestJavaException() {
		super();
	}

	public TestJavaException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public TestJavaException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public TestJavaException(final String message) {
		super(message);
	}

	public TestJavaException(final Throwable cause) {
		super(cause);
	}
}