package com.chowis.kyro.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	public static String formatDate(Date date, String pattern) {
		if (null != date) {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat(pattern);

				return sdf.format(date);
			} catch (Exception ex) {
				String message = String.format("Could not parse date %s with pattern %s ", date, pattern);
				throw new IllegalArgumentException(message, ex);
			}
		}

		return null;
	}

}
