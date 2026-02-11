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
import methods.GenerateName
import methods.FetchExcelData

public class Other_Details_Page {

	@Keyword
	static def fillFamilyDetails(String testcaseID/*String family1, String family1Age, String family2, String family2Age*/) {

		Map data = FetchExcelData.getData("Other_Details_Page", testcaseID)
		Map homedata = FetchExcelData.getData("Home_Page", testcaseID)

		WebUI.waitForPageLoad(GlobalVariable.pageTimeout)

		WebUI.delay(0.5)

		WebUI.enhancedClick(findTestObject('OtherDetails/otherDetailsQuestion1', ['Qu_Hobbies' : data['Qu_Hobbies']]))

		if(data['Qu_Hobbies'] == 'Yes') {
			WebUI.setText(findTestObject('OtherDetails/Question1Remarks'), data['Hobbies_Details'])
		}

		WebUI.enhancedClick(findTestObject('OtherDetails/otherDetailsQuestion2', ['Qu_Convicted' : data['Qu_Convicted']]))

		if(data['Qu_Convicted'] == 'Yes') {
			WebUI.setText(findTestObject('OtherDetails/Question2Remarks'), data['Convicted_Details'])
		}

		WebUI.enhancedClick(findTestObject('OtherDetails/OtherDetailsQuestion3No'))

		WebUI.enhancedClick(findTestObject('OtherDetails/OtherDetailsQuestion4No'))

		if(homedata['Proposal_Type'] != 'POS') {
			Other_Details_Page.addFamilyMember(data['Family1_Age'], data['Family1_Status'], data['Family1_Relation'], data['Family1_HealthStatus'], data['Family1_HealthHistory'], data['Family1_DiagnoseDate'], data['Family1_DeathDate'], data['Family1_DeathCause'])
			Other_Details_Page.addFamilyMember(data['Family2_Age'], data['Family2_Status'], data['Family2_Relation'], data['Family2_HealthStatus'], data['Family2_HealthHistory'], data['Family2_DiagnoseDate'], data['Family2_DeathDate'], data['Family2_DeathCause'])
		}

		WebUI.enhancedClick(findTestObject('OtherDetails/FamilyHistory/Next'))
		
		GlobalVariable.Actual += " | Other Details Filled Successfully"
	}

	@Keyword
	static def addFamilyMember(String age, String lifeStatus, String relation, String healthStatus, String healthHistory, String diagnoseDate, String deathDate, String deathCause) {

		WebUI.enhancedClick(findTestObject('OtherDetails/FamilyHistory/Addmember'))

		WebUI.setText(findTestObject('OtherDetails/FamilyHistory/Name'), GenerateName.getName())

		WebUI.setText(findTestObject('OtherDetails/FamilyHistory/Age'), age)

		dropdowns.selectAntDropdown('status', lifeStatus)

		dropdowns.selectAntDropdown('relationship', relation)

		if(lifeStatus == 'Alive') {

			dropdowns.selectAntDropdown('healthStatus', healthStatus)
			if(healthStatus == 'Adverse') {
				//				WebUI.setText(findTestObject('OtherDetails/FamilyHistory/AdverseReason'), adverseReason)
				dropdowns.selectAntDropdown('adverseStatus', healthHistory)
				WebUI.setText(findTestObject('OtherDetails/FamilyHistory/DiagnoseDate'), diagnoseDate)
			}
		} else {
			WebUI.setText(findTestObject('OtherDetails/FamilyHistory/DeathDate'), deathDate)
			WebUI.setText(findTestObject('OtherDetails/FamilyHistory/DeathCause'), deathCause)
		}

		WebUI.enhancedClick(findTestObject('OtherDetails/FamilyHistory/Done'), FailureHandling.STOP_ON_FAILURE)
	}
}
