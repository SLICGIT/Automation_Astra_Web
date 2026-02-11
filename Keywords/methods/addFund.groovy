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
import methods.FetchExcelData

public class addFund {
	
	@Keyword
	static def selectFund() {
		
		Map data = FetchExcelData.getData("Plan_Details_Page", GlobalVariable.testcaseID)
		
		if(data['Maximus_Fund%'] != null && data['Maximus_Fund%'] != '' && data['Maximus_Fund%'] != '0') {
		
			WebUI.enhancedClick(findTestObject('PlanDetails/AddFundBtn'))
			
			WebUI.enhancedClick(findTestObject('PlanDetails/FundName', [('Fund_Name') : 'Maximus']))
	
			WebUI.setText(findTestObject('PlanDetails/FundPercent'), data['Maximus_Fund%'])
	
			WebUI.enhancedClick(findTestObject('PlanDetails/FundOkBtn'))
			
		}
		if(data['Accelerator_Fund%'] != null && data['Accelerator_Fund%'] != '' && data['Accelerator_Fund%'] != '0') {
			
			WebUI.enhancedClick(findTestObject('PlanDetails/AddFundBtn'))
			
			WebUI.enhancedClick(findTestObject('PlanDetails/FundName', [('Fund_Name') : 'Accelerator']))
	
			WebUI.setText(findTestObject('PlanDetails/FundPercent'), data['Accelerator_Fund%'])
	
			WebUI.enhancedClick(findTestObject('PlanDetails/FundOkBtn'))
			
		}
		if(data['Tyaseer_Fund%'] != null && data['Tyaseer_Fund%'] != '' && data['Tyaseer_Fund%'] != '0') {
			
			WebUI.enhancedClick(findTestObject('PlanDetails/AddFundBtn'))
			
			WebUI.enhancedClick(findTestObject('PlanDetails/FundName', [('Fund_Name') : 'Tyaseer fund']))
	
			WebUI.setText(findTestObject('PlanDetails/FundPercent'), data['Tyaseer_Fund%'])
	
			WebUI.enhancedClick(findTestObject('PlanDetails/FundOkBtn'))
			
		}
		if(data['Balancer_Fund%'] != null && data['Balancer_Fund%'] != '' && data['Balancer_Fund%'] != '0') {
			
			WebUI.enhancedClick(findTestObject('PlanDetails/AddFundBtn'))
			
			WebUI.enhancedClick(findTestObject('PlanDetails/FundName', [('Fund_Name') : 'Balancer']))
	
			WebUI.setText(findTestObject('PlanDetails/FundPercent'), data['Balancer_Fund%'])
	
			WebUI.enhancedClick(findTestObject('PlanDetails/FundOkBtn'))
			
		}
		if(data['Guardian_Fund%'] != null && data['Guardian_Fund%'] != '' && data['Guardian_Fund%'] != '0') {
			
			WebUI.enhancedClick(findTestObject('PlanDetails/AddFundBtn'))
			
			WebUI.enhancedClick(findTestObject('PlanDetails/FundName', [('Fund_Name') : 'Guardian']))
	
			WebUI.setText(findTestObject('PlanDetails/FundPercent'), data['Guardian_Fund%'])
	
			WebUI.enhancedClick(findTestObject('PlanDetails/FundOkBtn'))
			
		}
		if(data['Preserver_Fund%'] != null && data['Preserver_Fund%'] != '' && data['Preserver_Fund%'] != '0') {
			
			WebUI.enhancedClick(findTestObject('PlanDetails/AddFundBtn'))
			
			WebUI.enhancedClick(findTestObject('PlanDetails/FundName', [('Fund_Name') : 'Preserver']))
	
			WebUI.setText(findTestObject('PlanDetails/FundPercent'), data['Preserver_Fund%'])
	
			WebUI.enhancedClick(findTestObject('PlanDetails/FundOkBtn'))
			
		}
		if(data['Pension_Protector_Fund%'] != null && data['Pension_Protector_Fund%'] != '' && data['Pension_Protector_Fund%'] != '0') {
			
				WebUI.enhancedClick(findTestObject('PlanDetails/AddFundBtn'))
				
				WebUI.enhancedClick(findTestObject('PlanDetails/FundName', [('Fund_Name') : 'Pension Protector']))
		
				WebUI.setText(findTestObject('PlanDetails/FundPercent'), data['Pension_Protector_Fund%'])
		
				WebUI.enhancedClick(findTestObject('PlanDetails/FundOkBtn'))
				
			}
		if(data['Pension_Balancer_Fund%'] != null && data['Pension_Balancer_Fund%'] != '' && data['Pension_Balancer_Fund%'] != '0') {
			
			WebUI.enhancedClick(findTestObject('PlanDetails/AddFundBtn'))
			
			WebUI.enhancedClick(findTestObject('PlanDetails/FundName', [('Fund_Name') : 'Pension Balancer']))
	
			WebUI.setText(findTestObject('PlanDetails/FundPercent'), data['Pension_Balancer_Fund%'])
	
			WebUI.enhancedClick(findTestObject('PlanDetails/FundOkBtn'))
			
		}
		if(data['Pension_maximiser_Fund%'] != null && data['Pension_maximiser_Fund%'] != '' && data['Pension_maximiser_Fund%'] != '0') {
			
			WebUI.enhancedClick(findTestObject('PlanDetails/AddFundBtn'))
			
			WebUI.enhancedClick(findTestObject('PlanDetails/FundName', [('Fund_Name') : 'Pension Maximiser']))
	
			WebUI.setText(findTestObject('PlanDetails/FundPercent'), data['Pension_maximiser_Fund%'])
	
			WebUI.enhancedClick(findTestObject('PlanDetails/FundOkBtn'))
			
		}
		if(data['Pension_MultiCap_Fund%'] != null && data['Pension_MultiCap_Fund%'] != '' && data['Pension_MultiCap_Fund%'] != '0') {
			
			WebUI.enhancedClick(findTestObject('PlanDetails/AddFundBtn'))
			
			WebUI.enhancedClick(findTestObject('PlanDetails/FundName', [('Fund_Name') : 'Pension Multi Cap Aggressive Fund']))
	
			WebUI.setText(findTestObject('PlanDetails/FundPercent'), data['Pension_MultiCap_Fund%'])
	
			WebUI.enhancedClick(findTestObject('PlanDetails/FundOkBtn'))
			
		}
		
	}
	
}
