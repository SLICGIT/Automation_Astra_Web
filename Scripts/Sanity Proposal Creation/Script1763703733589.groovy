import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil

import methods.CaptureScreenshot as SS
import methods.WriteExcel
import methods.FetchExcelData
import pages.login_Page
import pages.Home_Page
import pages.LA_Details_Page
import pages.Proposer_Details_Page
import pages.Address_Details_Page
import plan_Details_Page.ASP
import pages.SourceBy_Page
import pages.Medical_Details_Page
import pages.Other_Details_Page
import pages.Nominee_Details_Page
import pages.Declaration_Page
import pages.Documents_Page
import pages.NACH_Registration_Page
import pages.Summary_Page
import pages.Payment_Page
import plan_Details_Page.NSV
import plan_Details_Page.ECP
import plan_Details_Page.NSL
import plan_Details_Page.SIP
import plan_Details_Page.SCP
import plan_Details_Page.SPP_SP_POS
import plan_Details_Page.WealthPro
import plan_Details_Page.FPP
import plan_Details_Page.SPP_RP
import plan_Details_Page.SunischitLaabh
import plan_Details_Page.AIP
import plan_Details_Page.PAB
import plan_Details_Page.GoldenJubilee
import plan_Details_Page.FortuneBuilder
import plan_Details_Page.GrowthPlus

//def rows = FetchExcelData.getExecutableRowsFromExcel(GlobalVariable.testDataPath, "Login_Page")

if(Executor.toString().equalsIgnoreCase("Yes")) {    // Execute cases where executor is mentioned as yes

	try {
	
		SS.screenshotDir(TC_ID)   // Defined screenshot directory
		
		login_Page.login(TC_ID)
		
		Home_Page.chooseProposalType(TC_ID)
		
		LA_Details_Page.fillLADetails(TC_ID)
		Map homeData = FetchExcelData.getData("Home_Page", TC_ID)  // Fetching data from home page sheet to execute proposer details if life type is other life
		
		if(homeData['Life_Type'] == 'Other Life') {
			
			Proposer_Details_Page.fillProposerDetails(homeData['Proposal_Type'], TC_ID)
			
		}
		
		Address_Details_Page.fillAddressDetails(TC_ID)
		
		//Matching plan name from datafile to execute the respective plan keyword
		switch (true) {
			case GlobalVariable.planName.toString().startsWith('Shriram Life Assured Savings Plan'):
				ASP.fillPlanDetails(TC_ID)
				break
		
			case GlobalVariable.planName.toString().startsWith('SHRIRAM NEW SHRI VIDYA'):
				NSV.fillPlanDetails(TC_ID)
				break
		
			case GlobalVariable.planName.toString().startsWith('Shriram Life Early Cash Plan'):
				ECP.fillPlanDetails(TC_ID)
				break
		
			case GlobalVariable.planName.toString().startsWith('SHRIRAM NEW SHRI LIFE PLAN'):
				NSL.fillPlanDetails(TC_ID)
				break
		
			case GlobalVariable.planName.toString().startsWith('SHRIRAM LIFE SUPER INCOME PLAN'):
				SIP.fillPlanDetails(TC_ID)
				break
		
			case GlobalVariable.planName.toString().startsWith('Shriram Life Smart Choice Plan'):
				SCP.fillPlanDetails(TC_ID)
				break
		
			case GlobalVariable.planName.toString().startsWith('SHRIRAM LIFE SMART PROTECTION PLAN SP'):
				SPP_SP_POS.fillPlanDetails(TC_ID)
				break
				
			case GlobalVariable.planName.toString().toLowerCase().startsWith('shriram life wealth pro'):
				WealthPro.fillPlanDetails(TC_ID)
				break
			
			case GlobalVariable.planName.toString().startsWith('SHRIRAM LIFE FAMILY PROTECTION PLAN'):
				FPP.fillPlanDetails(TC_ID)
				break
				
			case GlobalVariable.planName.toString().toLowerCase().startsWith('shriram life smart protection plan'):
				SPP_RP.fillPlanDetails(TC_ID)
				break
				
			case GlobalVariable.planName.toString().startsWith('Shriram Life Sunishchit Laabh'):
				SunischitLaabh.fillPlanDetails(TC_ID)
				break
				
			case GlobalVariable.planName.toString().startsWith('Shriram Life Premier Assured Benefit'):
				PAB.fillPlanDetails(TC_ID)
				break
				
			case GlobalVariable.planName.toString().startsWith('Shriram Life Assured Income Plan'):
				AIP.fillPlanDetails(TC_ID)
				break
				
			case GlobalVariable.planName.toString().toLowerCase().startsWith('shriram life golden jubilee plan'):
				GoldenJubilee.fillPlanDetails(TC_ID)
				break
				
			case GlobalVariable.planName.toString().toLowerCase().startsWith('shriram life fortune builder'):
				FortuneBuilder.fillPlanDetails(TC_ID)
				break
				
			case GlobalVariable.planName.toString().toLowerCase().startsWith('shriram life growth plus'):
				GrowthPlus.fillPlanDetails(TC_ID)
				break
				
			default:
				// Optional: handle unexpected plan names
				// KeywordUtil.markWarning("Unknown plan: ${GlobalVariable.planName}")
				// or throw new IllegalArgumentException("Unsupported plan: ${GlobalVariable.planName}")
				break
		}
		GlobalVariable.Actual += " | Plan Details Filled Successfully"
		
		SourceBy_Page.fillSourceDetails(TC_ID)
		
		Medical_Details_Page.fillMedicalDetails(TC_ID)
		
		Other_Details_Page.fillFamilyDetails(TC_ID)
		
		if(homeData['Life_Type'] == 'Own Life') {
			
			Nominee_Details_Page.fillNomineeDetails(TC_ID)
			
		}
		
		Declaration_Page.fillDetails(homeData['Life_Type'], TC_ID, homeData['Proposal_Type'])
		
		Documents_Page.uploadAllDocuments(homeData['Life_Type'], TC_ID)
		
		Map planData = FetchExcelData.getData("Plan_Details_Page", TC_ID)
		
		if(planData['Frequency'] != 'Single') {
			NACH_Registration_Page.fillNachDetails(TC_ID)
		}
		
		Summary_Page.checkSummaryPage()
		
		Payment_Page.processPayment(TC_ID)
	
	} catch (Exception e) {
		
		SS.capture("Execution_Failure")
		
		KeywordUtil.markFailed("Step failed: " + e.message)
		
		String msg = (e.getMessage() ?: e.toString()).trim()
		String firstLine = msg.readLines()?.first() ?: msg
		
		GlobalVariable.Actual += " | " + firstLine
		
	}	
	
}