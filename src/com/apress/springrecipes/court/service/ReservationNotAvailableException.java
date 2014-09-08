package com.apress.springrecipes.court.service;

import java.util.Date;

public class ReservationNotAvailableException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1267566899561615386L;
	private String courtName;
	private Date date;
	private int hour;
	
	public ReservationNotAvailableException(){
		
	}
	
	public ReservationNotAvailableException(String courtName, Date date, int hour){
		this.courtName = courtName;
		this.date = date;
		this.hour = hour;
	}
	
	/**
	 * @return the courtName
	 */
	public String getCourtName() {
		return courtName;
	}
	/**
	 * @param courtName the courtName to set
	 */
	public void setCourtName(String courtName) {
		this.courtName = courtName;
	}
	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}
	/**
	 * @return the hour
	 */
	public int getHour() {
		return hour;
	}
	/**
	 * @param hour the hour to set
	 */
	public void setHour(int hour) {
		this.hour = hour;
	}
	
	
}