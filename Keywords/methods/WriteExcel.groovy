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


import org.apache.poi.ss.usermodel.*
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.apache.poi.common.usermodel.HyperlinkType

import internal.GlobalVariable

public class WriteExcel {

	@Keyword
	static def writeReportRow(String TestCase_ID, String User_ID, String Plan_Name, String Test_Desc, String Test_Type, String Expected) {

		def filePath = GlobalVariable.reportPath + "${GlobalVariable.date}/${GlobalVariable.planName}/Test_Report.xlsx"
		def file = new File(filePath)

		//	file.parentFile.mkdirs() // Ensure directory exists

		// Open existing workbook
		def inputStream = new FileInputStream(file)
		Workbook workbook = new XSSFWorkbook(inputStream)
		//	def sheet = workbook.getSheet("Report")
		inputStream.close()

		def sheet = workbook.getSheet("Report")
		if (sheet == null) {
			sheet = workbook.createSheet("Report")
		}

		// Prepare data row

		def nextRowIndex = (sheet.getLastRowNum() == 0 && sheet.getRow(0) == null) ? 0 : sheet.getLastRowNum() + 1
		def row = sheet.createRow(nextRowIndex)

		def data = [
			TestCase_ID,
			User_ID,
			GlobalVariable.ProposalNo,
			Plan_Name,
			Test_Desc,
			Test_Type,
			Expected,
			GlobalVariable.Actual,
			GlobalVariable.Status,
			GlobalVariable.date + "_" + GlobalVariable.time,
			GlobalVariable.ExecutionTime,
			GlobalVariable.screenshotDir,
			GlobalVariable.basePremAmount,
			GlobalVariable.totalAmount,
			GlobalVariable.SAAmount,
			GlobalVariable.APAmount
		]
		data.eachWithIndex { value, idx ->
			row.createCell(idx).setCellValue(value ?: "")
		}

		// Save workbook
		def outputStream = new FileOutputStream(file)
		workbook.write(outputStream)
		outputStream.close()
		workbook.close()

		println "Row appended successfully to: ${filePath}"
	}

	@Keyword
	static def writeSanityReport() {

		def filePath = "D:/Katalon/SLIC_Web/SLIC_Web/Reports/Sanity_Report.xlsx"
		def file = new File(filePath)

		Workbook workbook
		def sheet
		if (!file.exists()) {
			// If file does not exist, create a new workbook and sheet
			workbook = new XSSFWorkbook()
			sheet = workbook.createSheet("Report")
			println "File not found. Creating new file at: ${filePath}"

			// Create header row
			def headerRow = sheet.createRow(0)
			def headers = [
				"Test Case ID",
				"Plan Name",
				"Proposal No"
			]
			headers.eachWithIndex { header, idx ->
				headerRow.createCell(idx).setCellValue(header)
			}
		} else {
			// Open existing workbook
			def inputStream = new FileInputStream(file)
			workbook = new XSSFWorkbook(inputStream)
			inputStream.close()
		}

		sheet = workbook.getSheet("Report")
		if (sheet == null) {
			sheet = workbook.createSheet("Report")
		}

		// Prepare data row

		def nextRowIndex = (sheet.getLastRowNum() == 0 && sheet.getRow(0) == null) ? 0 : sheet.getLastRowNum() + 1
		def row = sheet.createRow(nextRowIndex)

		def data = [
			GlobalVariable.testcaseID,
			GlobalVariable.planName,
			GlobalVariable.ProposalNo
		]
		data.eachWithIndex { value, idx ->
			row.createCell(idx).setCellValue(value ?: "")
		}

		// Save workbook
		def outputStream = new FileOutputStream(file)
		workbook.write(outputStream)
		outputStream.close()
		workbook.close()

		println "Row appended successfully to: ${filePath}"
	}
}
