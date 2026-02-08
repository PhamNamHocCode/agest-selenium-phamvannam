package Constant;

import java.time.format.DateTimeFormatter;

import org.openqa.selenium.WebDriver;


public class Constant {
	
	// WebDriver
    public static WebDriver WEBDRIVER;  
    
    // Base URL
    public static final String RAILWAY_URL = "http://saferailway.somee.com"; 
    
    // Username and Password
    public static final String VALID_USERNAME = "phamnam@sharklasers.com";   
    public static final String VALID_PASSWORD = "11111111";     
    public static final String USERNAME_HASNT_ACTIVATED = "phamnam02@sharklasers.com"; 
    public static final String PASSWORD_HASNT_ACTIVATED = "11111111";
    
    // Mail service
    public static final String GUERRILLA_URL = "https://www.guerrillamail.com/"; 
    

    // Timeout
    public static final int WAIT_TIMEOUT = 20;      
    
    // Date format
    public static final DateTimeFormatter DATE_FORMAT =
            DateTimeFormatter.ofPattern("M/d/yyyy");      

    // Random chars
    public static final String ALPHA_NUMERIC =
            "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"; 

    // Table row
    public static final String ROW_BY_2_COLS =
            "//tr[td[%d][normalize-space()='%s'] and td[%d][normalize-space()='%s']]"; 

}

