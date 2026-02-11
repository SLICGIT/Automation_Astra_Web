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
import methods.CaptureScreenshot as SS
import methods.FetchExcelData

public class Home_Page {

	@Keyword
	static def chooseProposalType(String testcaseID) {

		Map data = FetchExcelData.getData("Home_Page", testcaseID)  // Fetching data from excel and storing in map variable

		WebUI.enhancedClick(findTestObject('HomePage/CreateNewProposal'))

		WebUI.enhancedClick(findTestObject('HomePage/NewProposal'))

		WebUI.waitForPageLoad(GlobalVariable.pageTimeout)

		WebUI.enhancedClick(findTestObject('HomePage/ProposalType', [('Proposal_Type') : data["Proposal_Type"]]))  //Defined proposal type and passed in X-path

		WebUI.enhancedClick(findTestObject('HomePage/CustomerType', [('Customer_Type') : data["Customer_Type"]]))

		WebUI.enhancedClick(findTestObject('HomePage/PolicyType', [('Policy_Type') : data["Policy_Type"]]))

		WebUI.enhancedClick(findTestObject('HomePage/LifeType', [('Life_Type') : data["Life_Type"]]))

		SS.capture("Basic_Details")

		WebUI.enhancedClick(findTestObject('HomePage/KYCType', [('KYC_Type') : data["KYC_Type"]]))

		WebUI.enhancedClick(findTestObject('HomePage/Continue'))

		GlobalVariable.Actual += " | Basic Details Filled Successfully"
	}
}
