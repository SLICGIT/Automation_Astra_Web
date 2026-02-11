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
import methods.FetchExcelData

public class Documents_Page {

	@Keyword
	static def uploadAllDocuments(String lifeType, String testcaseID/*documentPath, String pan, String aadhar*/) {

		Map data = FetchExcelData.getData("Documents_Page", testcaseID)

		WebUI.waitForPageLoad(GlobalVariable.pageTimeout)

		WebDriver driver = DriverFactory.getWebDriver()

		if (lifeType == 'Other Life') {

			dropdowns.selectAntDropdown('PropoIDProf', data['Prop_IDProof'])

			//WebUI.setText(findTestObject('DocumentsUpload/ProposerPAN'), Prop_PAN)

			WebElement uploadPropIDProof = driver.findElement(By.xpath('//span[text()=\'Proposer ID Proof Upload\']/ancestor::div[contains(@class,\'ant-upload-select\')]//input[@type=\'file\']'))

			uploadPropIDProof.sendKeys(data['Prop_IDProof_Doc'])

			WebUI.delay(1)

			dropdowns.selectAntDropdown('PropoAddProf', data['Prop_AddressProof'])

			//WebUI.setText(findTestObject('DocumentsUpload/PropAadhaar'), 'VI1234567')

			WebElement uploadPropAddressProof = driver.findElement(By.xpath('//span[text()=\'Proposer Address Proof Upload\']/ancestor::div[contains(@class,\'ant-upload-select\')]//input[@type=\'file\']'))

			uploadPropAddressProof.sendKeys(data['Prop_AddressProof_Doc'])

			WebUI.delay(1)
		}

		dropdowns.selectAntDropdown('AgePRDoc', data['LA_AgeProof'])

		if(data['LA_AgeProof'] == 'PAN Card - Standard') {

			WebUI.setText(findTestObject('DocumentsUpload/PANNUM'), data['LA_AgeProof_Number'])
		}

		WebElement uploadAgeProof = driver.findElement(By.xpath('//span[text()=\'LA Age Proof Upload\']/ancestor::div[contains(@class,\'ant-upload-select\')]//input[@type=\'file\']'))

		uploadAgeProof.sendKeys(data['LA_AgeProof_Doc'])

		WebUI.delay(1)

		dropdowns.selectAntDropdown('IDPRDoc', data['LA_IDProof'])

		WebUI.setText(findTestObject('DocumentsUpload/IDProofID'), data['LA_IDProof_Number'])

		WebElement uploadIDProof = driver.findElement(By.xpath('//span[text()=\'LA ID Proof Upload\']/ancestor::div[contains(@class,\'ant-upload-select\')]//input[@type=\'file\']'))

		uploadIDProof.sendKeys(data['LA_IDProof_Doc'])

		WebUI.delay(1)

		dropdowns.selectAntDropdown('AddPRDoc', data['LA_AddressProof'])

		WebUI.setText(findTestObject('DocumentsUpload/AddressProofID'), data['LA_AddressProof_Number'])

		WebElement uploadAddressProof = driver.findElement(By.xpath('//span[text()=\'LA Address Proof Upload\']/ancestor::div[contains(@class,\'ant-upload-select\')]//input[@type=\'file\']'))

		uploadAddressProof.sendKeys(data['LA_AddressProof_Doc'])

		WebUI.delay(1)

		WebElement uploadDeclaration = driver.findElement(By.xpath('//span[text()=\'Declaration Photo Copy\']/ancestor::div[contains(@class,\'ant-upload-select\')]//input[@type=\'file\']'))

		uploadDeclaration.sendKeys(data['LA_Declaration_Doc'])

		WebUI.delay(1)

		dropdowns.selectAntDropdown('IncoPRDoc', data['LA_IncomeProof'])

		WebElement uploadIncomeProof = driver.findElement(By.xpath('//span[text()=\'LA Income Proof Upload\']/ancestor::div[contains(@class,\'ant-upload-select\')]//input[@type=\'file\']'))

		uploadIncomeProof.sendKeys(data['LA_IncomeProof_Doc'])

		WebUI.delay(1)

		WebUI.enhancedClick(findTestObject('DocumentsUpload/Next'))
		
		GlobalVariable.Actual += " | Documents Uploaded Successfully"
	}
}
