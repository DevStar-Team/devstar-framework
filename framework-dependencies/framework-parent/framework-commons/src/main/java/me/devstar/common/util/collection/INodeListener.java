package me.devstar.common.util.collection;

/**
 * 노드 리스너 인터페이스
 * @author sudden(sch0718@naver.com)
 */
public interface INodeListener {

	/**
	 * 자식 노드가 추가된 후 실행된다.
	 * @param childNode 추가된 자식 노드
	 */
	public void appendChild(INode childNode);

	/**
	 * 노드가 생성 된 후 실행된다.
	 */
	public void created();

	/**
	 * 노드가 로드된 후 실행된다.
	 */
	public void load();

	/**
	 * 노드가 이동(index 변경)되었을때 실행된다.
	 * @param oldIndex
	 * @param newIndex
	 */
	public void move(int oldIndex, int newIndex);

	/**
	 * 노드가 삭제되기 직전 실행된다.
	 */
	public void remove();

	/**
	 * 자식 노드가 삭제된 후 실행된다.
	 * @param childNode 삭제된 자식 노드
	 */
	public void removeChild(INode childNode);
}
