package com.lti.bean;

import java.util.Date;

public class Semester {
	int id;
	Date startDate;
	Date endDate;
	Date startDateAddDrop;
	Date lastDateAddDrop;

	public Date getStartDateAddDrop() {
		return startDateAddDrop;
	}
	public void setStartDateAddDrop(Date startDateAddDrop) {
		this.startDateAddDrop = startDateAddDrop;
	}
	public Date getLastDateAddDrop() {
		return lastDateAddDrop;
	}
	public void setLastDateAddDrop(Date lastDateAddDrop) {
		this.lastDateAddDrop = lastDateAddDrop;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

}
