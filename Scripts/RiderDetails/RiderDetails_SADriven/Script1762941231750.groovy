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
import com.kms.katalon.core.annotation.Keyword as Keyword
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.By as By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import org.openqa.selenium.support.ui.ExpectedConditions as ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait as WebDriverWait
import java.time.Duration as Duration
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.Keys
import methods.dropdowns


if (AB_Rider == 'Yes' || AB_Rider == 'yes') {
	
	WebUI.enhancedClick(findTestObject('PlanDetails/Riders/ABchkbx'))
	
	WebUI.delay(1)
	
	WebUI.enhancedClick(findTestObject('PlanDetails/Riders/ABchkbx2'))
	
	WebUI.delay(1)
	
	riderDetails(AB_PT, AB_PPT, AB_SA)
	
}

if (FIB_Rider == 'Yes' || FIB_Rider == 'yes') {
	
	WebUI.enhancedClick(findTestObject('PlanDetails/Riders/FIBchkbx'))
	
	WebUI.delay(1)
	
	WebUI.enhancedClick(findTestObject('PlanDetails/Riders/FIBchkbx2'))
	
	WebUI.delay(1)
	
	riderDetails(FIB_PT, FIB_PPT, FIB_SA)
	
}

if (EIC_Rider == 'Yes' || EIC_Rider == 'yes') {
		
		WebUI.enhancedClick(findTestObject('PlanDetails/Riders/EICchkbx'))
		
		WebUI.delay(1)
		
		WebUI.enhancedClick(findTestObject('PlanDetails/Riders/EICchkbx2'))
		
		WebUI.delay(1)
		
		riderDetails(EIC_PT, EIC_PPT, EIC_SA)
		
	}
	
if ((CIWCare_Rider == 'Yes' || CIWCare_Rider == 'yes') && (CIW_Mat_Rider == 'Yes' || CIW_Mat_Rider == 'yes')) {
	
	WebUI.enhancedClick(findTestObject('PlanDetails/Riders/CIWchkbx'))
	
	WebUI.delay(1)
	
	CIWRiderDetails(CIWCare_PT, CIWCare_PPT, CIWCare_SA, CIW_Mat_PT, CIW_Mat_PPT, CIW_Mat_SA)
	
} else if (CIWCare_Rider == 'Yes' || CIWCare_Rider == 'yes') {
	
	WebUI.enhancedClick(findTestObject('PlanDetails/Riders/CIWchkbx'))
	
	WebUI.delay(1)
	
	WebUI.enhancedClick(findTestObject('PlanDetails/Riders/CareCoverchkbx'))
	
	WebUI.delay(1)
	
	riderDetails(CIWCare_PT, CIWCare_PPT, CIWCare_SA)
	
} else if (CIW_Mat_Rider == 'Yes' || CIW_Mat_Rider == 'yes') {
	
	WebUI.enhancedClick(findTestObject('PlanDetails/Riders/CIWchkbx'))
	
	WebUI.delay(1)
	
	WebUI.enhancedClick(findTestObject('PlanDetails/Riders/Maternitychkbx'))
	
	WebUI.delay(1)
	
	riderDetails(CIW_Mat_PT, CIW_Mat_PPT, CIW_Mat_SA)
	
}

if (CIP_Rider == 'Yes' || CIP_Rider == 'yes') {
	
	WebUI.enhancedClick(findTestObject('PlanDetails/Riders/CIPchkbx'))
	
	WebUI.delay(1)
	
	WebUI.enhancedClick(findTestObject('PlanDetails/Riders/CIPchkbx2'))
	
	WebUI.delay(1)
	
	riderDetails(CIP_PT, CIP_PPT, CIP_SA)
	
}

if (Step_Rider == 'Yes' || Step_Rider == 'yes') {
	
	WebUI.enhancedClick(findTestObject('PlanDetails/Riders/StepRiderchkbx'))
	
	WebUI.delay(1)
	
	WebUI.enhancedClick(findTestObject('PlanDetails/Riders/StepRiderchkbx2'))
	
	WebUI.delay(1)
	
	dropdowns.selectRiderDropdown('PolicyTerm', Step_PT)
	
	WebUI.delay(1)
	
	selectRiderDropdown('PermiumTerm', Step_PPT)
	
	dropdowns.selectAntDropdown('RiderPercentage', Step_SA)
	
	WebUI.enhancedClick(findTestObject('PlanDetails/Riders/Save'))
	
	String text = WebUI.getText(findTestObject('PlanDetails/Riders/RiderHeader'))
	
	WebUI.takeScreenshot(GlobalVariable.screenshotDir + '/' + text + '.jpg')
	
	WebUI.enhancedClick(findTestObject('PlanDetails/Riders/RiderCloseBtn'))
	
//	riderDetails(Step_PT, Step_PPT, Step_SA)
	
}

static void riderDetails(String policyTerm, String premiumTerm, String sumAssured) {
	
//	try {
//
//		WebUI.enhancedClick(findTestObject('PlanDetails/Riders/Riderchkbx'))
//
//	} catch (Exception e) {
//
//		WebUI.enhancedClick(findTestObject('PlanDetails/Riders/Maternitychkbx'))
//
//	}
//
//	WebUI.delay(1)
	
	dropdowns.selectRiderDropdown('PolicyTerm', policyTerm)
	
	WebUI.delay(1)
	
	dropdowns.selectRiderDropdown('PermiumTerm', premiumTerm)
	
	WebUI.setText(findTestObject('PlanDetails/Riders/SADriven_RiderSA'), sumAssured)
	
	WebUI.enhancedClick(findTestObject('PlanDetails/Riders/Save'))
	
	String text = WebUI.getText(findTestObject('PlanDetails/Riders/RiderHeader'))
	
	WebUI.takeScreenshot(GlobalVariable.screenshotDir + '/' + text + '.jpg')
	
	WebUI.enhancedClick(findTestObject('PlanDetails/Riders/RiderCloseBtn'))
	
}

static void CIWRiderDetails(String carepolicyTerm, String carepremiumTerm, String caresumAssured, String matpolicyTerm, String matpremiumTerm, String matsumAssured) {
	
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
	
	String text = WebUI.getText(findTestObject('PlanDetails/Riders/RiderHeader'))
	
	WebUI.takeScreenshot(GlobalVariable.screenshotDir + '/' + text + '.jpg')
	
	WebUI.enhancedClick(findTestObject('PlanDetails/Riders/RiderCloseBtn'))
	
}