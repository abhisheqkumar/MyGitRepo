package com.evs.vtiger.GenericCode;

import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class WebDriverUtil {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GenericMethods gmObj=	new GenericMethods();
		//ExtentSparkReporter exRepo=	(ExtentSparkReporter)driver;
		gmObj.extentReports("AlokYadav", "createTestLead");
		gmObj.launchBrowser("Chrome");
		gmObj.openUrl("http://localhost:8888/");
		gmObj.sendValue("name","user_name", "admin","user name", "Framwork\\takeSnapshot");
		gmObj.sendValue("name","user_password", "admin","user password", "Framwork\\takeSnapshot");
		gmObj.Click(null, null, null)

		gmObj.flushMethod();

	}

}
