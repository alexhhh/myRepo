package com.intern.alexx.model;

import java.util.List;

public class MyPage<T> {

	private List<T> contentPage;
	private Integer totalResults;
	private Integer pageSize;
	private Integer pageNumber;

	public List<T> getContentPage() {
		return contentPage;
	}

	public void setContentPage(List<T> contentPage) {
		this.contentPage = contentPage;
	}

	/**
	 * @return the totalResults
	 */
	public Integer getTotalResults() {
		return totalResults;
	}

	/**
	 * @param totalResults
	 *            the totalResults to set
	 */
	public void setTotalResults(Integer totalResults) {
		this.totalResults = totalResults;
	}

	/**
	 * @return the pageSize
	 */
	public Integer getPageSize() {
		return pageSize;
	}

	/**
	 * @param pageSize
	 *            the pageSize to set
	 */
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * @return the pageNumber
	 */
	public Integer getPageNumber() {
		return pageNumber;
	}

	/**
	 * @param pageNumber
	 *            the pageNumber to set
	 */
	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	@Override
	public String toString() {
		return "MyPage [contentPage=" + contentPage + ", totalResults=" + totalResults + ", pageSize=" + pageSize
				+ ", pageNumber=" + pageNumber + "]";
	}

}
