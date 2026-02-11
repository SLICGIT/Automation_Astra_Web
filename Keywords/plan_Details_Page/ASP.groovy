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
import plan_Details_Page.RiderDetails_PremDriven

public class ASP {

	@Keyword
	static def fillPlanDetails(String testcaseID) {

		Map data = FetchExcelData.getData("Plan_Details_Page", testcaseID) // Fetching data from excel and storing in map variable.

		WebUI.waitForPageLoad(GlobalVariable.pageTimeout) // Waiting for page to load. time is defined in environment

		WebUI.enhancedClick(findTestObject('chooseproduct/ProductName', [('Plan_Name') : data["Plan_Name"]]))

		WebUI.waitForPageLoad(GlobalVariable.pageTimeout)

		dropdowns.selectAntDropdown('AgeProofType', data["LA_Age_Proof"])

		dropdowns.selectAntDropdown('Option', data["Life_Cover"])

		dropdowns.selectAntDropdown('MaturityBenfit', data["Maturity_Benefit"])

		dropdowns.selectAntDropdown('Deathbenefit', data["Death_Benefit"])

		dropdowns.selectAntDropdown('PremFreqmode', data["Frequency"])

		dropdowns.selectAntDropdown('PolicyTerm', data["PT"])

		WebUI.delay(0.5)

		dropdowns.selectAntDropdown('PermiumTerm', data["PPT"])

		WebUI.setText(findTestObject('PlanDetails/PremiumContribution2'), data["Premium"])

		SS.capture("Plan_Details")
		
		WebUI.enhancedClick(findTestObject('PlanDetails/Calculate'))

		RiderDetails_PremDriven.fillRiderDetails() // Method is defined to execute riders if riders are available

		WebUI.enhancedClick(findTestObject('PlanDetails/Calculate'))

		Wait.addWait()

		WebUI.scrollToElement(findTestObject('PlanDetails/Next'), 1, FailureHandling.OPTIONAL)

		SS.capture("Plan_Details")

		CaptureAmounts.getValues() // getValues method is defined in methods package to capture premium values available in plan details screen.

		WebUI.enhancedClick(findTestObject('PlanDetails/Next'))

		downloadBI.checkforBIDownload(data["BI_Required"])
	}
}
