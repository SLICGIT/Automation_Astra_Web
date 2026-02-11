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
import methods.FetchExcelData
import methods.CaptureScreenshot as SS

public class LA_Details_Page {

	@Keyword
	static def fillLADetails(String testcaseID) {

		Map LAdata = FetchExcelData.getData("LA_Details_Page", testcaseID) // Fetching data from excel and storing in map variable.
		Map homedata = FetchExcelData.getData("Home_Page", testcaseID)  // Fetching home page data to validate POS and Non POS elements.

		String age = LAdata['LA_Age']

		dropdowns.selectAntDropdown('LATitle', LAdata['LA_Salutation'])

		WebUI.setText(findTestObject('CustomerDetails/FirstName'), GenerateName.getName())  // Defined getName() in methods package to generate random names.

		WebUI.setText(findTestObject('CustomerDetails/LastName'), 'Test')

		WebUI.setText(findTestObject('CustomerDetails/DOB'), ConvertAge.convertAgetoDOB(age)) // Defined convertAge in methods package to convert age to DOB.

		WebUI.delay(1)

		WebUI.enhancedClick(findTestObject('CustomerDetails/Gender', [('Gender') : LAdata['LA_Gender']])) // Passing values in Xpath

		WebUI.waitForElementClickable(findTestObject('CustomerDetails/MaritalStatus', [('LA_Marital_Status') : LAdata['LA_Marital_Status']]), GlobalVariable.pageTimeout) // Waiting for element to be clickable

		WebUI.enhancedClick(findTestObject('CustomerDetails/MaritalStatus', [('LA_Marital_Status') : LAdata['LA_Marital_Status']]))

		WebUI.setText(findTestObject('CustomerDetails/FatherSpousename'), GenerateName.getName())

		WebUI.setText(findTestObject('CustomerDetails/Aadhar'), LAdata['LA_Aadhaar'])

		WebUI.setText(findTestObject('CustomerDetails/PANnumber'), LAdata['LA_PAN'])

		if(age.toInteger() >= 18) {

			dropdowns.selectAntDropdown('LAAnInR', LAdata['LA_Income_Range'])

			WebUI.setText(findTestObject('CustomerDetails/AnnualIncome'), LAdata['LA_Income'])
		}

		dropdowns.selectAntDropdown('LAPL', LAdata['LA_Language'])

		dropdowns.selectAntDropdown('LAEduQual', LAdata['LA_Education'])

		dropdowns.selectAntDropdown('LAOBJINS', LAdata['LA_Insurance_Obj'])

		if(homedata['Proposal_Type'] == 'POS') {
			dropdowns.selectAntDropdown('NOd1', LAdata['LA_DutyNature'])
			dropdowns.selectAntDropdown('Doyousmoke', LAdata['LA_Smoke'])
		}

		WebUI.enhancedClick(findTestObject('CustomerDetails/PEP', [('LA_PEP') : LAdata['LA_PEP']]))

		dropdowns.selectAntDropdown('LARisk_App', LAdata['LA_Risk_Appetite'])

		SS.capture("LA_Details") // Defined capture method in methods package and passing screenshot name as argument

		if (homedata['Life_Type'] == 'Own Life') {
			WebUI.enhancedClick(findTestObject('CustomerDetails/Disability', [('LA_Disability') : LAdata['LA_Disability']]))
		}

		dropdowns.selectAntDropdown('Occ', LAdata['LA_Occupation'])

		if(age.toInteger() < 6) {
			dropdowns.selectAntDropdown('ChildOccupation', LAdata['LA_Occupation_SubCat'])
		} else if (age.toInteger() >= 6 && age.toInteger() < 18) {
			if(LAdata['LA_Occupation'] == 'Student') {
				dropdowns.selectAntDropdown('StudentOccupation', LAdata['LA_Occupation_SubCat'])
			} else {
				dropdowns.selectAntDropdown('NonStudentOccupation', LAdata['LA_Occupation_SubCat'])
			}
		} else {
			dropdowns.selectAntDropdown('SubOccSalEmp', LAdata['LA_Occupation_SubCat'])
		}

		WebUI.enhancedClick(findTestObject('CustomerDetails/EIA', [('LA_EIA') : LAdata['LA_EIA']]))

		dropdowns.selectAntDropdown('Nationality', LAdata['LA_Nationality'])

		WebUI.enhancedClick(findTestObject('CustomerDetails/Next'))

		GlobalVariable.Actual += " | LA Details Filled Successfully"
	}
}
