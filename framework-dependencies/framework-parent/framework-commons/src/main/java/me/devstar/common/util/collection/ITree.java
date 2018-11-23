package me.devstar.common.util.collection;

import java.io.Serializable;

/**
 * 트리 인터페이스
 * @author sudden(sch0718@naver.com)
 */
public interface ITree extends Jsonable, Serializable {

	/**
	 * 노드를 생성한다.
	 * @return 노드
	 */
	public INode createNode();

	/**
	 * 트리의 모든 노드 중 id를 갖고있는 노드를 리턴한다.
	 * @param id 찾는 노드의 id
	 * @return 노드
	 */
	public INode getNode(String id);

	/**
	 * 루트 노드를 리턴한다.
	 * @return 루트 노드
	 */
	public INode getRootNode();

	/**
	 * 트리를 리로드 한다.
	 */
	public void reload();

	/**
	 * 루트노드를 리턴한다.
	 * @param node 루트 노드
	 */
	public void setRootNode(INode node);

	/**
	 * 트리의 데이터를 JSON 타입으로 리턴한다. (숨김노드 출력)
	 * @return
	 */
	public String toJson();

	/**
	 * 노드의 데이터를 JSON 타입으로 리턴한다.
	 * @param showHiddenNode 숨김노드 출력 여부
	 * @return
	 */
	public String toJson(boolean showHiddenNode);
}
