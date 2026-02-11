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
import methods.CaptureScreenshot as SS
import methods.dropdowns
import methods.FetchExcelData

public class RiderDetails_SADriven {

	@Keyword
	static def fillRiderDetails() {

		Map data = FetchExcelData.getData("Plan_Details_Page", GlobalVariable.testcaseID) // Fetching data from excel and storing in map variable.

		if (data['AB_Rider'] == 'Yes' || data['AB_Rider'] == 'yes') {

			WebUI.enhancedClick(findTestObject('PlanDetails/Riders/ABchkbx'))

			WebUI.delay(1)

			WebUI.enhancedClick(findTestObject('PlanDetails/Riders/ABchkbx2'))

			WebUI.delay(1)

			riderDetails(data['AB_PT'], data['AB_PPT'], data['AB_SA'])
		}

		if (data['FIB_Rider'] == 'Yes' || data['FIB_Rider'] == 'yes') {

			WebUI.enhancedClick(findTestObject('PlanDetails/Riders/FIBchkbx'))

			WebUI.delay(1)

			WebUI.enhancedClick(findTestObject('PlanDetails/Riders/FIBchkbx2'))

			WebUI.delay(1)

			riderDetails(data['FIB_PT'], data['FIB_PPT'], data['FIB_SA'])
		}

		if (data['EIC_Rider'] == 'Yes' || data['EIC_Rider'] == 'yes') {

			WebUI.enhancedClick(findTestObject('PlanDetails/Riders/EICchkbx'))

			WebUI.delay(1)

			WebUI.enhancedClick(findTestObject('PlanDetails/Riders/EICchkbx2'))

			WebUI.delay(1)

			riderDetails(data['EIC_PT'], data['EIC_PPT'], data['EIC_SA'])
		}

		if ((data['CIWCare_Rider'] == 'Yes' || data['CIWCare_Rider'] == 'yes') && (data['CIW_Mat_Rider'] == 'Yes' || data['CIW_Mat_Rider'] == 'yes')) {

			WebUI.enhancedClick(findTestObject('PlanDetails/Riders/CIWchkbx'))

			WebUI.delay(1)

			CIWRiderDetails(data['CIWCare_PT'], data['CIWCare_PPT'], data['CIWCare_SA'], data['CIW_Mat_PT'], data['CIW_Mat_PPT'], data['CIW_Mat_SA'])
		} else if (data['CIWCare_Rider'] == 'Yes' || data['CIWCare_Rider'] == 'yes') {

			WebUI.enhancedClick(findTestObject('PlanDetails/Riders/CIWchkbx'))

			WebUI.delay(1)

			WebUI.enhancedClick(findTestObject('PlanDetails/Riders/CareCoverchkbx'))

			WebUI.delay(1)

			riderDetails(data['CIWCare_PT'], data['CIWCare_PPT'], data['CIWCare_SA'])
		} else if (data['CIW_Mat_Rider'] == 'Yes' || data['CIW_Mat_Rider'] == 'yes') {

			WebUI.enhancedClick(findTestObject('PlanDetails/Riders/CIWchkbx'))

			WebUI.delay(1)

			WebUI.enhancedClick(findTestObject('PlanDetails/Riders/Maternitychkbx'))

			WebUI.delay(1)

			riderDetails(data['CIW_Mat_PT'], data['CIW_Mat_PPT'], data['CIW_Mat_SA'])
		}

		if (data['CIP_Rider'] == 'Yes' || data['CIP_Rider'] == 'yes') {

			WebUI.enhancedClick(findTestObject('PlanDetails/Riders/CIPchkbx'))

			WebUI.delay(1)

			WebUI.enhancedClick(findTestObject('PlanDetails/Riders/CIPchkbx2'))

			WebUI.delay(1)

			riderDetails(data['CIP_PT'], data['CIP_PPT'], data['CIP_SA'])
		}

		if (data['Step_Rider'] == 'Yes' || data['Step_Rider'] == 'yes') {

			WebUI.enhancedClick(findTestObject('PlanDetails/Riders/StepRiderchkbx'))

			WebUI.delay(1)

			WebUI.enhancedClick(findTestObject('PlanDetails/Riders/StepRiderchkbx2'))

			WebUI.delay(1)

			dropdowns.selectRiderDropdown('PolicyTerm', data['Step_PT'])

			WebUI.delay(1)

			dropdowns.selectRiderDropdown('PermiumTerm', data['Step_PPT'])

			dropdowns.selectAntDropdown('RiderPercentage', data['Step_SA'])

			WebUI.enhancedClick(findTestObject('PlanDetails/Riders/Save'))

			SS.capture("Plan_Details_Rider")

			WebUI.enhancedClick(findTestObject('PlanDetails/Riders/RiderCloseBtn'))
		}

		if(data['Arogya_Rider'].toString().equalsIgnoreCase("Yes")) {

			WebUI.enhancedClick(findTestObject('PlanDetails/Riders/ArogyaRiderchkbx'))

			if(data['Arogya_Option1'].toString().equalsIgnoreCase("Yes")) {
				WebUI.enhancedClick(findTestObject('PlanDetails/Riders/ArogyaOption1chkbx'))
				dropdowns.selectRiderDropdown('PolicyTerm', data['Arogya_Opt1_PT'])
				dropdowns.selectRiderDropdown('PermiumTerm', data['Arogya_Opt1_PPT'])
				WebUI.enhancedClick(findTestObject('PlanDetails/Riders/ArogyaOpt1Consent'))
				WebUI.enhancedClick(findTestObject('PlanDetails/Riders/ArogyaConsentAgreeBtn'))
				WebUI.enhancedClick(findTestObject('PlanDetails/Riders/Save'))
				SS.capture("Plan_Details_Rider")
				WebUI.enhancedClick(findTestObject('PlanDetails/Riders/RiderCloseBtn'))
			}
			if(data['Arogya_Option2'].toString().equalsIgnoreCase("Yes")) {
				WebUI.enhancedClick(findTestObject('PlanDetails/Riders/ArogyaOption2chkbx'))
				dropdowns.selectRiderDropdown('PolicyTerm', data['Arogya_Opt2_PT'])
				dropdowns.selectRiderDropdown('PermiumTerm', data['Arogya_Opt2_PPT'])
				WebUI.enhancedClick(findTestObject('PlanDetails/Riders/ArogyaOpt1Consent'))
				WebUI.enhancedClick(findTestObject('PlanDetails/Riders/ArogyaConsentAgreeBtn'))
				WebUI.enhancedClick(findTestObject('PlanDetails/Riders/Save'))
				SS.capture("Plan_Details_Rider")
				WebUI.enhancedClick(findTestObject('PlanDetails/Riders/RiderCloseBtn'))
			}
			if(data['Arogya_Option3'].toString().equalsIgnoreCase("Yes")) {
				WebUI.enhancedClick(findTestObject('PlanDetails/Riders/ArogyaOption3chkbx'))
				dropdowns.selectRiderDropdown('PolicyTerm', data['Arogya_Opt3_PT'])
				dropdowns.selectRiderDropdown('PermiumTerm', data['Arogya_Opt3_PPT'])
				WebUI.enhancedClick(findTestObject('PlanDetails/Riders/ArogyaOpt1Consent'))
				WebUI.enhancedClick(findTestObject('PlanDetails/Riders/ArogyaConsentAgreeBtn'))
				WebUI.enhancedClick(findTestObject('PlanDetails/Riders/Save'))
				SS.capture("Plan_Details_Rider")
				WebUI.enhancedClick(findTestObject('PlanDetails/Riders/RiderCloseBtn'))
			}
		}
	}

