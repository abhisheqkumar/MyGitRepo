package com.evs.vtiger.GenericCode;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.Select;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.managers.OperaDriverManager;

public class GenericMethods {
	static WebDriver driver;
	static ExtentTest exTest;

	public static void sendValue(String locatorType , String locatorValue, String sendValue, String elementName, String filePath) {
		try {
			WebElement wefind =GenericMethods.getWebElement(locatorType, locatorValue, elementName);
			boolean status = checkPropertiesOfElement(wefind, elementName);
			if (status == true) {
				wefind.sendKeys(sendValue);
				exTest.log(Status.INFO, sendValue + " send in " + elementName + " successfully");
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			GenericMethods.takeSnapshot(filePath);

		} catch (StaleElementReferenceException e) {
			e.printStackTrace();
			GenericMethods.takeSnapshot(filePath);

		} catch (Exception e) {
			e.printStackTrace();
			GenericMethods.takeSnapshot(filePath);

		}
	}

	public static void sendValueJavaScript(String locatorValue, String elementName, String value, String filePath) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			WebElement wefind = driver.findElement(By.xpath(locatorValue));
			boolean status = checkPropertiesOfElement(wefind, elementName);
			if (status == true) {
				js.executeScript("arguments[0].value='" + value + "'", wefind);
				exTest.log(Status.INFO, value + "send in " + elementName + " successfully");
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			GenericMethods.takeSnapshot(filePath);

		} catch (StaleElementReferenceException e) {
			e.printStackTrace();
			GenericMethods.takeSnapshot(filePath);

		} catch (Exception e) {
			e.printStackTrace();
			GenericMethods.takeSnapshot(filePath);

		}
	}

