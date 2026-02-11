package plan_Details_Page

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
import methods.CaptureAmounts
import methods.downloadBI
import methods.CaptureScreenshot as SS
import methods.FetchExcelData
import plan_Details_Page.RiderDetails_SADriven

public class FlexiShield {

	@Keyword
	static def fillPlanDetails(String testcaseID) {

		Map data = FetchExcelData.getData("Plan_Details_Page", testcaseID) // Fetching data from excel and storing in map variable.

		WebUI.waitForPageLoad(GlobalVariable.pageTimeout) // Waiting for page to load. time is defined in environment

		WebUI.enhancedClick(findTestObject('chooseproduct/ProductName', [('Plan_Name') : data["Plan_Name"]]))

		WebUI.waitForPageLoad(GlobalVariable.pageTimeout)

		dropdowns.selectAntDropdown('AgeProofType', data["LA_Age_Proof"])

		dropdowns.selectAntDropdown('Option', data['Life_Cover'])

		if(data['Life_Cover'] == 'Shield') {
			dropdowns.selectAntDropdown('suboption', data['Sub_Life_Cover'])
		}

		dropdowns.selectAntDropdown('Deathbenefit', data['Death_Benefit'])

		if(data['Sub_Life_Cover'] == 'Level Cover') {

			dropdowns.selectAntDropdown('DeathLifeGoal', data['Death_Life_Goal'])
		}

		if(data['Death_Benefit'].toString().contains('Increasing') || data['Death_Benefit'].toString().contains('Decreasing') || data['Death_Benefit'].toString().contains('Life Stage Shield')) {

			dropdowns.selectAntDropdown('DeathIncreamentType', data['Death_Incr_Type'])

			dropdowns.selectAntDropdown('Death_IncValue', data['Death_Incr_Value'])

			if(data['Death_Benefit'].toString().contains('Decreasing')) {

				dropdowns.selectAntDropdown('DeathRetirementAgeList', data['Death_Retire_Age'])
			}
		}

		dropdowns.selectAntDropdown('PremFreqmode', data['Frequency'])

		dropdowns.selectAntDropdown('PolicyTermSlab', data['Policy_Term_Slab'])

		String Age = WebUI.getAttribute(findTestObject('PlanDetails/Age'), 'value')

		int ageInt = Age.toInteger()

		String PT = (100 - ageInt).toString()

		if(data['Policy_Term_Slab'].toString().equalsIgnoreCase('Whole Life')) {
			dropdowns.selectAntDropdown('PolicyTerm', PT)
		} else {
			dropdowns.selectAntDropdown('PolicyTerm', data['PT'])
		}

		dropdowns.selectAntDropdown('PremiumTermSlab', data['Premium_Term_Slab'])

		if(data['Premium_Term_Slab'].toString().contains('Whole Life Regular')) {
			dropdowns.selectAntDropdown('PermiumTerm', PT)
		} else {
			dropdowns.selectAntDropdown('PermiumTerm', data['PPT'])
		}

		WebUI.setText(findTestObject('PlanDetails/SumAssured'), data['Sum_Assured'])

		SS.capture("Plan_Details")
		
		WebUI.enhancedClick(findTestObject('PlanDetails/Calculate'))

		RiderDetails_SADriven.fillRiderDetails()

		WebUI.enhancedClick(findTestObject('PlanDetails/Calculate'))

		Wait.addWait()

		WebUI.scrollToElement(findTestObject('PlanDetails/Next'), 1, FailureHandling.OPTIONAL)

		SS.capture("Plan_Details")

		CaptureAmounts.getValues()

		WebUI.enhancedClick(findTestObject('PlanDetails/Next'))
		
		downloadBI.checkforBIDownload(data["BI_Required"])
	}
}
