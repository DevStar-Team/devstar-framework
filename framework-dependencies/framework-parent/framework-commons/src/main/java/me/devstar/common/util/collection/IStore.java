package me.devstar.common.util.collection;

import java.io.Serializable;
import java.util.List;

/**
 * 데이터 저장소 객체
 * @author sudden(sch0718@naver.com)
 */
public interface IStore extends List<IRecord>, Jsonable, Serializable {

	/**
	 * 오름차순 정렬방향
	 */
	public static int SORT_DIRECTION_ASC = 1;

	/**
	 * 내림차순 정렬방향
	 */
	public static int SORT_DIRECTION_DESC = -1;

	/**
	 * index 행에 IRecord 를 추가한다.
	 * @param index  인덱스
	 * @param record 데이터 레코드
	 * @throws IndexOutOfBoundsException
	 */
	public void add(int index, IRecord record) throws IndexOutOfBoundsException;

	/**
	 * columnName 을 이름으로 갖는 컬럼이 value 값을 가진 레코드를 검색하여 가장 첫번째 검색되는 인덱스를 리턴한다. 찾지못하면 -1을 리턴한다.
	 * @param columnName 검색대상 컬럼 이름
	 * @param value      검색 값
	 * @return 인덱스
	 */
	public int find(String columnName, Object value);

	/**
	 * startIndex 부터 마지막 인덱스까지의 레코드를 IStore 객체로 리턴한다.
	 * @param startIndex 시작 인덱스
	 * @return 해당 범위의 레코드가 들어있는 IStore 객체
	 * @throws IndexOutOfBoundsException
	 */
	public IStore getRange(int startIndex) throws IndexOutOfBoundsException;

	/**
	 * startIndex 부터 endIndex 까지의 레코드를 IStore 객체로 리턴한다.
	 * @param startIndex 시작 인덱스
	 * @param endIndex   끝 인덱스
	 * @return 해당 범위의 데이터가 들어있는 IStore 객체
	 * @throws IndexOutOfBoundsException
	 */
	public IStore getRange(int startIndex, int endIndex) throws IndexOutOfBoundsException;

	/**
	 * 리스트의 다음 인덱스에 해당하는 IRecord 가 있는지 여부를 리턴한다.
	 * @return 다음 인덱스가 있는지 여부
	 */
	public boolean hasNext();

	/**
	 * 리스트의 이전 인덱스에 해댱하는 IRecord 가 있는지 여부를 리턴한다.
	 * @return 이전 인덱스가 있는지 여부
	 */
	public boolean hasPrev();

	/**
	 * 인덱스를 처음으로 이동시킨다.
	 */
	public void moveFirst();

	/**
	 * 인덱스를 마지막으로 이동시킨다.
	 */
	public void moveLast();

	/**
	 * 리스트의 인덱스를 하나 증가시키고, 현재 인덱스에 해당하는 IRecord 를 리턴한다.
	 * @return 현재 인덱스에 해당하는 IRecord
	 */
	public IRecord next();

	/**
	 * 리스트의 인덱스를 하나 감소시키고, 현재 인덱스에 해당하는 IRecord 를 리턴한다.
	 * @return 현재 인덱스에 해당하는 IRecord
	 */
	public IRecord prev();

	/**
	 * columnName 을 이름으로 갖는 컬럼이 value 값을 가진 레코드를 검색하여 IStore 객체로 리턴한다.
	 * @param columnName 검색대상 컬럼 이름
	 * @param value      검색 값
	 * @return 검색된 레코드가 들어있는 IStore 객체
	 */
	public IStore query(String columnName, Object value);

	/**
	 * 필터를 통하여 검색된 레코드를 IStore 객체로 리턴한다.
	 * @param filter 검색 필터
	 * @return 검색된 레코들가 들어있는 IStore 객체
	 */
	public IStore queryBy(IStoreFilter filter);

	/**
	 * 정렬조건을 설정한다.
	 * @param columnName 정렬할 대상 컬럼의 이름
	 * @param direction  정렬한 방향
	 * @throws UnsupportedSortDirectionException
	 */
	public void setSortConditions(String columnName, int direction) throws UnsupportedSortDirectionException;

	/**
	 * 설정된 정렬조건으로 정렬한다.
	 * @throws UnsupportedSortDirectionException
	 */
	public void sort() throws UnsupportedSortDirectionException;
}