	@Keyword
	static def riderDetails(String policyTerm, String premiumTerm, String sumAssured) {

		dropdowns.selectRiderDropdown('PolicyTerm', policyTerm)

		WebUI.delay(1)

		dropdowns.selectRiderDropdown('PermiumTerm', premiumTerm)

		WebUI.setText(findTestObject('PlanDetails/Riders/SADriven_RiderSA'), sumAssured)

		WebUI.enhancedClick(findTestObject('PlanDetails/Riders/Save'))

		SS.capture("Plan_Details_Rider")

		WebUI.enhancedClick(findTestObject('PlanDetails/Riders/RiderCloseBtn'))
	}

	@Keyword
	static def CIWRiderDetails(String carepolicyTerm, String carepremiumTerm, String caresumAssured, String matpolicyTerm, String matpremiumTerm, String matsumAssured) {

		//	WebUI.enhancedClick(findTestObject('PlanDetails/Riders/Riderchkbx'))

		WebUI.enhancedClick(findTestObject('PlanDetails/Riders/CareCoverchkbx'))

		WebUI.delay(1)

		dropdowns.selectRiderDropdown('PolicyTerm', carepolicyTerm)

		WebUI.delay(1)

		dropdowns.selectRiderDropdown('PermiumTerm', carepremiumTerm)

		WebUI.setText(findTestObject('PlanDetails/Riders/SADriven_RiderSA'), caresumAssured)

		WebUI.enhancedClick(findTestObject('PlanDetails/Riders/Save'))

		WebUI.enhancedClick(findTestObject('PlanDetails/Riders/Maternitychkbx'))

		WebUI.delay(1)

		dropdowns.selectRiderDropdownOption2('PolicyTerm', matpolicyTerm)

		WebUI.delay(1)

		dropdowns.selectRiderDropdownOption2('PermiumTerm', matpremiumTerm)

		WebUI.setText(findTestObject('PlanDetails/Riders/SADriven_MaternitySA'), matsumAssured)

		WebUI.enhancedClick(findTestObject('PlanDetails/Riders/Save2'))

		SS.capture("Plan_Details_Rider")

		WebUI.enhancedClick(findTestObject('PlanDetails/Riders/RiderCloseBtn'))
	}
}
