package me.devstar.common.util.collection;

/**
 * IStore에서 사용하는 검색 필터 인터페이스
 * @author sudden(sch0718@naver.com)
 */
public interface IStoreFilter {

	/**
	 * 해당 레코드가 검색조건에 부합하는지 여부를 리턴한다.
	 * @param record 레코드 객체
	 * @return 검색조건에 부합여부
	 */
	public boolean accept(IRecord record);
}
