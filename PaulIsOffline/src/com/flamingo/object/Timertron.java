/*Copyright 2013 Christopher Eggison

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.*/

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

	/**
	 * Returns the current set target time
	 * @return
	 */
	public long getTargetTime() {
		return targetTime;
	}

	/**
	 * Sets the current target time. Remember to call calculateDifference() afterwards.
	 * @param targetTime New Target Time
	 */
	public void setTargetTime(long targetTime) {
		this.targetTime = targetTime;
	}

	/**
	 * Returns the calculated difference in whole days.
	 * @return
	 */
	public long getTargetDays() {
		return targetDays;
	}

	/**
	 * Returns the calculated difference in hours. Does not count past 24 hours.
	 * @return
	 */
	public long getTargetHours() {
		return targetHours;
	}

	/**
	 * Returns the calculated difference in minutes. Does not count past 60 minutes.
	 * @return
	 */
	public long getTargetMinutes() {
		return targetMinutes;
	}
}
