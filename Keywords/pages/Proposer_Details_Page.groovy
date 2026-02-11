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
import methods.ConvertAge
import methods.GenerateName
import methods.Wait
import methods.FetchExcelData
import methods.CaptureScreenshot as SS

public class Proposer_Details_Page {

	@Keyword
	static def fillProposerDetails(String proposaltype, String testcaseID) {

		Map data = FetchExcelData.getData("Proposer_Details_Page", testcaseID) // Fetching data from excel and storing in map variable.

		WebUI.waitForPageLoad(GlobalVariable.pageTimeout)

		dropdowns.selectAntDropdown('TPR1', 'Mr.')

		WebUI.setText(findTestObject('ProposerDetails/FirstName'), GenerateName.getName())

		WebUI.setText(findTestObject('ProposerDetails/LastName'), GenerateName.getName())

		WebUI.setText(findTestObject('ProposerDetails/ProposerDOB'), ConvertAge.convertAgetoDOB(data["Prop_Age"]))

		dropdowns.selectAntDropdown('PropRelLA', 'Father')

		if(proposaltype == 'POS') {
			dropdowns.selectAntDropdown('SourceOfIncome', 'Salary')
		}

		WebUI.setText(findTestObject('ProposerDetails/PAN'), data["Prop_PAN"])

		dropdowns.selectAntDropdown('PropEDuQual', 'Graduation')

		WebUI.setText(findTestObject('ProposerDetails/Occupation'), data["Prop_Occupation"])

		WebUI.setText(findTestObject('ProposerDetails/AnnualIncome'), data["Prop_Income"])

		dropdowns.selectAntDropdown('NT1', 'Indian')

		WebUI.setText(findTestObject('ProposerDetails/Mobile'), data["Prop_Mobile"])

		WebUI.enhancedClick(findTestObject('ProposerDetails/VerifyMobile'))

		Wait.addWait()
		
		dropdowns.selectDropdownValue("Relationship", "Self")
		
		WebUI.setText(findTestObject('ProposerDetails/MobileVerifyReason'), 'Ok')
		
		WebUI.enhancedClick(findTestObject('ProposerDetails/OkBtn'))

		Wait.addWait() // Defined addWait method in methods package to wait until loading element is visible

		SS.capture("Proposer_Details") // Defined capture method in methods package and passing screenshot name as argument

		WebUI.enhancedClick(findTestObject('ProposerDetails/DisabilityNo'))

		WebUI.enhancedClick(findTestObject('ProposerDetails/Next'))

		GlobalVariable.Actual += " | Proposer Details filled Successfully"
	}
}
