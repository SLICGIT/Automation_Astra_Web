package methods

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.By as By
import org.openqa.selenium.support.ui.ExpectedConditions as ExpectedConditions
import java.time.Duration as Duration
import org.openqa.selenium.interactions.Actions as Actions
import org.openqa.selenium.Keys as Keys
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import org.openqa.selenium.support.ui.WebDriverWait as WebDriverWait

import internal.GlobalVariable

public class dropdowns {

	@Keyword
	static def selectAntDropdown(String dropdownName, String valueToSelect) {
		WebDriver driver = DriverFactory.getWebDriver()

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10))

		WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(('div[name=\'' + dropdownName) +
				'\']')))

		dropdown.click()

		Actions actions = new Actions(driver)

		//    WebElement option = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(('//div[contains(@class,\'ant-select-item-option-content\') and text()=\'' +
		//                valueToSelect) + '\']')))

		for (int j = 0; j < 9; j++) {

			for (int i = 1; i < 7; i++) {

				try {

					def optionXPath = '(//div[contains(@class,\'ant-select-item-option-content\') and text()=\'' + valueToSelect + '\'])[' + i + ']'

					WebElement option = driver.findElement(By.xpath(optionXPath))

					if (option.isDisplayed()) {

						option.click()
						return
					}
				} catch (Exception e) {}

				actions.sendKeys(Keys.ARROW_DOWN).perform()
			}
		}
	}

	@Keyword
	static def selectDropdownValueByLabel(String labelText, String valueToSelect) {
		WebDriver driver = DriverFactory.getWebDriver()

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20))

		String dropdownXPath = (('//label[.//span[normalize-space(text())=\'' + labelText) + '\']]') + '/preceding-sibling::div//div[contains(@class,\'ant-select-selector\')]'

		WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dropdownXPath)))

		dropdown.click()

		//    String optionXPath = ('//div[contains(@class,\'ant-select-item-option-content\') and normalize-space(text())=\'' + valueToSelect) +
		//    '\']'
		String optionXPath = "//div[contains(@class,'ant-select-item-option-content') and contains(text(),'" + valueToSelect + "')]"

		WebElement option = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(optionXPath)))

		option.click()

		WebUI.comment("✅ Selected '$valueToSelect' for label '$labelText'")
	}

	@Keyword
	static void selectDropdownValue(String labelText, String valueToSelect) {
		WebDriver driver = DriverFactory.getWebDriver()

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20))

		String dropdownXpath = "(//label[normalize-space(text())='$labelText']/preceding-sibling::div//div[contains(@class,'ant-select-selector')])[1]"

		WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dropdownXpath)))

		dropdown.click()

		Thread.sleep(500)

		Actions actions = new Actions(driver)

		//		def optionXPath = '//div[contains(@class,\'ant-select-dropdown\') and not(contains(@class,\'hidden\'))]' + "//div[contains(@class,'ant-select-item-option-content') and normalize-space(text())='$valueToSelect']"
		def optionXPath

		for (int i = 1; i < 51; i++) {
			try {
				//				optionXPath = '(//div[contains(@class,\'ant-select-dropdown\') and not(contains(@class,\'hidden\'))]' + "//div[contains(@class,'ant-select-item-option-content') and normalize-space(text())='$valueToSelect'])[" + i + "]"
				optionXPath = "(//div[contains(@class,'ant-select-item-option-content') and normalize-space(text())='" + valueToSelect + "'])[1]"
				WebElement option = driver.findElement(By.xpath(optionXPath))

				if (option.isDisplayed()) {
					option.click()

					break
				}
			}
			catch (Exception e) {
			}

			try {
				//				optionXPath = '(//div[contains(@class,\'ant-select-dropdown\') and not(contains(@class,\'hidden\'))]' + "//div[contains(@class,'ant-select-item-option-content') and normalize-space(text())='$valueToSelect'])[" + (i+1) + "]"
				optionXPath = "(//div[contains(@class,'ant-select-item-option-content') and normalize-space(text())='" + valueToSelect + "'])[2]"
				WebElement option = driver.findElement(By.xpath(optionXPath))

				if (option.isDisplayed()) {
					option.click()

					break
				}
			}
			catch (Exception e) {
			}

			actions.sendKeys(Keys.ARROW_DOWN).perform()
		}
	}

	@Keyword
	static def selectRiderDropdown(String dropdownName, String valueToSelect) {
		WebDriver driver = DriverFactory.getWebDriver()

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10))

		WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(('(//div[@name=\'' + dropdownName) +
				'\'])[2]')))

		dropdown.click()

		Actions actions = new Actions(driver);

		for (int i = 0; i < 9; i++) {

			for (int j = 1; j < 7; j++) {

				try {

					def optionXPath = "(//div[contains(@class,'ant-select-item-option-content') and text()='" + valueToSelect + "'])[" + j + "]"

					WebElement option = driver.findElement(By.xpath(optionXPath))

					if(option.isDisplayed()) {

						option.click()
						return
					}
				} catch (Exception e) {}

				actions.sendKeys(Keys.ARROW_DOWN).perform();
			}
		}
	}

	@Keyword
	static def selectRiderDropdownOption2(String dropdownName, String valueToSelect) {
		WebDriver driver = DriverFactory.getWebDriver()

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10))

		WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(('(//div[@name=\'' + dropdownName) +
				'\'])[3]')))

		dropdown.click()

		Actions actions = new Actions(driver);

		for (int i = 0; i < 9; i++) {

			for (int j = 1; j < 7; j++) {

				try {

					def optionXPath = "(//div[contains(@class,'ant-select-item-option-content') and text()='" + valueToSelect + "'])[" + j + "]"

					WebElement option = driver.findElement(By.xpath(optionXPath))

					if (option.isDisplayed()) {

						option.click()

						return
					}
				} catch (Exception e) {}

				actions.sendKeys(Keys.ARROW_DOWN).perform();
			}
		}
	}

	@Keyword
	static void selectUlipDropdown(String labelText, String valueToSelect) {
		WebDriver driver = DriverFactory.getWebDriver()

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20))

		//		String dropdownXpath = ('//label[normalize-space(text())=\'' + labelText) + '\']/preceding-sibling::div//div[contains(@class,\'ant-select-selector\')][2]'
		String dropdownXpath = "(//label[normalize-space(text())='$labelText']/preceding-sibling::div//div[contains(@class,'ant-select-selector')])[2]"

		WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dropdownXpath)))

		dropdown.click()

		Thread.sleep(500)

		Actions actions = new Actions(driver)

		def optionXPath = '//div[contains(@class,\'ant-select-dropdown\') and not(contains(@class,\'hidden\'))]' + "//div[contains(@class,'ant-select-item-option-content') and normalize-space(text())='$valueToSelect']"

		for (int i = 0; i < 50; i++) {
			try {
				WebElement option = driver.findElement(By.xpath(optionXPath))

				if (option.isDisplayed()) {
					option.click()

					break
				}
			}
			catch (Exception e) {
			}

			actions.sendKeys(Keys.ARROW_DOWN).perform()
		}
	}
	
	@Keyword
	static def selectUlipRiderDropdown(String dropdownName, String valueToSelect) {
		WebDriver driver = DriverFactory.getWebDriver()

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10))

		String dropdownXpath = "//div[contains(@class, 'ant-modal-mask')]/following::div[@name='$dropdownName'][1]"

		WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dropdownXpath)))

		dropdown.click()

		Actions actions = new Actions(driver)

		//    WebElement option = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(('//div[contains(@class,\'ant-select-item-option-content\') and text()=\'' +
		//                valueToSelect) + '\']')))

		for (int j = 0; j < 9; j++) {

			for (int i = 1; i < 7; i++) {

				try {

					def optionXPath = '(//div[contains(@class,\'ant-select-item-option-content\') and text()=\'' + valueToSelect + '\'])[' + i + ']'

					WebElement option = driver.findElement(By.xpath(optionXPath))

					if (option.isDisplayed()) {

						option.click()
						return
					}
				} catch (Exception e) {}

				actions.sendKeys(Keys.ARROW_DOWN).perform()
			}
		}
	}
	
	
}
