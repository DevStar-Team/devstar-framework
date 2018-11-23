package me.devstar.common.entity;

import org.springframework.data.domain.Pageable;

import me.devstar.common.util.ToStringUtils;

/**
 * 검색 폼
 */
public class SearchForm {

	public static final String FORM_NAME = "searchForm";

	/**
	 * 검색 키
	 */
	protected String searchKey;

	/**
	 * 검색 값
	 */
	protected String searchValue;

	/**
	 * 논리적으로 삭제된 모든 데이터 조회 여부
	 */
	protected boolean isShowAll;

	/**
	 * 페이지 처리 객체
	 */
	protected Pageable pageable;

	/**
	 * @return the pageable
	 */
	public Pageable getPageable() {
		return pageable;
	}

	/**
	 * @return the searchKey
	 */
	public String getSearchKey() {
		return searchKey;
	}

	/**
	 * @return the searchValue
	 */
	public String getSearchValue() {
		return searchValue;
	}

	/**
	 * @return the isShowAll
	 */
	public boolean isShowAll() {
		return isShowAll;
	}

	/**
	 * @param pageable the pageable to set
	 */
	public void setPageable(Pageable pageable) {
		this.pageable = pageable;
	}

	/**
	 * @param searchKey the searchKey to set
	 */
	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	/**
	 * @param searchValue the searchValue to set
	 */
	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}

	/**
	 * @param isShowAll the isShowAll to set
	 */
	public void setShowAll(boolean isShowAll) {
		this.isShowAll = isShowAll;
	}

	@Override
	public String toString() {
		return ToStringUtils.toString(this);
	}

}
