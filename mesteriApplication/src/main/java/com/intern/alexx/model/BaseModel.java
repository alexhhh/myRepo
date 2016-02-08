package com.intern.alexx.model;

public  class BaseModel {

	private int id;
	

	public BaseModel() {
	}
	
	public BaseModel(int id) {
		this.id=id;
	}
	
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *   the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

}
