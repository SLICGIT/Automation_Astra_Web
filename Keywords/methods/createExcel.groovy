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
import java.nio.file.*

import internal.GlobalVariable

public class createExcel {

	@Keyword
	static def generateReport() {

		// Define file path
		def filePath = GlobalVariable.reportPath + "${GlobalVariable.date}/${GlobalVariable.planName}/Test_Report.xlsx"
		def file = new File(filePath)

		// Create directory if not exists
		file.parentFile.mkdirs()

		if (!file.exists()) {

			// Create workbook and sheet
			def workbook = new XSSFWorkbook()
			def sheet = workbook.createSheet("Report")

			// Create header style
			def headerFont = workbook.createFont()
			headerFont.setBold(true)
			headerFont.setColor(IndexedColors.WHITE.getIndex())

			def headerStyle = workbook.createCellStyle()
			headerStyle.setFont(headerFont)
			headerStyle.setFillForegroundColor(IndexedColors.BLUE.getIndex())
			headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND)

			// Create header row
			def headers = [
				"Qrace_TestCase_ID",
				"User_ID",
				"Proposal_No",
				"Plan Name",
				"Test Case Description",
				"Test Case Type",
				"Expected Result",
				"Actual Result",
				"Status",
				"Date & Time",
				"Execution Duration",
				"Screenshot Path",
				"Base Premium",
				"Total Premium",
				"Sum Assured",
				"Annualized Premium"
			]
			def headerRow = sheet.createRow(0)
			headers.eachWithIndex { header, idx ->
				def cell = headerRow.createCell(idx)
				cell.setCellValue(header)
				cell.setCellStyle(headerStyle)
			}

			// Auto-size columns for better readability
			headers.size().times { sheet.autoSizeColumn(it) }

			// Write workbook to file
			def outputStream = new FileOutputStream(file)
			workbook.write(outputStream)
			outputStream.close()
			workbook.close()

			println "Excel report generated at: ${filePath}"
		}
	}
}
