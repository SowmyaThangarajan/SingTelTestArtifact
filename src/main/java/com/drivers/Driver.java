package com.drivers;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class Driver {

	public WebDriver driver;
	public static ThreadLocal<WebDriver> tl = new ThreadLocal<WebDriver>();
	
	public WebDriver initializeDriver(String browserName) {
		System.out.println("initializeDriver " + browserName);
		if(browserName.equalsIgnoreCase("Chrome")) {
			WebDriverManager.chromedriver().setup();
			tl.set(new ChromeDriver());
		}
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		return getDriver();
	}
	
	public static synchronized WebDriver getDriver() {
		return tl.get();
	}
	
	public static void tearDown() {
		getDriver().quit();
	}
	
	public static void takeScreenshot(String fileName) throws IOException {
		Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(getDriver());
		ImageIO.write(screenshot.getImage(), "jpg", new File(System.getProperty("user.dir")+"/target/SaveResults/Screenshots/" + fileName + ".jpg"));
	}
	
}
