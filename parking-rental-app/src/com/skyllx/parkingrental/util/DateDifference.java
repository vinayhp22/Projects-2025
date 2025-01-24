package com.skyllx.parkingrental.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DateDifference {

	public DateDifference() {
		log.info("Created DateDifference");
	}

	public static long findDaysDifference(String createdDate, String currentDate) {
		log.info("runngin findDaysDifference()");
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm aa");

		try {
			// Use parse method to get date object of both dates
			
			Date date1 = dateFormat.parse(createdDate);
			Date date2 = dateFormat.parse(currentDate);
			
			log.info("DateDifference: date1: "+date1);
			log.info("DateDifference: date2: "+date2);

			// Calucalte time difference in milliseconds
			long time_difference = date2.getTime() - date1.getTime();

			// Calucalte time difference in days using TimeUnit class
			long days_difference = TimeUnit.MILLISECONDS.toDays(time_difference) % 365;

			System.out.print("Difference between two dates is: ");
			System.out.println(days_difference + " days");
			return days_difference;
		}
		// Catch parse exception
		catch (ParseException excep) {
			excep.printStackTrace();
			return 0;
		}
	}
}
