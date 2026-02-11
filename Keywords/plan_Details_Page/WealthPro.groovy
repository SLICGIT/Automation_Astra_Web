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
import methods.addFund

public class WealthPro {

	@Keyword
	static def fillPlanDetails(String testcaseID) {

		Map data = FetchExcelData.getData("Plan_Details_Page", testcaseID) // Fetching data from excel and storing in map variable.

		WebUI.waitForPageLoad(GlobalVariable.pageTimeout) // Waiting for page to load. time is defined in environment

		WebUI.enhancedClick(findTestObject('chooseproduct/ProductName', [('Plan_Name') : data["Plan_Name"]]))

		WebUI.waitForPageLoad(GlobalVariable.pageTimeout)

		if(GlobalVariable.planName.toString().contains('V02')) {

			dropdowns.selectAntDropdown('AgeProofType', data["LA_Age_Proof"])

			dropdowns.selectAntDropdown('Option', data['Life_Cover'])

			dropdowns.selectAntDropdown('Deathbenefit', data['Death_Benefit'])

			dropdowns.selectAntDropdown('PremFreqmode', data['Frequency'])

			dropdowns.selectDropdownValue('Policy Term', data["PT"])

			dropdowns.selectAntDropdown('PremiumTermSlab', data['Premium_Term_Slab'])

			dropdowns.selectDropdownValue('Premium Term', data["PPT"])

			WebUI.setText(findTestObject('PlanDetails/PremiumContribution2'), data['Premium'])

			WebUI.setText(findTestObject('PlanDetails/SumAssured'), data['Sum_Assured'])

			SS.capture("Plan_Details")

			if(data['ATO_Option'].toString().equalsIgnoreCase("yes")) {
				WebUI.enhancedClick(findTestObject('PlanDetails/ATOOptionUlip'))
				dropdowns.selectAntDropdown('ATOPeriodMonths', data['ATO_Period'])
			}
			
			addFund.selectFund()

			WebUI.enhancedClick(findTestObject('PlanDetails/Calculate'))

			WebUI.enhancedClick(findTestObject('PlanDetails/Calculate'))
			
			Wait.addWait()

			WebUI.scrollToElement(findTestObject('PlanDetails/Next'), 1, FailureHandling.OPTIONAL)

			SS.capture("Plan_Details")

			WebUI.enhancedClick(findTestObject('PlanDetails/Next'))
		} else {

			dropdowns.selectAntDropdown('AgeProofID', data["LA_Age_Proof"])

			dropdowns.selectDropdownValue('Death Benefit Type', data['Death_Benefit'])

			dropdowns.selectDropdownValue('Maturity Benefit', data['Maturity_Benefit'])

			dropdowns.selectDropdownValue('Policy term', data['PT'])

			dropdowns.selectDropdownValue('Premium term', data['PPT'])

			dropdowns.selectDropdownValue('Premium Mode', data['Frequency'])

			dropdowns.selectUlipDropdown('Death Benefit Type', data['Death_Payout'])

			WebUI.setText(findTestObject('PlanDetails/SumAssured'), data['Sum_Assured'])

			WebUI.setText(findTestObject('PlanDetails/PremiumContribution2'), data['Premium'])

			SS.capture("Plan_Details")

			addFund.selectFund()

			WebUI.enhancedClick(findTestObject('PlanDetails/Calculate2'))

			Wait.addWait()

			WebUI.scrollToElement(findTestObject('PlanDetails/NextBtn2'), 1, FailureHandling.OPTIONAL)

			SS.capture("Plan_Details")

			WebUI.enhancedClick(findTestObject('PlanDetails/NextBtn2'))
		}

		downloadBI.checkforBIDownload(data["BI_Required"])
	}
}
