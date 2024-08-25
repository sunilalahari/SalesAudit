
package com.sephora.salesaudit.common;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * This class creates the unique name on run time on the basis of system's current date and time.
 * 
 */
public class CreateUniqueNameOnRunTime {
	
	/**
	 * This method creates a unique name which can be used anywhere like search creation, report creation, output file creation etc.
	 * @param prefix prefix will be supplied by the user to make differentiation of the unique name with other users.
	 * @param browser This will identifies the name of the browser on which creation process of unique name is occurred. 
	 * @return this will returns the unique name in String to the caller.
	 */
	@Deprecated
	public static String GetUniqueName(String prefix, String browser) {
		Calendar cal = Calendar.getInstance();
		String dayInTwoDigit = null;
		String monthInTwoDigit = null;
		String hoursInTwoDigit = null;
		String minutesInTwoDigit = null;
		String secondsInTwoDigit = null;
		int day = cal.get(Calendar.DATE);
		if (day < 10) {
			dayInTwoDigit = "0" + day;
		} else {
			dayInTwoDigit = Integer.toString(day);
		}
		int month = cal.get(Calendar.MONTH) + 1;
		if (month < 10) {
			monthInTwoDigit = "0" + month;
		} else {
			monthInTwoDigit = Integer.toString(month);
		}
		int year = cal.get(Calendar.YEAR);
		int hours = cal.get(Calendar.HOUR);
		if (hours < 10) {
			hoursInTwoDigit = "0" + hours;
		} else {
			hoursInTwoDigit = Integer.toString(hours);
		}
		int minutes = cal.get(Calendar.MINUTE);
		if (minutes < 10) {
			minutesInTwoDigit = "0" + minutes;
		} else {
			minutesInTwoDigit = Integer.toString(minutes);
		}
		int seconds = cal.get(Calendar.SECOND);
		if (seconds < 10) {
			secondsInTwoDigit = "0" + seconds;
		} else {
			secondsInTwoDigit = Integer.toString(seconds);
		}
		return prefix + "_" + browser + "_" + dayInTwoDigit + "" + monthInTwoDigit + "" + year + "_"
				+ hoursInTwoDigit + "" + minutesInTwoDigit + ""
				+ secondsInTwoDigit;
	}
	
	/**
	 * This Function creates a Unique name based on Current Date/Time
	 * @return String Unique Name
	 */
	public static String createUniqueName()
	{
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("MMdd_HHmmss");
		String formattedDate = sdf.format(date);
		return formattedDate;
	}
} // End Class CreateUniqueNameOnRunTime
