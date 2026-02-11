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
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import methods.dropdowns
import methods.Wait
import methods.WriteExcel
import methods.CaptureScreenshot as SS
import methods.FetchExcelData

public class Payment_Page {

	@Keyword
	static def processPayment(String testcaseID) {

		Map data = FetchExcelData.getData("Payment_Page", testcaseID)

		WebUI.waitForPageLoad(GlobalVariable.pageTimeout)

		WebUI.enhancedClick(findTestObject('PaymentDetails/PayOption', ['Payment_Option' : data['Payment_Option']]))

		WebUI.enhancedClick(findTestObject('PaymentDetails/SubmitBTN'))

		Wait.addWait()

		WebUI.enhancedClick(findTestObject('PaymentDetails/PaymentType', ['Payment_Type' : data['Payment_Type']]))

		Wait.addWait()

		WebUI.enhancedClick(findTestObject('PaymentDetails/InstrumentType', ['Instrument_Type' : data['Instrument_Type']]))

		WebUI.setText(findTestObject('PaymentDetails/ChequeNo'), data['Cheque_Number'])

		dropdowns.selectDropdownValueByLabel('Bank Name', data['Bank_Name'])

		WebUI.setText(findTestObject('PaymentDetails/BankBranch'), data['Bank_Branch'])

		dropdowns.selectDropdownValueByLabel('Deposit Bank', data['Deposit Bank'])

		WebUI.enhancedClick(findTestObject('PaymentDetails/PayBTN'))

		Wait.addWait()

		Boolean error = WebUI.verifyTextPresent('credit limit', false, FailureHandling.OPTIONAL)

		if(error) {

			SS.capture("Payment_Failure")

			GlobalVariable.Actual += " | Payment Failed due to Credit Limit Issue"

			KeywordUtil.markFailed("Payment Failed")
		} else {

			WebUI.enhancedClick(findTestObject('PaymentDetails/SubmitBTN'))

			Wait.addWait()

			Payment_Page.getProposal()

			SS.capture("Proposal_Number")

			GlobalVariable.Actual += " | Proposal Created Successfully"

			WebUI.closeBrowser()
		}
	}

	@Keyword
	static def getProposal() {

		String text = WebUI.getText(findTestObject('ProposalNumber/ProposalNo'))

		GlobalVariable.ProposalNo = text.substring(18)

		println('Proposal Number: ' + GlobalVariable.ProposalNo)
	}
}
