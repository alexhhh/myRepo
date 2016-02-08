package com.intern.alexx.services.impl;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.intern.alexx.model.Mester;
import com.intern.alexx.repository.MesterRepository;


public class MyPage {

	private List<Mester> contentPage ;
	private Integer totalRezults;
	private Integer pageSize;
	private Integer pageNumber;
	
	
	
	/**
	 * @return the contentPage
	 */
	public List<Mester> getContentPage() {
		return contentPage;
	}
	/**
	 * @param contentPage the contentPage to set
	 */
	public void setContentPage(List<Mester> contentPage) {
		this.contentPage = contentPage;
	}
	/**
	 * @return the totalRezults
	 */
	public Integer getTotalRezults() {
		return totalRezults;
	}
	/**
	 * @param totalRezults the totalRezults to set
	 */
	public void setTotalRezults(Integer totalRezults) {
		this.totalRezults = totalRezults;
	}
	/**
	 * @return the pageSize
	 */
	public Integer getPageSize() {
		return pageSize;
	}
	/**
	 * @param pageSize the pageSize to set
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
	 * @param pageNumber the pageNumber to set
	 */
	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}
	
	 


	

 

 
}
