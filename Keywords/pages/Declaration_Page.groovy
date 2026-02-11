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
import javax.swing.JOptionPane as JOptionPane
import methods.dropdowns
import methods.Wait
import methods.FetchExcelData

public class Declaration_Page {

	@Keyword
	static def fillDetails(String lifeType, String testcaseID, String proposerType) {

		Map data = FetchExcelData.getData("Declaration_Page", testcaseID)
		Map ladata = FetchExcelData.getData("LA_Details_Page", testcaseID)

		WebUI.waitForElementPresent(findTestObject('Declaration/Photo'), GlobalVariable.pageTimeout)

		WebUI.delay(0.5)

		WebDriver driver = DriverFactory.getWebDriver()

		WebElement uploadLAPhoto = driver.findElement(By.xpath('(//div[contains(@class,\'upload-photo\')]//input[@type=\'file\'])[1]'))

		// Use sendKeys() to upload file directly
		uploadLAPhoto.sendKeys(data['LA_Photo'])

		WebUI.delay(1)

		if(lifeType == 'Other Life') {

			WebElement uploadPropPhoto = driver.findElement(By.xpath('(//div[contains(@class,\'upload-photo\')]//input[@type=\'file\'])[2]'))

			uploadPropPhoto.sendKeys(data['Prop_Photo'])

			WebUI.delay(1)

			if(proposerType == 'ULIP') {

				dropdowns.selectAntDropdown('ProposerVerify', 'Proposer OTP')

				WebUI.enhancedClick(findTestObject('Declaration/ProposerOTPBtn'))

				JOptionPane.showMessageDialog(null, 'Click OK to continue')

				WebUI.enhancedClick(findTestObject('Declaration/OTPConfirmBtn'))

				Wait.addWait()
			} else {

				dropdowns.selectAntDropdown('ProposerVerify', 'Signature of the Proposer')

				WebElement uploadPropSignature = driver.findElement(By.xpath('//span[text()=\'Signature of the Proposer\']/ancestor::div[contains(@class,\'ant-upload-select\')]//input[@type=\'file\']'))

				uploadPropSignature.sendKeys(data['Prop_Signature'])

				WebUI.delay(1)
			}
		}

		if(proposerType == 'ULIP') {
			
			if(ladata['LA_Age'].toString().toInteger() >= 18) {

				dropdowns.selectAntDropdown('LAVerify', 'LA OTP')
	
				WebUI.enhancedClick(findTestObject('Declaration/LAOTPBtn'))
	
				JOptionPane.showMessageDialog(null, 'Click OK to continue')
	
				WebUI.enhancedClick(findTestObject('Declaration/OTPConfirmBtn'))
	
				Wait.addWait()
			
			}
			
		} else {

			dropdowns.selectAntDropdown('LAVerify', 'Signature of the LA')

			WebElement uploadLASignature = driver.findElement(By.xpath('//span[text()=\'Signature of the LA\']/ancestor::div[contains(@class,\'ant-upload-select\')]//input[@type=\'file\']'))

			uploadLASignature.sendKeys(data['LA_Signature'])
		}

		WebUI.delay(1)

		dropdowns.selectAntDropdown('AgentVerify', 'Signature of the Agent')

		WebElement uploadAgentSignature = driver.findElement(By.xpath('//span[text()=\'Signature of the Agent\']/ancestor::div[contains(@class,\'ant-upload-select\')]//input[@type=\'file\']'))

		uploadAgentSignature.sendKeys(data['Agent_Signature'])

		WebUI.delay(1)

		WebUI.enhancedClick(findTestObject('Declaration/Consent1'))

		WebUI.enhancedClick(findTestObject('Declaration/Consent2'))

		WebUI.enhancedClick(findTestObject('Declaration/Next'))

		WebUI.enhancedClick(findTestObject('Declaration/Agree'))

		GlobalVariable.Actual += " | Declaration Page Filled Successfully"
	}
}
