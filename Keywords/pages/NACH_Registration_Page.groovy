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
import methods.dropdowns
import methods.Wait
import methods.GenerateName
import methods.FetchExcelData

public class NACH_Registration_Page {

	@Keyword
	static def fillNachDetails(String testcaseID/*String accountNo, String ifsc, String documentPath*/) {

		Map data = FetchExcelData.getData("NACH_Page", testcaseID)

		WebUI.waitForPageLoad(GlobalVariable.pageTimeout)

		dropdowns.selectAntDropdown('NachRegdr', data['NACH_Registration'])

		WebUI.delay(1)

		WebUI.enhancedClick(findTestObject('NACHRegistration/AddNACH'))

		WebUI.waitForPageLoad(GlobalVariable.pageTimeout)

		WebUI.setText(findTestObject('NACHRegistration/AccHolderName'), GenerateName.getName())

		WebUI.setText(findTestObject('NACHRegistration/AccNumber'), data['Account_Number'])

		WebUI.setText(findTestObject('NACHRegistration/ReAccNumber'), data['Account_Number'])

		WebUI.setText(findTestObject('NACHRegistration/IFSC'), data['IFSC_Code'])

		WebUI.enhancedClick(findTestObject('NACHRegistration/IFSCSearch'))

		Wait.addWait()

		dropdowns.selectAntDropdown('AccountTypeMN', data['Account_Type'])

		dropdowns.selectAntDropdown('PrefDebitDateMN', data['Debit_Date'])

		WebUI.enhancedClick(findTestObject('NACHRegistration/SameBankBtn', ['Same_Bank' : data['Same_Bank']]))

		WebUI.enhancedClick(findTestObject('NACHRegistration/Save'))

		Wait.addWait()

		WebUI.enhancedClick(findTestObject('NACHRegistration/OkBtn'))

		Wait.addWait()

		WebDriver driver = DriverFactory.getWebDriver()

		WebElement uploadFront = driver.findElement(By.xpath('//span[text()=\'Take a Picture (Front Side)\']/ancestor::div[contains(@class,\'ant-upload-select\')]//input[@type=\'file\']'))

		uploadFront.sendKeys(data['Front_Picture'])

		WebUI.delay(1)

		WebElement uploadBack = driver.findElement(By.xpath('//span[text()=\'Take a Picture (Back Side)\']/ancestor::div[contains(@class,\'ant-upload-select\')]//input[@type=\'file\']'))

		uploadBack.sendKeys(data['Back_Picture'])

		WebUI.delay(1)

		WebElement uploadBankProof = driver.findElement(By.xpath('//span[text()=\'Copy of Bank Proof\']/ancestor::div[contains(@class,\'ant-upload-select\')]//input[@type=\'file\']'))

		uploadBankProof.sendKeys(data['BankProof_Copy'])

		WebUI.delay(1)

		WebUI.enhancedClick(findTestObject('NACHRegistration/Next'))

		WebUI.delay(1)

		WebUI.enhancedClick(findTestObject('NACHRegistration/CheckBox'))

		WebUI.enhancedClick(findTestObject('NACHRegistration/Confirm'))

		GlobalVariable.Actual += " | NACH Details Updated Successfully"
	}
}
