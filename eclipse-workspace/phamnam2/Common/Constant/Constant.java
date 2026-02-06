package Constant;

import java.time.format.DateTimeFormatter;

import org.openqa.selenium.WebDriver;


public class Constant {

		public static WebDriver WEBDRIVER;
		public static final String RAILWAY_URL = "http://saferailway.somee.com";
		public static final String VALID_USERNAME = "phamnam@sharklasers.com";
		public static final String VALID_PASSWORD = "11111111";
		
		public static final String GUERRILLA_URL = "https://www.guerrillamail.com/";
		public static final String USERNAME_HASNT_ACTIVATED = "phamnam02@sharklasers.com";
		public static final String PASSWORD_HASNT_ACTIVATED = "11111111";

		public static final int WAIT_TIMEOUT = 20;
		public static final DateTimeFormatter DATE_FORMAT =
	            DateTimeFormatter.ofPattern("M/d/yyyy");

		
		public static final String ALPHA_NUMERIC = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		
		
}
