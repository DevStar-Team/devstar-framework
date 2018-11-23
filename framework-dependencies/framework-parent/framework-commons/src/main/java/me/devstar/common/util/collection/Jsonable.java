package me.devstar.common.util.collection;

/**
 * JSON 형태 변환 인터페이스
 * @author sudden(sch0718@naver.com)
 */
public interface Jsonable {

	/**
	 * Object를 JSON 문자열 형태로 리턴한다.
	 * @return
	 */
	public String toJson();
}
