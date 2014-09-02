package com.apress.springrecipes.court.domain;

public class Member {
	
	private int fname;
	private String lname;
	private String address;
	private String phoneNo;
	
	/**
	 * @return the fname
	 */
	public int getFname() {
		return fname;
	}
	/**
	 * @param fname the fname to set
	 */
	public void setFname(int fname) {
		this.fname = fname;
	}
	/**
	 * @return the lname
	 */
	public String getLname() {
		return lname;
	}
	/**
	 * @param lname the lname to set
	 */
	public void setLname(String lname) {
		this.lname = lname;
	}
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @return the phoneNo
	 */
	public String getPhoneNo() {
		return phoneNo;
	}
	/**
	 * @param phoneNo the phoneNo to set
	 */
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	
}
