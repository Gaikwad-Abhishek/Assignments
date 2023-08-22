package com.learning.hello.controller;

public class OdometerController {
	
	
	private static final String DIGITS = "123456789";
	
	private int reading;
	
	private  int getMinReading(int size) {
		return Integer.valueOf(DIGITS.substring(0, size));
	}
	
	private  int getMaxReading(int size) {
		return Integer.valueOf(DIGITS.substring(DIGITS.length() - size, DIGITS.length()));
	}
	
	private  int getSize(int reading) {
		return String.valueOf(reading).length();
	}
	
	public OdometerController(int size) {
		reading = 0;
	}
	
	public OdometerController(OdometerController copy) {
		reading = copy.reading;
	}
	
	public int getReading() {
		return reading;
	}
	
	public void setReading(int reading) {
		if(isAscending(reading))
			this.reading = reading;
		else
			this.reading = 123;
	}
	
	@Override
	public String toString() {
		return "(" + reading + ")";
	}
		
	public static boolean isAscending(int reading) {
		if (reading < 10)
			return true;
		if(reading % 10 <= (reading / 10) % 10)
			return false;
		return isAscending(reading / 10);
	}
	
	public int incrementReading() {
		do {
			if (reading == getMaxReading(getSize(reading)))
				reading = getMinReading(getSize(reading));
			else
				reading++;
		} while (!isAscending(reading));
		return reading;
	}
	
	public OdometerController nextReading() {
		OdometerController temp = new OdometerController(this);
		temp.incrementReading();
		return temp;
	}
	
	public int decrementReading() {
		do {
			if (reading == getMinReading(getSize(reading)))
				reading = getMaxReading(getSize(reading));
			else
				reading--;
		} while (!isAscending(reading));
		
		return reading;
	}
	
	public void reset() {
		this.reading = getMinReading(getSize(this.reading));
	}
	
	public int getSize() {
		return getSize(this.reading);
	}

}
