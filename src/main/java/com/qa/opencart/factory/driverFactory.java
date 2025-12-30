package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class driverFactory {

	public WebDriver driver;
	public Properties prop;
	public OptionsManager optionsManger;

	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	// Initialize driver on the basis of browserName

	public WebDriver initDriver(Properties prop) {

		String browserName = prop.getProperty("browser");

		optionsManger = new OptionsManager(prop);

		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			
			
			// driver = new ChromeDriver(optionsManger.getChromeOptions());
			tlDriver.set(new ChromeDriver(optionsManger.getChromeOptions()));
		} else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			// driver = new FirefoxDriver(optionsManger.getFireFoxOptions());
			tlDriver.set(new FirefoxDriver(optionsManger.getFireFoxOptions()));
		} else if (browserName.equalsIgnoreCase("safari")) {
			WebDriverManager.safaridriver().setup();
			// driver = new SafariDriver();
			tlDriver.set(new SafariDriver());
		} else {
			System.out.println("Pass the right browser");
		}

		getDriver().manage().deleteAllCookies();
		getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));
		return getDriver();
	}

	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}

	// Initialize properties from config.properties

	/**
	 * 
	 * @return this return Properties reference with all the config properties
	 */
	public Properties initProp() {

		// Especially designed by java to read prop files/non java files.
		// for global scope
		prop = new Properties();
		FileInputStream ip = null;

		String envName = System.getProperty("env");
		System.out.println("Running Test Cases on environment : " + envName);

//		try {
//			//make the connection with the file defined
//			ip = new FileInputStream("./src/test/resource/config/config.properties");
//			prop.load(ip);
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//			
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		if (envName == null) {
			System.out.println("No env given, hence running the cases on QA env");
			try {
				ip = new FileInputStream("./src/test/resource/config/qa.config.properties");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			try {
				switch (envName) {
				case "qa":
					ip = new FileInputStream("./src/test/resource/config/qa.config.properties");
					break;

				case "stage":
					ip = new FileInputStream("./src/test/resource/config/stage.config.properties");
					break;

				case "uat":
					ip = new FileInputStream("./src/test/resource/config/uat.config.properties");
					break;

				default:
					System.out.println("Pass the right browser");
					break;
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

		}
		try {
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return prop;
	}
	
	public static Object[][] getTestData(String sheetName) throws IOException {
		
		FileInputStream fis = new FileInputStream("TestData.xlsx");
		Workbook workbook = new XSSFWorkbook(fis);
		Sheet sheet = workbook.getSheet(sheetName);
		
		int rows = sheet.getLastRowNum();
		int cols = sheet.getRow(0).getLastCellNum();
		
		Object[] [] data = new Object[rows][cols];
		
		for(int i=0; i < rows; i++) {
			for(int j=0; j < cols; j++) {
				data[i+1][j] = sheet.getRow(i).getCell(j).toString();
			}
		}
		 workbook.close();
		 return data;
		
	}
	
	
	public static String takeScreenshot(String methodName) {
		
		File src = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		
//		 String dirPath = "./screenshot/";
//		    File directory = new File(dirPath);
//
//		    // ✅ create directory if not exists
//		    if (!directory.exists()) {
//		        directory.mkdirs();
//		    }
		
		String path = System.getProperty("user.dir") + "./screenshot/"+System.currentTimeMillis()+".png";
		
		File destn = new File(path);
		
		try {
			FileHandler.copy(src, destn);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}
	

}
