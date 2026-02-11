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
import methods.GenerateName
import methods.ConvertAge
import methods.FetchExcelData

public class Nominee_Details_Page {

	@Keyword
	static def fillNomineeDetails(String testcaseID/*String age, String gender*/) {

		Map data = FetchExcelData.getData("Nominee_Details_Page", testcaseID)

		WebUI.waitForPageLoad(GlobalVariable.pageTimeout)

		dropdowns.selectAntDropdown('Salut', data['Nominee_Salutation'])

		WebUI.setText(findTestObject('NomineeDetails/Firstname'), GenerateName.getName())

		WebUI.setText(findTestObject('NomineeDetails/MiddleName'), GenerateName.getName())

		WebUI.setText(findTestObject('NomineeDetails/LastName'), GenerateName.getName())

		WebUI.setText(findTestObject('NomineeDetails/DOB'), ConvertAge.convertAgetoDOB(data['Nominee_Age']))

		//WebUI.waitForPageLoad(GlobalVariable.pageTimeout)

		WebUI.enhancedClick(findTestObject('NomineeDetails/Gender', [('Nominee_Gender') : data['Nominee_Gender']]))

		Wait.addWait()

		dropdowns.selectAntDropdown('reln', data['Nominee_Relation'])

		WebUI.enhancedClick(findTestObject('OtherDetails/FamilyHistory/Next'))
		
		GlobalVariable.Actual += " | Nominee Details Filled Successfully"
	}
}
