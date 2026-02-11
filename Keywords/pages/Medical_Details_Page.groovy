package pages

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
import org.openqa.selenium.interactions.Actions as Actions
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.By as By
import org.openqa.selenium.WebDriver as WebDriver
import methods.dropdowns
import methods.FetchExcelData
import methods.CaptureScreenshot as SS

public class Medical_Details_Page {

	@Keyword
	static def fillMedicalDetails(String testcaseID) {

		Map data = FetchExcelData.getData("Medical_Details_Page", testcaseID)
		Map LAdata = FetchExcelData.getData("LA_Details_Page", testcaseID)
		WebDriver driver = DriverFactory.getWebDriver()
		Actions actions = new Actions(driver)

		WebUI.waitForPageLoad(GlobalVariable.pageTimeout)

		WebUI.setText(findTestObject('Medicals Details/Weight'), data['Weight'])

		WebUI.setText(findTestObject('Medicals Details/HeightCms'), data['Height'])

		if(GlobalVariable.planName.toString().startsWith(data['GH_PlanName'])) {

			WebUI.enhancedClick(findTestObject('Medicals Details/GoodHealth', ['Qu_GoodHealth' : data['Qu_GoodHealth']]))
			if(LAdata['LA_Gender'] == 'Female') {
				WebUI.enhancedClick(findTestObject('Medicals Details/GynDisorderQuestion', ['Qu_GynDisorder' : data['Qu_GynDisorder']]))
			}
		} else {

			if(!GlobalVariable.planName.toString().startsWith(data['Health_PlanName'])) {
				WebUI.enhancedClick(findTestObject('Medicals Details/SmokeQuestion', ['Qu_Smoke' : data['Qu_Smoke']]))

				if(data['Qu_Smoke'] == 'Yes') {
					dropdowns.selectAntDropdown('SmTy', data['Smoke_Type'])
					WebUI.setText(findTestObject('Medicals Details/SmokeQuantity'), data['Smoke_Quantity'])
				}
			}
			
			if(GlobalVariable.planName.toString().equalsIgnoreCase("shriram life flexi shield")) {
				WebUI.enhancedClick(findTestObject('Medicals Details/WeightlossGainBtn'))
				WebUI.enhancedClick(findTestObject('Medicals Details/SuicideAttempt'))
			}

			WebUI.enhancedClick(findTestObject('Medicals Details/AlcoholQuestion', ['Qu_Alcohol' : data['Qu_Alcohol']]))

			if(data['Qu_Alcohol'] == 'Yes') {
				dropdowns.selectAntDropdown('AlcohTy', data['Alcohol_Type'])
				WebUI.setText(findTestObject('Medicals Details/AlcoholQuantity'), data['Alcohol_Quantity'])
			}

			WebUI.enhancedClick(findTestObject('Medicals Details/DeformityQuestion', ['Qu_Deformity' : data['Qu_Deformity']]))

			if(data['Qu_Deformity'] == 'Yes') {
				dropdowns.selectAntDropdown('DefTy', data['Deformity_Type'])
				dropdowns.selectAntDropdown('PerDis', data['Disability%'])
				dropdowns.selectAntDropdown('Reason1', data['Deformity_Reason'])
				WebUI.enhancedClick(findTestObject('Medicals Details/AidWalkBtn', ['Aid_Walk' : data['Aid_Walk']]))
				dropdowns.selectAntDropdown('LimbAff', data['Limbs_Affected'])
			}

			WebUI.enhancedClick(findTestObject('Medicals Details/DrugsQuestion', ['Qu_Drugs' : data['Qu_Drugs']]))

			if(data['Qu_Drugs'] == 'Yes') {
				WebUI.setText(findTestObject('Medicals Details/DrugDetails'), data['Drug_Details'])
			}

			WebUI.enhancedClick(findTestObject('Medicals Details/LeaveQuestion', ['Qu_Leave' : data['Qu_Leave']]))

			if(data['Qu_Leave'] == 'Yes') {
				WebUI.setText(findTestObject('Medicals Details/LeaveDetails'), data['Leave_Details'])
			}

			WebUI.enhancedClick(findTestObject('Medicals Details/MedicalTreatmentQuestion', ['Qu_MedicalTreatment' : data['Qu_MedicalTreatment']]))

			if(data['Qu_MedicalTreatment'] == 'Yes') {
				WebUI.setText(findTestObject('Medicals Details/MedicalTreatmentDetails'), data['MedTreat_Details'])
			}

			WebUI.enhancedClick(findTestObject('Medicals Details/AilmentsQuestion', ['Qu_Ailments' : data['Qu_Ailments']]))

			if(data['Qu_Ailments'] == 'Yes') {
				WebUI.setText(findTestObject('Medicals Details/AilmentDetails'), data['Ailment_Details'])
			}

			WebUI.enhancedClick(findTestObject('Medicals Details/HIVQuestion', ['Qu_Disease' : data['Qu_Disease']]))

			if(data['Qu_Disease'] == 'Yes') {
				WebUI.setText(findTestObject('Medicals Details/HIVDetails'), data['Disease_Details'])
			}

			WebUI.enhancedClick(findTestObject('Medicals Details/RespiratoryQuestion', ['Qu_Disorders' : data['Qu_Disorders']]))

			if(data['Qu_Disorders'] == 'Yes') {
				WebUI.setText(findTestObject('Medicals Details/RespiratoryDetails'), data['Disorder_Details'])
			}

			WebUI.enhancedClick(findTestObject('Medicals Details/BPDiaQuestion', ['Qu_Diabetes' : data['Qu_Diabetes']]))

			if(data['Qu_Diabetes'] == 'Yes') {
				WebUI.setText(findTestObject('Medicals Details/BPDiaDetails'), data['Diabetes_Details'])
			}

			WebUI.enhancedClick(findTestObject('Medicals Details/OtherIllQuestion', ['Qu_Other_Illness' : data['Qu_Other_Illness']]))

			if(data['Qu_Other_Illness'] == 'Yes') {
				WebUI.setText(findTestObject('Medicals Details/OtherIllDetails'), data['OtherIll_Details'])
			}

			WebUI.enhancedClick(findTestObject('Medicals Details/EarEyesDisorderQuestion', ['Qu_EyeDisorder' : data['Qu_EyeDisorder']]))

			if(data['Qu_EyeDisorder'] == 'Yes') {
				WebUI.setText(findTestObject('Medicals Details/EarEyesDisorderDetails'), data['EyeDisorder_Details'])
			}

			WebUI.enhancedClick(findTestObject('Medicals Details/HospitalIlQuestion', ['Qu_Hospitalize' : data['Qu_Hospitalize']]))

			if(data['Qu_Hospitalize'] == 'Yes') {
				WebUI.setText(findTestObject('Medicals Details/HospitalIlDetails'), data['Hospitalize_Details'])
			}

			WebUI.enhancedClick(findTestObject('Medicals Details/AnaemiaQuestion', ['Qu_Anaemia' : data['Qu_Anaemia']]))

			if(data['Qu_Anaemia'] == 'Yes') {
				WebUI.setText(findTestObject('Medicals Details/AnaemiaDetails'), data['Anaemia_Details'])
			}

			if (LAdata['LA_Gender'] == 'Female' && LAdata['LA_Marital_Status'] == 'Married') {

				WebUI.enhancedClick(findTestObject('Medicals Details/PregnencyQuestion', ['Qu_Pregnant' : data['Qu_Pregnant']]))

				if(data['Qu_Pregnant'] == 'Yes') {
					WebUI.setText(findTestObject('Medicals Details/PregnancyDetails'), data['Pregnancy_Weeks'])
				}

				WebUI.enhancedClick(findTestObject('Medicals Details/MisscarriageQuestion', ['Qu_Abortion' : data['Qu_Abortion']]))

				if(data['Qu_Abortion'] == 'Yes') {
					WebUI.setText(findTestObject('Medicals Details/AbortionDetails'), data['Abortion_Date'])
					actions.sendKeys(Keys.ENTER).perform()
				}

				WebUI.enhancedClick(findTestObject('Medicals Details/DeliveredBabyQuestion', ['Qu_Delivery' : data['Qu_Delivery']]))

				if(data['Qu_Delivery'] == 'Yes') {
					WebUI.setText(findTestObject('Medicals Details/DeliveryDetails'), data['Delivery_Date'])
					actions.sendKeys(Keys.ENTER).perform()
				}

				WebUI.enhancedClick(findTestObject('Medicals Details/GynProblemQuestion', ['Qu_GynTreatment' : data['Qu_GynTreatment']]))

				if(data['Qu_GynTreatment'] == 'Yes') {
					WebUI.setText(findTestObject('Medicals Details/GynProblemDetails'), data['GynTreat_Details'])
				}
			}
		}

		SS.capture("Medical_page")

		WebUI.enhancedClick(findTestObject('Medicals Details/Next'))

		GlobalVariable.Actual += " | Medical Details Filled Successfully"
	}
}
