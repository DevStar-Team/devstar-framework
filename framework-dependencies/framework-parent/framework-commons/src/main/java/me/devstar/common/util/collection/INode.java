package me.devstar.common.util.collection;

import java.util.Comparator;
import java.util.List;

/**
 * 트리 노드 인터페이스
 * @author sudden(sch0718@naver.com)
 */
public interface INode extends Jsonable {

	/**
	 * 리스너를 추가한다.
	 * @param listener
	 */
	public void addListener(INodeListener listener);

	/**
	 * 자식 노드를 마지막 순서로 추가한다.
	 * @param node 추가할 자식 노드
	 * @return 추가된 부모 노드
	 */
	public INode appendChild(INode node);

	/**
	 * 자식 노드를 index 순서로 추가한다. index 이후 순서의 노드들은 순서가 1씩 밀려난다.
	 * @param index 추가할 순서
	 * @param node  추가할 자식 노드
	 * @return 추가된 부모 노드
	 */
	public INode appendChild(int index, INode node);

	/**
	 * 모든 리스너를 삭제한다.
	 */
	public void clearListener();

	/**
	 * node 를 자식 노드로 가지고 있는지 여부를 리턴한다.
	 * @param node 자식 노드
	 * @return 가지고 있는지 여부
	 */
	public boolean contains(INode node);

	/**
	 * id 의 노드를 자식 노드로 가지고 있는지 여부를 리턴한다.
	 * @param id 자식 노드의 id
	 * @return 가지고 있는지 여부
	 */
	public boolean contains(String id);

	/**
	 * 노드를 사용 불가하도록 한다.
	 */
	public void disable();

	/**
	 * 노드를 사용 가능하도록 한다.
	 */
	public void enable();

	/**
	 * 필터의 조건에 맞는 노드를 찾아 리턴한다.
	 * @param filter 필터
	 * @return 찾는 노드 리스트
	 */
	public List<INode> findChild(ITreeFilter filter);

	/**
	 * 속성들을 맵으로 리턴한다.
	 * @return {@link IRecord} 속성 맵
	 */
	public IRecord getAttributeMap();

	/**
	 * 속성의 값을 리턴한다.
	 * @param attrName 속성 이름
	 * @return 속성 값
	 */
	public String getAttributeValue(String attrName);

	/**
	 * index 의 자식 노드를 리턴한다.
	 * @param index 자식 노드의 index
	 * @return
	 */
	public INode getChild(int index);

	/**
	 * id의 자식 노드를 리턴한다.
	 * @param id 자식 노드의 id
	 * @return 자식 노드
	 */
	public INode getChild(String id);

	/**
	 * 자식 노드들을 리턴한다.
	 * @return 자식 노드 리스트
	 */
	public List<INode> getChildren();

	/**
	 * 설명글을 리턴한다.
	 * @return 노드의 설명글
	 */
	public String getComment();

	/**
	 * 노드의 depth 를 리턴한다.
	 * @return 노드의 depth
	 */
	public int getDepth();

	/**
	 * id 를 리턴한다.
	 * @return 노드의 id
	 */
	public String getId();

	/**
	 * index(순서)를 리턴한다. 0부터 시작
	 * @return 노드의 index(순서)
	 */
	public int getIndex();

	/**
	 * 노드의 리스너들을 리턴한다.
	 * @return
	 */
	public List<INodeListener> getListeners();

	/**
	 * 노드를 가지고 있는 트리를 리턴한다.
	 * @return 노드를 가지고 있는 트리
	 */
	public ITree getOwnerTree();

	/**
	 * 부모 노드의 id 를 리턴한다.
	 * @return 부모 노드의 id
	 */
	public String getParentId();

	/**
	 * 부모 노드를 리턴한다.
	 * @return 부모 노드
	 */
	public INode getParentNode();

	/**
	 * text 를 리턴한다.
	 * @return 노드의 text
	 */
	public String getText();

	/**
	 * 자식 노드를 가지고 있는지 여부를 리턴한다.
	 * @return 자식 노드를 가지고 있는지 여부
	 */
	public boolean hasChild();

