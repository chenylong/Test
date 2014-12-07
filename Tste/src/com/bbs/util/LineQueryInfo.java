package com.bbs.util;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

public class LineQueryInfo extends QueryInfo implements Serializable {
	private int maxPrice = 0;
	private int minPrice = 0;
	private int maxDays  = 0;
	private int minDays  = 0;
	private int maxPriceInit = 0;
	private int minPriceInit = 0;
	private int maxDaysInit = 0;
	private int minDaysInit  = 0;
	private String start = "";
	private String minStartTime;
	private String maxStartTime;
	private String minEndTime;
	private String maxEndTime;
	private String minStartTimeInit;
	private String maxStartTimeInit;
	private String minEndTimeInit;
	private String maxEndTimeInit;
	private String departureDate = "";
	private String destination = "";
	private List publicors = new ArrayList();
	private List publicorsInit = new ArrayList();
	private List mainClass = new ArrayList();
	private List subClass = new ArrayList();
	private boolean inadsearch = false;
	
	public LineQueryInfo(){
		
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public int getMaxDays() {
		return maxDays;
	}

	public void setMaxDays(int maxDays) {
		this.maxDays = maxDays;
	}

	public int getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(int maxPrice) {
		this.maxPrice = maxPrice;
	}

	public int getMinDays() {
		return minDays;
	}

	public void setMinDays(int minDays) {
		this.minDays = minDays;
	}

	public int getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(int minPrice) {
		this.minPrice = minPrice;
	}

	public List getPublicors() {
		return publicors;
	}

	public void setPublicors(List publicors) {
		this.publicors = publicors;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public boolean isInadsearch() {
		return inadsearch;
	}

	public void setInadsearch(boolean inadsearch) {
		this.inadsearch = inadsearch;
	}

	public int getMaxDaysInit() {
		return maxDaysInit;
	}

	public void setMaxDaysInit(int maxDaysInit) {
		this.maxDaysInit = maxDaysInit;
	}

	public int getMaxPriceInit() {
		return maxPriceInit;
	}

	public void setMaxPriceInit(int maxPriceInit) {
		this.maxPriceInit = maxPriceInit;
	}

	public int getMinDaysInit() {
		return minDaysInit;
	}

	public void setMinDaysInit(int minDaysInit) {
		this.minDaysInit = minDaysInit;
	}

	public int getMinPriceInit() {
		return minPriceInit;
	}

	public void setMinPriceInit(int minPriceInit) {
		this.minPriceInit = minPriceInit;
	}

	public List getPublicorsInit() {
		return publicorsInit;
	}

	public void setPublicorsInit(List publicorsInit) {
		this.publicorsInit = publicorsInit;
	}

	public String getMaxEndTime() {
		return maxEndTime;
	}

	public void setMaxEndTime(String maxEndTime) {
		this.maxEndTime = maxEndTime;
	}

	public String getMaxStartTime() {
		return maxStartTime;
	}

	public void setMaxStartTime(String maxStartTime) {
		this.maxStartTime = maxStartTime;
	}

	public String getMinEndTime() {
		return minEndTime;
	}

	public void setMinEndTime(String minEndTime) {
		this.minEndTime = minEndTime;
	}

	public String getMinStartTime() {
		return minStartTime;
	}

	public void setMinStartTime(String minStartTime) {
		this.minStartTime = minStartTime;
	}

	public String getMaxEndTimeInit() {
		return maxEndTimeInit;
	}

	public void setMaxEndTimeInit(String maxEndTimeInit) {
		this.maxEndTimeInit = maxEndTimeInit;
	}

	public String getMaxStartTimeInit() {
		return maxStartTimeInit;
	}

	public void setMaxStartTimeInit(String maxStartTimeInit) {
		this.maxStartTimeInit = maxStartTimeInit;
	}

	public String getMinEndTimeInit() {
		return minEndTimeInit;
	}

	public void setMinEndTimeInit(String minEndTimeInit) {
		this.minEndTimeInit = minEndTimeInit;
	}

	public String getMinStartTimeInit() {
		return minStartTimeInit;
	}

	public void setMinStartTimeInit(String minStartTimeInit) {
		this.minStartTimeInit = minStartTimeInit;
	}

	public String getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(String departureDate) {
		this.departureDate = departureDate;
	}

	public List getMainClass() {
		return mainClass;
	}

	public void setMainClass(List mainClass) {
		this.mainClass = mainClass;
	}

	public List getSubClass() {
		return subClass;
	}

	public void setSubClass(List subClass) {
		this.subClass = subClass;
	}
	
	
}
