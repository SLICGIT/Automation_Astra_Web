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
import methods.dropdowns
import methods.Wait
import methods.FetchExcelData

public class Address_Details_Page {

	@Keyword
	static def fillAddressDetails(String testcaseID) {

		Map data = FetchExcelData.getData("Address_Details_Page", testcaseID) // Fetching data from excel and storing in map variable.

		WebUI.waitForPageLoad(GlobalVariable.pageTimeout)  // Waiting for page to load. Time is defined in environment

		WebUI.setText(findTestObject('AddressDetails/Doornumber'), data['Door_No'])

		WebUI.setText(findTestObject('AddressDetails/Street'), data['Street'])

		WebUI.setText(findTestObject('AddressDetails/Pincode'), data['Pincode'])

		Wait.addWait() // Defined addWait method in methods package to wait until loading element is visible

		dropdowns.selectAntDropdown('PerArea', 'Alwal')

		WebUI.enhancedClick(findTestObject('AddressDetails/CommAddressQues', [('Comm_Address') : data['Comm_Address']]))

		WebUI.setText(findTestObject('AddressDetails/LAMobilenumber'), data['Mobile_Number'])

		WebUI.enhancedClick(findTestObject('AddressDetails/Verify'))
		
		Wait.addWait() // Defined addWait method in methods package to wait until loading element is visible
		
		dropdowns.selectDropdownValue("Relationship", "Self")
		
		WebUI.setText(findTestObject('AddressDetails/MobileVerifyReason'), 'Ok')
		
		WebUI.enhancedClick(findTestObject('AddressDetails/OkBtn'))

		Wait.addWait() // Defined addWait method in methods package to wait until loading element is visible

		WebUI.enhancedClick(findTestObject('AddressDetails/whatsappQues', [('WhatsApp') : data['WhatsApp']]))

		WebUI.setText(findTestObject('AddressDetails/EmailID'), data['EmaiID'])

		WebUI.enhancedClick(findTestObject('AddressDetails/Next'))

		GlobalVariable.Actual += " | Address Details Filled Successfully"
	}
}
