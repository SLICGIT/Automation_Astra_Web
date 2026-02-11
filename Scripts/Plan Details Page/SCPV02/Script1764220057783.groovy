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
import javax.swing.JOptionPane as JOptionPane
import org.openqa.selenium.interactions.Actions as Actions
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.apache.poi.ss.usermodel.*
import java.nio.file.*
import java.io.*
import methods.dropdowns
import methods.Wait
import methods.CaptureAmounts


dropdowns.selectAntDropdown('Option', Life_Cover)

dropdowns.selectAntDropdown('Deathbenefit', Death_Benefit)

if(Death_Benefit != 'Basic Cover-Lumpsum') {
	
	dropdowns.selectAntDropdown('DeathPayoutMode', Death_Payout)
	
	dropdowns.selectAntDropdown('DeathPayoutPeriod', Death_Payout_Period)
	
}

dropdowns.selectAntDropdown('DeathIncreamentType', Death_Incr_Type)

if(Death_Incr_Type == 'Increasing') {
	
	dropdowns.selectAntDropdown('Death_IncValue', Death_Incr_Value)
	
}

dropdowns.selectAntDropdown('DeathSmartExit', Smart_Exit)

dropdowns.selectAntDropdown('PremFreqmode', Frequency)

dropdowns.selectAntDropdown('PolicyTerm', PT)

WebUI.delay(0.5)

dropdowns.selectAntDropdown('PermiumTerm', PPT)

WebUI.setText(findTestObject('PlanDetails/SumAssured'), Sum_Assured)

WebUI.takeScreenshot(GlobalVariable.screenshotDir + '/2_PlanPageDetails.jpg')

WebUI.callTestCase(findTestCase('Test Cases/Old Test Cases/RiderDetails/RiderDetails_SADriven'),
	[
					('EIC_Rider') : EIC_Rider,
					('EIC_PT') : EIC_PT,
					('EIC_PPT') : EIC_PPT,
					('EIC_SA') : EIC_SA,
					('CIWCare_Rider') : CIWCare_Rider,
					('CIWCare_PT') : CIWCare_PT,
					('CIWCare_PPT') : CIWCare_PPT,
					('CIWCare_SA') : CIWCare_SA,
					('CIW_Mat_Rider') : CIW_Mat_Rider,
					('CIW_Mat_PT') : CIW_Mat_PT,
					('CIW_Mat_PPT') : CIW_Mat_PPT,
					('CIW_Mat_SA') : CIW_Mat_SA,
					('CIP_Rider') : CIP_Rider,
					('CIP_PT') : CIP_PT,
					('CIP_PPT') : CIP_PPT,
					('CIP_SA') : CIP_SA,
					('AB_Rider') : AB_Rider,
					('AB_PT') : AB_PT,
					('AB_PPT') : AB_PPT,
					('AB_SA') : AB_SA,
					('FIB_Rider') : FIB_Rider,
					('FIB_PT') : FIB_PT,
					('FIB_PPT') : FIB_PPT,
					('FIB_SA') : FIB_SA,
					('Step_Rider') : Step_Rider,
					('Step_PT') : Step_PT,
					('Step_PPT') : Step_PPT,
					('Step_SA') : Step_SA
	],
	FailureHandling.STOP_ON_FAILURE)

WebUI.enhancedClick(findTestObject('PlanDetails/Calculate'))

Wait.addWait()

WebUI.scrollToElement(findTestObject('PlanDetails/Next'), 1, FailureHandling.OPTIONAL)

WebUI.takeScreenshot(GlobalVariable.screenshotDir + '/3_PlanCalculate.jpg')

CaptureAmounts.getValues()

WebUI.enhancedClick(findTestObject('PlanDetails/Next'))