	/**
	 * 자식노드의 index를 리턴한다.
	 * @param node 자식 노드
	 * @return
	 * @since 1.1
	 */
	public int indexOf(INode node);

	/**
	 * 노드가 사용 가능한지 여부를 리턴한다.
	 * @return 사용 여부
	 */
	public boolean isEnabled();

	/**
	 * 노드가 부모 노드의 첫번째 자식 노드인지 여부를 리턴한다.
	 * @return 첫번째 노드인지 여부
	 */
	public boolean isFirst();

	/**
	 * 노드가 부모 노드의 마지막 자식 노드인지 여부를 리턴한다.
	 * @return 마지막 노드인지 여부
	 */
	public boolean isLast();

	/**
	 * 노드가 자식 노드가 없는지 여부를 리턴한다.
	 * @return 자식 노드가 없는지 여부
	 */
	public boolean isLeaf();

	/**
	 * 루트노드인지 여부를 리턴한다.
	 * @return 루트노드 여부
	 */
	public boolean isRoot();

	/**
	 * 노드의 출력 여부를 리턴한다.
	 * @return 출력 여부
	 */
	public boolean isVisible();

	/**
	 * 노드를 리로드 한다.
	 */
	public void reload();

	/**
	 * 속성을 제거한다.
	 * @param attrName 속성 이름
	 */
	public void removeAttribute(String attrName);

	/**
	 * 자식 노드를 제거한다. 제거된 노드 이후 순서의 노드들은 순서가 1씩 당겨진다.
	 * @param node 제거할 자식 노드
	 * @return 제거된 부모 노드
	 */
	public INode removeChild(INode node);

	/**
	 * index 순서의 자식 노드를 제거한다. 제거된 노드 이후 순서의 노드들은 순서가 1씩 당겨진다.
	 * @param index 제거할 자식 노드의 index
	 * @return 제거된 부모 노드
	 */
	public INode removeChild(int index);

	/**
	 * id의 자식 노드를 제거한다. 제거된 노드 이후 순서의 노드들은 순서가 1씩 당겨진다.
	 * @param id 제거할 자식 노드의 id
	 * @return 제거된 부모 노드
	 */
	public INode removeChild(String id);

	/**
	 * 모든 자식노드들을 제거한다.
	 */
	public void removeChildAll();

	/**
	 * 리스너를 삭제한다.
	 * @param listener
	 */
	public void removeListener(INodeListener listener);

	/**
	 * 리스너를 삭제한다.
	 * @param index
	 */
	public void removeListener(int index);

	/**
	 * 속성을 설정한다.
	 * @param attrName 속성 이름
	 * @param value    속성 값
	 */
	public void setAttribute(String attrName, String value);

	/**
	 * 설명글을 설정한다.
	 * @param comment 노드의 설명글
	 */
	public void setComment(String comment);

	/**
	 * id 를 설정한다.
	 * @param id 노드의 id
	 */
	public void setId(String id);

	/**
	 * index(순서)를 설정한다.
	 * @param index 노드의 index(순서)
	 */
	public void setIndex(int index);

	/**
	 * 부모 노드를 설정한다.
	 * @param parentNode 부모 노드
	 */
	public void setParentNode(INode parentNode);

	/**
	 * text 를 설정한다.
	 * @param text 노드의 text
	 */
	public void setText(String text);

	/**
	 * 노드의 출력 여부를 설정한다.
	 * @param visible 출력 여부
	 */
	public void setVisible(boolean visible);

	/**
	 * 자식 노드의 수를 리턴한다.
	 * @return 자식 노드의 수
	 */
	public int size();

	/**
	 * 자식 노드들을 index 순서로 정렬한다.
	 */
	public void sort();

	/**
	 * 자식 노드들을 comparator 를 이용하여 정렬한다.
	 * @param comparator
	 */
	public void sort(Comparator<INode> comparator);

	/**
	 * 노드의 데이터를 JSON 타입으로 리턴한다. (숨김노드 출력)
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
