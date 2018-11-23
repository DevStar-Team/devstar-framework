package me.devstar.common.util.collection;

/**
 * 지원하지 않는 방향으로 정렬하려 할 경우 발생하는 Exception
 * @author sudden(sch0718@naver.com)
 */
public class UnsupportedSortDirectionException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1706655896583865485L;

	/**
	 * 생성자.
	 */
	public UnsupportedSortDirectionException() {
		super();
	}

	/**
	 * 생성자.
	 * @param message
	 */
	public UnsupportedSortDirectionException(String message) {
		super(message);
	}

	/**
	 * 생성자.
	 * @param message
	 * @param cause
	 */
	public UnsupportedSortDirectionException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * 생성자.
	 * @param cause
	 */
	public UnsupportedSortDirectionException(Throwable cause) {
		super(cause);
	}
}
