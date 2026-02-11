import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject

import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile

import internal.GlobalVariable as GlobalVariable

import com.kms.katalon.core.annotation.BeforeTestCase
import com.kms.katalon.core.annotation.BeforeTestSuite
import com.kms.katalon.core.annotation.AfterTestCase
import com.kms.katalon.core.annotation.AfterTestSuite
import com.kms.katalon.core.context.TestCaseContext
import com.kms.katalon.core.context.TestSuiteContext
import methods.FetchExcelData
import methods.WriteExcel

class ExecutionListener {
	
	long startTime
	
	@BeforeTestCase
	def setGlobalVariablesBeforeEachCase() {
		
		GlobalVariable.Status = ""
		GlobalVariable.Actual = ""
		GlobalVariable.ProposalNo = ""
		GlobalVariable.basePremAmount = ""
		GlobalVariable.totalAmount = ""
		GlobalVariable.SAAmount = ""
		GlobalVariable.APAmount = ""
		GlobalVariable.SSCount = 1
		GlobalVariable.ExecutionTime = ""
		GlobalVariable.testcaseID = ""
		GlobalVariable.planName = ""
		GlobalVariable.userID = ""
		GlobalVariable.screenshotDir = "E:/Katalon/SLIC_Web/SLIC_Web/Screenshot/"
		startTime = System.currentTimeMillis()

	}
	
	@AfterTestCase
	def afterCase(TestCaseContext testCaseContext) {
		
		Map flag = FetchExcelData.getData("Login_Page", GlobalVariable.testcaseID)
		if(flag['Executor'].toString().equalsIgnoreCase("Yes")) {
		GlobalVariable.Status = testCaseContext.getTestCaseStatus()
		long endTime = System.currentTimeMillis()
		long elapsedTime = (endTime - startTime) / 1000
		
		long minutes = elapsedTime / 60
		long seconds = elapsedTime % 60
		
		GlobalVariable.ExecutionTime = "${minutes} min ${seconds} sec"
			
		Map data = FetchExcelData.getData("Plan_Details_Page", GlobalVariable.testcaseID)
//		Map flag = FetchExcelData.getData("Login_Page", GlobalVariable.testcaseID)
//		if(flag['Executor'].toString().equalsIgnoreCase("Yes")) {
			if(data['TestCase_Desc'].toString().equalsIgnoreCase("sanity")) {
				if(GlobalVariable.ProposalNo != "") {
					WriteExcel.writeSanityReport()
				}
			} else {
				WriteExcel.writeReportRow(GlobalVariable.testcaseID, GlobalVariable.userID, GlobalVariable.planName, data['TestCase_Desc'], data['TestCase_Type'], data['Expected_Result'])
			}
		}	
	}	
			
}