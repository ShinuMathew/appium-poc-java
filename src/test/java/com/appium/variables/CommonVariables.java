package com.appium.variables;

import org.openqa.selenium.WebDriver;

public class CommonVariables {

	public static WebDriver driver;
	
	public static ContextSet setContext;
	
	public enum ContextSet{
		
		WebView, NativeView
	}
}


