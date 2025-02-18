package assignment.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class XDate {
	static SimpleDateFormat fmt = new SimpleDateFormat();
	
	public static Date parse(String text, String pattern) {
		try {
			fmt.applyPattern(pattern);
			return fmt.parse(text);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static String format(Date date, String pattern) {
		fmt.applyPattern(pattern);
		return fmt.format(date);
	}
}
