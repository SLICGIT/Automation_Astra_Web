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
//import com.kms.katalon.core.testdata.TestDataFactory
import org.apache.poi.ss.usermodel.*
import org.apache.poi.xssf.usermodel.XSSFWorkbook
//import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testdata.reader.ExcelFactory

public class FetchExcelData {

	@Keyword
	static Map<String, String> getData(String sheetName, String testcaseID) {
		FileInputStream fis = new FileInputStream(new File(GlobalVariable.testDataPath))
		Workbook workbook = new XSSFWorkbook(fis)
		Sheet sheet = workbook.getSheet(sheetName)

		Map<String, String> rowData = [:]
		Row headerRow = sheet.getRow(0)
		int colCount = headerRow.getLastCellNum()

		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			Row row = sheet.getRow(i)
			if (row.getCell(0).getStringCellValue() == testcaseID) {
				for (int j = 0; j < colCount; j++) {
					String colName = headerRow.getCell(j).getStringCellValue()
					String value = row.getCell(j)?.toString()
					rowData[colName] = value
				}
				break
			}
		}
		workbook.close()
		return rowData
	}


	@Keyword
	static List<Map<String, String>> getExecutableRowsFromExcel(String filePath, String sheetName, String executorColName = 'Executor', String yesValue = 'Yes') {

		def td = ExcelFactory.getExcelDataWithDefaultSheet(filePath, sheetName, true)
		List<String> colNames = td.getColumnNames()
		int executorIdx = colNames.indexOf(executorColName)
		if (executorIdx == -1) {
			throw new IllegalArgumentException("Column not found: " + executorColName)
		}
		int executorCol = executorIdx + 1

		int rows = td.getRowNumbers()
		List<Map<String, String>> out = []
		for (int r = 1; r <= rows; r++) {
			String flag = (td.getValue(executorCol, r) ?: '').trim()
			if (flag.equalsIgnoreCase(yesValue)) {
				Map<String, String> rowMap = [:]
				for (int c = 0; c < colNames.size(); c++) {
					rowMap[colNames[c]] = td.getValue(c + 1, r)
				}
				out.add(rowMap)
			}
		}
		return out
	}
}
