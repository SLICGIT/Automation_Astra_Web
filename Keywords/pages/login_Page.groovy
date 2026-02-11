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
import methods.createExcel
import methods.FetchExcelData
import methods.CaptureScreenshot as SS

public class login_Page {

	static def login(String testcaseID) {

		Map data = FetchExcelData.getData("Login_Page", testcaseID)  // Fetching data from excel and storing in map variable

		GlobalVariable.userID = data["Username"]

		WebUI.openBrowser(GlobalVariable.url)

		WebUI.waitForPageLoad(GlobalVariable.pageTimeout)

		WebUI.maximizeWindow()

		WebUI.setText(findTestObject('Login/User ID'), GlobalVariable.userID)

		WebUI.setText(findTestObject('Login/Password'), data["Password"])

		WebUI.setText(findTestObject('Login/EnterCaptcha'), '1234')

		SS.capture("LoginPage")  // Defined capture method in methods package and passing screenshot name as argument

		createExcel.generateReport()

		WebUI.enhancedClick(findTestObject('Login/Login'))

		GlobalVariable.Actual = "${GlobalVariable.userID} Login Successfully"
	}
}
