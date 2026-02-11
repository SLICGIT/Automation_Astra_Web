package pages

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

import internal.GlobalVariable
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.By as By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import org.openqa.selenium.support.ui.ExpectedConditions as ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait as WebDriverWait
import java.time.Duration as Duration
import methods.dropdowns
import methods.FetchExcelData
import methods.Wait

public class SourceBy_Page {

	@Keyword
	static def fillSourceDetails(String testcaseID) {

		Map data = FetchExcelData.getData("SourceBy_Page", testcaseID)

		WebUI.waitForPageLoad(GlobalVariable.pageTimeout)
		
		Wait.addWait()

		WebUI.enhancedClick(findTestObject('SourceDetails/PolicySourcebyMeYes'))

		dropdowns.selectAntDropdown('SourceBy1', 'Direct')

		//selectAntDropdown('SourceCode1', 'J02076-JEET YOGESH KANSARA\t')
		WebDriver driver = DriverFactory.getWebDriver()

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10))

		WebElement sourecCodeDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(('div[name=\'' + 'SourceCode1') +
				'\']')))

		sourecCodeDropdown.click()

		WebElement SourceCodeOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(('//div[contains(@class,\'ant-select-item-option-content\') and contains(text(),\'' +
				GlobalVariable.userID) + '\')]')))

		SourceCodeOption.click()

		WebUI.enhancedClick(findTestObject('SourceDetails/Next'))

		WebUI.enhancedClick(findTestObject('SourceDetails/Confirm'))

		GlobalVariable.Actual += " | Source Details Filled Successfully"
	}
}
