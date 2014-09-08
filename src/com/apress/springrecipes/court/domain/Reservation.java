package com.apress.springrecipes.court.domain;

import java.util.Date;

public class Reservation {

	private String courtName;
	private Date date;
	private int hour;
	private Player player;
	private SportType sportType;
	
	public Reservation(){
		
	}

	public Reservation(String courtName, Date date, int hour, Player player,
			SportType sportType) {
		this.courtName = courtName;
		this.date = date;
		this.hour = hour;
		this.player = player;
		this.sportType = sportType;
	}

	/**
	 * @return the courtName
	 */
	public String getCourtName() {
		return courtName;
	}

	/**
	 * @param courtName
	 *            the courtName to set
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
	 * @param date
	 *            the date to set
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
	 * @param hour
	 *            the hour to set
	 */
	public void setHour(int hour) {
		this.hour = hour;
	}

	/**
	 * @return the player
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * @param player
	 *            the player to set
	 */
	public void setPlayer(Player player) {
		this.player = player;
	}

	/**
	 * @return the sportType
	 */
	public SportType getSportType() {
		return sportType;
	}

	/**
	 * @param sportType
	 *            the sportType to set
	 */
	public void setSportType(SportType sportType) {
		this.sportType = sportType;
	}

}
