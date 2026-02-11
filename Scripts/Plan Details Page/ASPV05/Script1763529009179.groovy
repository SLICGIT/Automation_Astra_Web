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


dropdowns.selectAntDropdown('Option', Life_Cover)

dropdowns.selectAntDropdown('MaturityBenfit', Maturity_Benefit)

dropdowns.selectAntDropdown('Deathbenefit', Death_Benefit)

dropdowns.selectAntDropdown('PremFreqmode', Frequency)

dropdowns.selectAntDropdown('PolicyTerm', PT)

WebUI.delay(0.5)

dropdowns.selectAntDropdown('PermiumTerm', PPT)

WebUI.setText(findTestObject('PlanDetails/PremiumContribution'), Premium)

WebUI.takeScreenshot(GlobalVariable.screenshotDir + '/2_PlanPageDetails.jpg')

WebUI.callTestCase(findTestCase('Test Cases/Old Test Cases/RiderDetails/RiderDetails_PremDriven'),
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

addWait()

WebUI.scrollToElement(findTestObject('PlanDetails/Next'), 1, FailureHandling.OPTIONAL)

WebUI.takeScreenshot(GlobalVariable.screenshotDir + '/3_PlanCalculate.jpg')

getPremiumValues()

WebUI.enhancedClick(findTestObject('PlanDetails/Next'))


//static def selectAntDropdown(String dropdownName, String valueToSelect) {
//	WebDriver driver = DriverFactory.getWebDriver()
//
//	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10))
//
//	WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(('div[name=\'' + dropdownName) +
//				'\']')))
//
//	dropdown.click()
//
//	Actions actions = new Actions(driver)
//
////	WebElement option = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(('//div[contains(@class,\'ant-select-item-option-content\') and text()=\'' +
////				valueToSelect) + '\']')))
//
//	for (int i = 1; i < 3; i++) {
//		
//		try {
//			
//			def optionXPath = '(//div[contains(@class,\'ant-select-item-option-content\') and text()=\'' + valueToSelect + '\'])[' + i + ']'
//			
//			WebElement option = driver.findElement(By.xpath(optionXPath))
//			
//            if (option.isDisplayed()) {
//                option.click()
//				
//                break
//            }
//			
//        } catch (Exception e) {} 
//        
//	}
//	
//}


def getPremiumValues() {
	
	String text1 = WebUI.getText(findTestObject('PlanDetails/BasePremValue'))
	
//	println('Basic Premium: ' + text1)
	GlobalVariable.basePremAmount = text1.substring(2)
	
	String text2 = WebUI.getText(findTestObject('PlanDetails/TotalValue'))
	GlobalVariable.totalAmount = text2.substring(2)
	
	String text3 = WebUI.getText(findTestObject('PlanDetails/SAValue'))
	GlobalVariable.SAAmount = text3.substring(2)
	
	if(WebUI.waitForElementPresent(findTestObject('PlanDetails/APValue'), 2)) {
	
		String text4 = WebUI.getText(findTestObject('PlanDetails/APValue'))
		GlobalVariable.APAmount = text4.substring(2)
	
	}
	
}

static def addWait() {
	WebDriver driver = DriverFactory.getWebDriver()

	while (driver.findElements(By.xpath('//span[contains(@class,\'ant-spin-dot-spin\')]')).size() > 0) {
		WebUI.delay(1)
	}
	
	WebUI.delay(0.5)
}

