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

import java.nio.file.*

import internal.GlobalVariable

public class downloadBI {

	@Keyword
	static def getBI(String BI_Required) {

		Map data = FetchExcelData.getData("Home_Page", GlobalVariable.testcaseID) // Fetching data from excel and storing in map variable.

		// Define directories
		String DOWNLOAD_DIR = "C:/Users/qk user2/Downloads"
		//		String TARGET_DIR = "D:/Katalon/SLIC_Web/SLIC_Web/Screenshot/${GlobalVariable.date}/$planName/${tcID}"

		// Get list of files before download
		List<String> beforeFiles = new File(DOWNLOAD_DIR).list() as List

		if(WebUI.waitForElementPresent(findTestObject('PlanDetails/BIDownloadBtn2', [('BI_Required') : BI_Required]), 2)) {
			// Perform UI actions
			WebUI.enhancedClick(findTestObject('PlanDetails/BIDownloadBtn2', [('BI_Required') : BI_Required]))
			//	WebUI.verifyElementPresent(findTestObject("//*[contains(text(),'Downloaded BI Successfully')]"), 20)
			WebUI.waitForElementPresent(findTestObject('PlanDetails/DownloadedBIMessage'), 30)
			WebUI.delay(1)
			WebUI.click(findTestObject('PlanDetails/BIDownloadOK2'))
		} else {
			// Perform UI actions
			WebUI.enhancedClick(findTestObject('PlanDetails/BIDownloadBtn', [('BI_Required') : BI_Required]))
			//	WebUI.verifyElementPresent(findTestObject("//*[contains(text(),'Downloaded BI Successfully')]"), 20)
			WebUI.waitForElementPresent(findTestObject('PlanDetails/DownloadedBIMessage'), 30)
			WebUI.delay(1)
			WebUI.click(findTestObject('PlanDetails/BIDownloadOK'))
		}

		// Get list of files after download
		List<String> afterFiles = new File(DOWNLOAD_DIR).list() as List

		// Find new files
		Set<String> newFiles = afterFiles.toSet() - beforeFiles.toSet()

		if (newFiles.size() > 0) {
			String pdfFile = newFiles[0] // Assuming only one new file
			Path source = Paths.get("${DOWNLOAD_DIR}/${pdfFile}")
			Path target = Paths.get("${GlobalVariable.screenshotDir}/${pdfFile}")
			//			Path target = Paths.get("${TARGET_DIR}/${pdfFile}")

			// Create target directory if not exists
			//		Files.createDirectories(Paths.get(TARGET_DIR))

			// Move file
			Files.move(source, target, StandardCopyOption.REPLACE_EXISTING)
			println "File moved: ${pdfFile}"
		} else {
			println "No new file found!"
		}
	}

	@Keyword
	static def checkforBIDownload(String BI_Required) {

		if (BI_Required == 'Yes') {
			downloadBI.getBI(BI_Required)
		} else {
			Map data = FetchExcelData.getData("Home_Page", GlobalVariable.testcaseID)
			if(WebUI.waitForElementPresent(findTestObject('PlanDetails/BIDownloadBtn2', [('BI_Required') : BI_Required]), 1)) {
				WebUI.enhancedClick(findTestObject('PlanDetails/BIDownloadBtn2', [('BI_Required') : BI_Required]))
			} else {
				WebUI.enhancedClick(findTestObject('PlanDetails/BIDownloadBtn', [('BI_Required') : BI_Required]))
			}
		}
	}
}
