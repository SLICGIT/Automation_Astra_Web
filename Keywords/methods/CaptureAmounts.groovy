package methods

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

public class CaptureAmounts {

	static def getValues() {

		String text1 = WebUI.getText(findTestObject('PlanDetails/BasePremValue'))

		//	println('Basic Premium: ' + text1)
		GlobalVariable.basePremAmount = text1.substring(2)

		String text2 = WebUI.getText(findTestObject('PlanDetails/TotalValue'))
		GlobalVariable.totalAmount = text2.substring(2)

		String text3 = WebUI.getText(findTestObject('PlanDetails/SAValue'))
		GlobalVariable.SAAmount = text3.substring(2)

		if(WebUI.waitForElementPresent(findTestObject('PlanDetails/APValue'), 2)) {

			//String text4 = WebUI.getText(findTestObject('PlanDetails/APValue'))
			//GlobalVariable.APAmount = text4.substring(2)
		}
	}
}
