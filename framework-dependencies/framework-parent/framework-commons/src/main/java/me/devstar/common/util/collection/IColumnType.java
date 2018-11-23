package me.devstar.common.util.collection;

/**
 * DB의 컬럼 타입 인터페이스
 * @author sudden(sch0718@naver.com)
 */
public interface IColumnType {

	/**
	 * 컬럼타입이 obj 와 같은지 여부를 리턴한다.
	 * @param obj 객체
	 * @return 동일여부
	 */
	public boolean equals(Object obj);

	/**
	 * 컬럼의 Class 를 리턴한다.
	 * @return 클래스
	 */
	public Class<?> getColumnClass();
}
