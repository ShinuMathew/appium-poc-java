package com.maven.testngDemo.TestNGDemo;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.*;

import com.appium.commonactions.CommonActions;
import com.appium.variables.CommonVariables;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import net.bytebuddy.utility.privilege.GetSystemPropertyAction;

public class AppiumTestStartUp {
	private static String Appium_Node_Path = "C:\\Program Files\\nodejs\\node.exe";
	private static String Appium_JS_Path = "C:\\Users\\#$h!nz#\\AppData\\Local\\Programs\\Appium\\resources\\app\\node_modules\\appium\\build\\lib\\main.js";
	private static AppiumDriverLocalService appiumService;

	public static SimpleDateFormat df = new SimpleDateFormat("MM-dd-yy HH:mm:ss.SSS");

	@BeforeTest
	public static void testSetUp() {
		System.out.println("Starting Appium server" + df.format(new Date()));
		startAppiumServer();
		//Commented5
	}

	@AfterTest
	public static void testCleanUp() {
		System.out.println("Stopping Appium server " + df.format(new Date()));
		stopAppiumServer();
	}

	
	@Test(priority = 1)
	public static void testCase() {
		// AppiumDriver<MobileElement> driver = null;
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("deviceName", "OnePlus 3T");
		caps.setCapability("udid", "de7af825"); // Give Device ID of your mobile phone
		caps.setCapability("platformName", "Android");
		caps.setCapability("platformVersion", "8.0");
		/*
		 * caps.setCapability("appPackage", "com.experitest.ExperiBank");
		 * caps.setCapability("appActivity", ".LoginActivity");
		 */
		caps.setCapability("browserName", "Chrome");
		caps.setCapability("noReset", "true");
		caps.setCapability("autoGrantPermissions", "true");
		caps.setCapability("autoAcceptAlerts", "true");

		if (appiumService.isRunning()) {
			try {
				CommonVariables.driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"),
						caps);
				CommonVariables.driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
				CommonVariables.driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
				CommonVariables.driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
				CommonVariables.driver.get("https://qa.marykayintouch.ca/Login/Login.aspx");

				CommonVariables.driver.findElement(By.id("txtConsultantID")).sendKeys("BU6865");
				CommonVariables.driver.findElement(By.id("txtPassword")).sendKeys("MKqa2015");

				((AndroidDriver<?>) CommonVariables.driver).pressKeyCode(AndroidKeyCode.ENTER);
				Thread.sleep(5000);
				CommonVariables.setContext= com.appium.variables.CommonVariables.ContextSet.NativeView;

				CommonActions.switchContext();
				
				/*String title = CommonVariables.driver.getTitle();
				if (title.equalsIgnoreCase("InTouch Home")) {
					System.out.println("Verified");
				} else {
					System.out.println("Mismatch");
				}
*/
				CommonVariables.driver.close();
				CommonVariables.driver.quit();
				// break;
			}

			catch (Exception ex) {
				System.out.println("Unable to launch app.\nFollowing exception occured while launching the app: "
						+ ex.getMessage());
			}
		}

	}

	public static void startAppiumServer() {
		try {
			appiumService = AppiumDriverLocalService
					.buildService(new AppiumServiceBuilder().usingDriverExecutable(new File(Appium_Node_Path))
							.withAppiumJS(new File(Appium_JS_Path)).withIPAddress("127.0.0.1").usingPort(4723));
			appiumService.start();
		} catch (Exception e) {
			System.out.println("Unable to start appium server.\nFollowing exception occured while starting the server: "
					+ e.getMessage());
		}
	}

	public static void stopAppiumServer() {
		appiumService.stop();
	}

}
