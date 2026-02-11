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
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Random


public class ConvertAge {

	@Keyword
	static def convertAgetoDOB(String age) {

		int ageint = age.toInteger()
		float agefloat = age.toFloat()

		LocalDate today = LocalDate.now()
		LocalDate dob

		if (ageint == 0) {

			dob = today//.minusDays(30)
			
		} else if (agefloat == 0.1) {
			
			dob = today.minusDays(30)
			
		} else {

			// Calculate birth year
			int birthYear = today.getYear() - ageint

			// Generate random month and day
			Random rand = new Random()
			int month = rand.nextInt(12) + 1
			int day = rand.nextInt(28) + 1 // Keep within 28 to avoid invalid dates

			// Create DOB
			dob = LocalDate.of(birthYear, month, day)

			if (dob.isAfter(today.minusYears(ageint))) {
				dob = dob.minusYears(1)
			}
		}

		// Format DOB
		String formattedDOB = dob.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))

		return formattedDOB
	}
}
