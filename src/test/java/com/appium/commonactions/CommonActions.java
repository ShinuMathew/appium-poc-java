package com.appium.commonactions;

import java.util.Set;

import org.openqa.selenium.By;

import com.appium.variables.CommonVariables;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class CommonActions {

	//Switch Contexts
	public static void switchContext() {
		try {
			Set<String> contexts = ((AppiumDriver<MobileElement>) CommonVariables.driver).getContextHandles();
			if (contexts.size() > 1) {
				for (String context : contexts) {
					if (CommonVariables.setContext.toString().equalsIgnoreCase("NativeView")) {
						if (context.equalsIgnoreCase("NATIVE_APP")) {
							((AppiumDriver<MobileElement>) CommonVariables.driver).context(context);
							((AppiumDriver<MobileElement>) CommonVariables.driver).findElement(By.id("button_once"))
									.click();
						}
					} else if (CommonVariables.setContext.toString().equalsIgnoreCase("WebView")) {
						if (context.equalsIgnoreCase("NATIVE_APP")) {
							((AppiumDriver<MobileElement>) CommonVariables.driver).context(context);

						}
					}

				}
			}
		} catch (Exception e) {

		}
	}
}
