package com.flamingo.object;

/**
 * Time Encapsulation and Manipulation object used for the Timer Ticker.
 * @author Chris Eggison (http://flamin.co)
 *
 */
public class Timertron {
	// If you get the Timertron reference, good on you.
	private long targetTime = 0L;
	private long targetDays = 0L;
	private long targetHours = 0L;
	private long targetMinutes = 0L;
	
	/**
	 * How long a day is in milliseconds
	 */
	public static final long DAY_MILLIS = 86400000L;
	
	/**
	 * How long an hour is in milliseconds
	 */
	public static final long HOUR_MILLIS = 3600000L;
	
	/**
	 * How long a minute is in milliseconds
	 */
	public static final long MINUTE_MILLIS = 60000L;
	
	public Timertron() {
		
	}
	
	/**
	 * Create a new instance of Timertron and calculate the difference between the current system time
	 * and the specified target time.
	 * @param targetTime
	 */
	public Timertron(long targetTime) {
		this.targetTime = targetTime;
		
		calculateDifference();
	}
	
	/**
	 * Performs time differential operation on the given targetTime and the current system time.
	 * To get output, use getTarget[Time]() methods.
	 * 
	 * Note: If the targetTime is less than the current system time, you will only get 0's back.
	 */
	public void calculateDifference() {
		long currentTime = System.currentTimeMillis();
		if (currentTime < targetTime) {
			long diff = targetTime - currentTime;
			
			targetDays = diff / DAY_MILLIS;
			if (targetDays > 0) {
				diff -= targetDays * DAY_MILLIS;
			} else {
				targetDays = 0;
			}
			
			targetHours = diff / HOUR_MILLIS;
			if (targetHours > 0) {
				diff -= targetHours * HOUR_MILLIS;
			} else {
				targetHours = 0;
			}
			
			targetMinutes = diff / MINUTE_MILLIS;
			if (targetMinutes > 0) {
				diff -= targetMinutes * MINUTE_MILLIS;
			} else {
				targetMinutes = 0;
			}
		} else {
			targetDays = 0;
			targetHours = 0;
			targetMinutes = 0;
		}
	}

	public long getTargetTime() {
		return targetTime;
	}

	public void setTargetTime(long targetTime) {
		this.targetTime = targetTime;
	}

	public long getTargetDays() {
		return targetDays;
	}

	public long getTargetHours() {
		return targetHours;
	}

	public long getTargetMinutes() {
		return targetMinutes;
	}
}