	public static void sendValueActions(String locatorValue, String elementName, String value, String filePath) {
		try {
			Actions actionsObj = new Actions(driver);
			WebElement wefind = driver.findElement(By.xpath(locatorValue));
			boolean status = checkPropertiesOfElement(wefind, elementName);
			if (status == true) {
				actionsObj.moveToElement(wefind).sendKeys(value).build().perform();
				exTest.log(Status.INFO, value + " send in " + elementName + " successfully");
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			GenericMethods.takeSnapshot(filePath);

		} catch (StaleElementReferenceException e) {
			e.printStackTrace();
			GenericMethods.takeSnapshot(filePath);

		} catch (Exception e) {
			e.printStackTrace();
			GenericMethods.takeSnapshot(filePath);

		}
	}

	public static void Click(String locatorValue, String elementName, String filePath) {
		try {
			WebElement wefind = driver.findElement(By.xpath(locatorValue));
			boolean status = checkPropertiesOfElement(wefind, elementName);
			if (status == true) {
				wefind.click();
				exTest.log(Status.INFO, " Click on" + elementName + " successfully");
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			GenericMethods.takeSnapshot(filePath);
		} catch (StaleElementReferenceException e) {
			e.printStackTrace();
			GenericMethods.takeSnapshot(filePath);
		} catch (Exception e) {
			e.printStackTrace();
			GenericMethods.takeSnapshot(filePath);
		}
	}

	public static void clickActions(String locatorValue, String elementName, String filePath) {
		try {
			Actions actionsObj = new Actions(driver);
			WebElement wefind = driver.findElement(By.xpath(locatorValue));
			boolean status = checkPropertiesOfElement(wefind, elementName);
			if (status == true) {
				actionsObj.moveToElement(wefind).click().build().perform();
				exTest.log(Status.INFO, " Click on" + elementName + " successfully");
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			GenericMethods.takeSnapshot(filePath);
		} catch (StaleElementReferenceException e) {
			e.printStackTrace();
			GenericMethods.takeSnapshot(filePath);
		} catch (Exception e) {
			e.printStackTrace();
			GenericMethods.takeSnapshot(filePath);
		}
	}

	public static void clickJavaScript(String locatorValue, String elementName, String filePath) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			WebElement wefind = driver.findElement(By.xpath(locatorValue));
			boolean status = checkPropertiesOfElement(wefind, elementName);
			if (status == true) {
				js.executeScript("arguments[0].click()", wefind);
				exTest.log(Status.INFO, " Click on" + elementName + " successfully");
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			GenericMethods.takeSnapshot(filePath);
		} catch (StaleElementReferenceException e) {
			e.printStackTrace();
			GenericMethods.takeSnapshot(filePath);
		} catch (Exception e) {
			e.printStackTrace();
			GenericMethods.takeSnapshot(filePath);
		}
	}

	public static void takeSnapshot(String filePath) {
		Date date = new Date();
		long time = date.getTime();
		TakesScreenshot tSs = (TakesScreenshot) driver;
		File fObj = tSs.getScreenshotAs(OutputType.FILE);
		File fileObj = new File(filePath + time + ".png");
	}

	public static boolean checkPropertiesOfElement(WebElement wefind, String elementName) {
		boolean status = false;
		if (wefind.isDisplayed() == true) {
			exTest.log(Status.INFO, elementName + " is displaying on UI");
			if (wefind.isEnabled() == true) {
				status = true;
				exTest.log(Status.INFO, elementName + " is functionable");
			} else {
				exTest.log(Status.FAIL, elementName + " is not functionable");
			}
		} else {
			exTest.log(Status.FAIL, elementName + " is not Displaying on UI");
		}
		return status;
	}

	public static WebElement getWebElement(String locatorType, String locatorValue,String elementName) {
		WebElement we = null;
		if (locatorType.equalsIgnoreCase("name")) {
			
			we = driver.findElement(By.name(locatorValue));
			exTest.log(Status.INFO, locatorValue + " entered in "+elementName+" box");

		} else if (locatorType.equalsIgnoreCase("id")) {
			we = driver.findElement(By.id(locatorValue));
			exTest.log(Status.INFO, locatorValue + " entered in "+elementName+" box");

		} else if (locatorType.equalsIgnoreCase("linkText")) {
			we = driver.findElement(By.linkText(locatorValue));
			exTest.log(Status.INFO, locatorValue + " entered in "+elementName+" box");

		} else if (locatorType.equalsIgnoreCase("className")) {
			we = driver.findElement(By.className(locatorValue));
			exTest.log(Status.INFO, locatorValue + " entered in "+elementName+" box");

		} else if (locatorType.equalsIgnoreCase("partialLinkText")) {
			we = driver.findElement(By.partialLinkText(locatorValue));
			exTest.log(Status.INFO, locatorValue + " entered in "+elementName+" box");

		} else if (locatorType.equalsIgnoreCase("cssSelector")) {
			we = driver.findElement(By.cssSelector(locatorValue));
			exTest.log(Status.INFO, locatorValue + " entered in "+elementName+" box");

		} else if (locatorType.equalsIgnoreCase("xpath")) {
			we = driver.findElement(By.xpath(locatorValue));
			exTest.log(Status.INFO, locatorValue + " entered in "+elementName+" box");

		} else if (locatorType.equalsIgnoreCase("tagName")) {
			we = driver.findElement(By.tagName(locatorValue));
			exTest.log(Status.INFO, locatorValue + " entered in "+elementName+" box");

		} else {
			exTest.log(Status.FAIL, locatorType + " -locator type is mismatched try again");
		}
		return we;
	}

	public static String getInnerText(String locatorType, String locatorValue, String elementName) {
		String innerText = null;
		WebElement we = getWebElement(locatorType, locatorValue,elementName);
		boolean status = checkPropertiesOfElement(we, elementName);
		if (status == true) {
			innerText = we.getText();
			exTest.log(Status.INFO, innerText + "- is text of " + elementName);
		}
		return innerText;
	}

	public static void getwindowHandlesValue(String TitleOfWindow) {
		Set<String> handles = driver.getWindowHandles();
		for (String string : handles) {
			driver.switchTo().window(string);
			String title = driver.getTitle();
			if (title.equals(handles)) {
				exTest.log(Status.INFO, "We switched on " + title + " window successfully");

				break;
			}
		}
	}

	public static void selectByindexing(String locatorType, String locatorValue, String elementName, int index) {
		WebElement wefind = GenericMethods.getWebElement(locatorType, locatorValue,elementName);
		boolean status = GenericMethods.checkPropertiesOfElement(wefind, elementName);
		if (status == true) {
			Select selectObj = new Select(wefind);
			selectObj.selectByIndex(index);
			exTest.log(Status.INFO, index + "th element of dropdown is selected successfully");

		}
	}

	public static void selectByValueAttribute(String locatorType, String locatorValue, String elementName,
			String atributeValue$id) {
		WebElement wefind = GenericMethods.getWebElement(locatorType, locatorValue,elementName);
		boolean status = GenericMethods.checkPropertiesOfElement(wefind, elementName);
		if (status == true) {
			Select selectObj = new Select(wefind);
			selectObj.selectByValue(atributeValue$id);
			exTest.log(Status.INFO, atributeValue$id + " element of dropdown is selected successfully");

		}
	}

	public static void selectByVisiableInnerText(String locatorType, String locatorValue, String elementName,
			String innerText) {
		WebElement wefind = GenericMethods.getWebElement(locatorType, locatorValue,elementName);
		boolean status = GenericMethods.checkPropertiesOfElement(wefind, elementName);
		if (status == true) {
			Select selectObj = new Select(wefind);
			selectObj.selectByVisibleText(innerText);
			exTest.log(Status.INFO, innerText + " element of dropdown is selected successfully");

		}
	}

	public static void launchBrowser(String browserName) {
		if (browserName.equalsIgnoreCase("Chrome")) {
			driver = new ChromeDriver();
			exTest.log(Status.INFO, browserName + " browser launched Successfully");
		} else if (browserName.equalsIgnoreCase("FireFox")) {
			driver = new FirefoxDriver();
			exTest.log(Status.INFO, browserName + " browser launched Successfully");

		} else if (browserName.equalsIgnoreCase("Safari")) {
			driver = new SafariDriver();
			exTest.log(Status.INFO, browserName + " browser launched Successfully");

		} else if (browserName.equalsIgnoreCase("InternetExplorer")) {
			driver = new InternetExplorerDriver();
			exTest.log(Status.INFO, browserName + " browser launched Successfully");

		} else if (browserName.equalsIgnoreCase("Opera")) {
			driver = (WebDriver) new OperaDriverManager();
			exTest.log(Status.INFO, browserName + " browser launched Successfully");

		} else {
			exTest.log(Status.FAIL, "browser Name -" + browserName + " missmatched try again");

		}

	}

	public static void openUrl(String url) {
		try {
			driver.get(url);
			exTest.log(Status.PASS, url + "Opened successfully");

		} catch (Exception e) {
			e.printStackTrace();
			exTest.log(Status.FAIL, url + "missmatched");
		}
	}

	public ExtentReports extentReports(String fileName, String testCaseName) {
		ExtentReports reports = null;
		try {
			ExtentSparkReporter exRepo = new ExtentSparkReporter(new File(fileName + ".html"));
			reports = new ExtentReports();
			reports.attachReporter(exRepo);
			exTest = reports.createTest(testCaseName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reports;

	}

	public static void flushMethod() {
		ExtentReports exrepo  =new ExtentReports();
		exrepo.flush();
	}
}
