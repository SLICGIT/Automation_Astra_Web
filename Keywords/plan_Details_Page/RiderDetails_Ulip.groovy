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
import methods.Wait

public class RiderDetails_Ulip {

	@Keyword
	static def fillRiderDetails() {

		Map data = FetchExcelData.getData("Plan_Details_Page", GlobalVariable.testcaseID)

		if(data['ADD_Rider'].toString().equalsIgnoreCase('Yes')) {
			
			WebUI.enhancedClick(findTestObject('PlanDetails/RidersUlip/ADD_Riderchkbx'))
			
			RiderDetails(data['ADD_PT'], data['ADD_PPT'], data['ADD_SA'])
			
		}
		
		if(data['CIC_Rider'].toString().equalsIgnoreCase('Yes')) {
			
			WebUI.enhancedClick(findTestObject('PlanDetails/RidersUlip/CIC_Riderchkbx'))
			
			RiderDetails(data['CIC_PT'], data['CIC_PPT'], data['CIC_SA'])
			
		}
		
		if(data['ADDI_Rider'].toString().equalsIgnoreCase('Yes')) {
			
			WebUI.enhancedClick(findTestObject('PlanDetails/RidersUlip/ADD_IncomeRiderchkbx'))
			
			RiderDetails(data['ADDI_PT'], data['ADDI_PPT'], data['ADDI_SA'])
			
		}
		
	}
	
	@Keyword
	static def RiderDetails(String policyTerm, String premiumTerm, String sumAssured) {
		
		WebUI.enhancedClick(findTestObject('PlanDetails/RidersUlip/RiderEditBtn'))
		
		Wait.addWait()
		
		dropdowns.selectUlipRiderDropdown("PolicyTerm", policyTerm)
		
		dropdowns.selectUlipRiderDropdown("PermiumTerm", premiumTerm)
		
		WebUI.setText(findTestObject('PlanDetails/RidersUlip/RiderSABtn'), sumAssured)
		
		WebUI.enhancedClick(findTestObject('PlanDetails/RidersUlip/RiderSaveBtn'))
		
		SS.capture("Plan_Details_Rider")
		
		WebUI.enhancedClick(findTestObject('PlanDetails/Riders/RiderCloseBtn'))
		
	}
	
}
