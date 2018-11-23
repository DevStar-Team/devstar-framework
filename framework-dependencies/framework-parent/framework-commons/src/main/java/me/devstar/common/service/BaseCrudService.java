package me.devstar.common.service;

import org.springframework.data.domain.Page;

/**
 * <pre>
 * 기본 CRUD 서비스
 * </pre>
 */
public interface BaseCrudService<T, F, ID> {

	/**
	 * 개체를 생성한다.
	 * @param t
	 * @return
	 */
	public T create(T t);

	/**
	 * 개체를 가져온다.
	 * @param id
	 * @return
	 */
	public T get(final ID id);

	/**
	 * 목록을 가져온다.
	 * @param searchForm
	 * @return
	 */
	public Page<T> getList(final F searchForm);

	/**
	 * 개체를 수정한다.
	 * @param t
	 * @return
	 */
	public T modify(T t);

	/**
	 * 개체를 수정한다.
	 * @param t
	 * @param currentUserId 현재 사용자 ID
	 * @return
	 */
	public T modify(T t, final String currentUserId);

	/**
	 * <pre>
	 * 개체를 논리적으로 삭제한다.
	 * </pre>
	 * 
	 * @param id
	 */
	public void remove(final ID id);

	/**
	 * <pre>
	 * 개체를 논리적으로 삭제한다.
	 * </pre>
	 * 
	 * @param id
	 * @param currentUserId 현재 사용자 ID
	 */
	public void remove(final ID id, final String currentUserId);

	/**
	 * <pre>
	 * 복수의  개체를 논리적으로 삭제한다.
	 * </pre>
	 * 
	 * @param iter
	 * @see #remove(Iterable, boolean)
	 */
	public void remove(final Iterable<T> iter);

	/**
	 * <pre>
	 * 복수의  개체를 논리적으로 삭제한다.
	 * </pre>
	 * 
	 * @param iter
	 * @param currentUserId 현재 사용자 ID
	 * @see #remove(Iterable, boolean)
	 */
	public void remove(final Iterable<T> iter, final String currentUserId);

	/**
	 * <pre>
	 * 개체를 물리적으로 삭제한다.
	 * </pre>
	 * 
	 * @param id
	 */
	public void removeByPhysical(final ID id);

	/**
	 * <pre>
	 * 개체를 물리적으로 삭제한다.
	 * </pre>
	 * 
	 * @param id
	 * @param currentUserId 현재 사용자 ID
	 */
	public void removeByPhysical(final ID id, final String currentUserId);

	/**
	 * <pre>
	 * 복수의 개체를 물리적으로 삭제한다.
	 * </pre>
	 * 
	 * @param iter
	 */
	public void removeByPhysical(final Iterable<T> iter);

	/**
	 * <pre>
	 * 복수의 개체를 물리적으로 삭제한다.
	 * </pre>
	 * 
	 * @param iter
	 * @param currentUserId 현재 사용자 ID
	 */
	public void removeByPhysical(final Iterable<T> iter, final String currentUserId);
}
