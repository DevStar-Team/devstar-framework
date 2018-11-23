package me.devstar.common.util.collection;

/**
 * 트리에서 사용하는 필터
 * @author sudden(sch0718@naver.com)
 */
public interface ITreeFilter {

	/**
	 * 해당 노드가 조건에 부합하는지 여부를 리턴한다.
	 * @param node 노드
	 * @return 조건에 부합여부
	 */
	public boolean accept(INode node);
}
