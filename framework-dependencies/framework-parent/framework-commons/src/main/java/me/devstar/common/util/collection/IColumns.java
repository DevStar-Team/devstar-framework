package me.devstar.common.util.collection;

/**
 * DB의 컬럼정보 인터페이스
 * @author sudden(sch0718@naver.com)
 */
public interface IColumns extends Iterable<String> {

	/**
	 * 컬럼을 추가한다.
	 * @param columnName 컬럼 이름
	 * @param columnType 컬럼타입 객체
	 */
	public void addColumn(String columnName, IColumnType columnType);

	/**
	 * 컬럼을 추가한다.
	 * @param columnName 컬럼 이름
	 * @param columnType 컬럼타입 객체
	 * @param maxLength  최대크기
	 */
	public void addColumn(String columnName, IColumnType columnType, int maxLength);

	/**
	 * columnName 에 해당하는 컬럼 인덱스를 리턴한다.
	 * @param columnName 컬럼 이름
	 * @return 컬럼 인덱스
	 */
	public int getColumnIndex(String columnName);

	/**
	 * columnIndex 에 해당하는 컬럼의 이름을 리턴한다.
	 * @param columnIndex 컬럼 인덱스
	 * @return 컬럼 이름
	 * @throws IndexOutOfBoundsException
	 */
	public String getColumnName(int columnIndex) throws IndexOutOfBoundsException;

	/**
	 * columnIndex 에 해당하는 컬럼의 최대크기를 리턴한다.
	 * @param columnIndex 컬럼 인덱스
	 * @return 최대크기
	 */
	public int getMaxLength(int columnIndex);

	/**
	 * columnName 에 해당하는 컬럼의 최대크기를 리턴한다.
	 * @param columnName 컬럼 이름
	 * @return 최대크기
	 */
	public int getMaxLength(String columnName);

	/**
	 * 컬럼의 갯수를 리턴한다.
	 * @return 컬럼 갯수
	 */
	public int getSize();

	/**
	 * columnIndex 에 해당하는 컬럼타입을 IColumnType 로 리턴한다.
	 * @param columnIndex 컬럼 인덱스
	 * @return 컬럼타입 객체
	 */
	public IColumnType getType(int columnIndex);

	/**
	 * columnName 에 해당하는 컬럼타입을 IColumnType 로 리턴한다.
	 * @param columnName 컬럼 이름
	 * @return 컬럼타입 객체
	 */
	public IColumnType getType(String columnName);

	/**
	 * 컬럼을 삭제한다.
	 * @param columnName 컬럼 이름
	 */
	public void removeColumn(String columnName);
}
