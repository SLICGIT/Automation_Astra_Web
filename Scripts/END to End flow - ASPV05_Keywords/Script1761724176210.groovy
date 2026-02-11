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
import methods.WriteExcel
import methods.createExcel
import methods.dropdowns


if (Executor == 'Yes' || Executor == 'yes') {
	
	try {
		
		GlobalVariable.Status = "FAIL"
		GlobalVariable.Actual = "Proposal Creation Failed"
		GlobalVariable.ProposalNo = ""
		GlobalVariable.basePremAmount = ""
		GlobalVariable.totalAmount = ""
		GlobalVariable.SAAmount = ""
		GlobalVariable.APAmount = ""

		WebUI.openBrowser(GlobalVariable.url)
		
		WebUI.waitForPageLoad(10)
		
		WebUI.maximizeWindow()
		
		screenshotDir()
		
		WebUI.setText(findTestObject('Login/User ID'), Username)
		
		WebUI.setText(findTestObject('Login/Password'), Password)
		
		WebUI.setText(findTestObject('Login/EnterCaptcha'), '1234')
		
//		JOptionPane.showMessageDialog(null, 'Click OK to continue')
		
		WebUI.takeScreenshot(GlobalVariable.screenshotDir + '/1_LoginPage.jpg')
		
//		generateReport()
		
		CustomKeywords.'methods.createExcel.generateReport'()

		WebUI.enhancedClick(findTestObject('Login/Login'))
		
		WebUI.enhancedClick(findTestObject('HomePage/Menu'))
		
		WebUI.enhancedClick(findTestObject('HomePage/ProposalJourney'))
		
		WebUI.waitForElementClickable(findTestObject('HomePage/NewProposal'), 0)
		
		WebUI.enhancedClick(findTestObject('HomePage/NewProposal'))
		
		WebUI.waitForElementPresent(findTestObject('HomePage/ProposalType'), 20)
		
		WebUI.enhancedClick(findTestObject('HomePage/ProposalType'))
		
		WebUI.enhancedClick(findTestObject('HomePage/PolicyType'))
		
		WebUI.enhancedClick(findTestObject('HomePage/LifeType', [('Life_Type') : Life_Type]))
		
		WebUI.enhancedClick(findTestObject('HomePage/KYCType'))
		
		WebUI.enhancedClick(findTestObject('HomePage/Continue'))
		
		// 1️⃣ Get WebDriver from Katalon
		// 2️⃣ Define explicit wait
		// 3️⃣ Click the dropdown to open it
		// 4️⃣ Wait for the dropdown list to appear
		// 6️⃣ Optional: Verify selection
		//String selectedText = driver.findElement(By.cssSelector("div[name='" + dropdownName + "'] span.ant-select-selection-item")).getText()
		//println("✅ Selected dropdown value for '${dropdownName}': ${selectedText}")
		dropdowns.selectAntDropdown('LATitle', 'Dr.')
		
		WebUI.setText(findTestObject('CustomerDetails/FirstName'), First_Name)
		
		WebUI.setText(findTestObject('CustomerDetails/LastName'), Last_Name)
		
		WebUI.setText(findTestObject('CustomerDetails/DOB'), DOB)
		
		WebUI.delay(1)
		
		//WebUI.waitForElementClickable(findTestObject('CustomerDetails/Gender'), 20)
		WebUI.enhancedClick(findTestObject('CustomerDetails/Gender', [('Gender') : Gender]))
		
		WebUI.waitForElementClickable(findTestObject('CustomerDetails/MaritalStatus'), 20)
		
		WebUI.enhancedClick(findTestObject('CustomerDetails/MaritalStatus'))
		
		WebUI.setText(findTestObject('CustomerDetails/FatherSpousename'), Father_Name)
		
		WebUI.setText(findTestObject('CustomerDetails/Aadhar'), Aadhar)
		
		WebUI.setText(findTestObject('CustomerDetails/PANnumber'), PAN)
		
		dropdowns.selectAntDropdown('LAAnInR', '50001-100000')
		
		WebUI.setText(findTestObject('CustomerDetails/AnnualIncome'), Annual_Income)
		
		dropdowns.selectAntDropdown('LAPL', 'Hindi')
		
		dropdowns.selectAntDropdown('LAEduQual', 'Graduation')
		
		dropdowns.selectAntDropdown('LAOBJINS', 'Wealth Creation')
		
		WebUI.enhancedClick(findTestObject('CustomerDetails/PEP'))
		
		dropdowns.selectAntDropdown('LARisk_App', 'Medium')
		
		if (Life_Type == 'Own Life') {
		
			WebUI.enhancedClick(findTestObject('CustomerDetails/Disability'))
			
		}
		
		dropdowns.selectAntDropdown('Occ', 'Salaried Employee')
		
		dropdowns.selectAntDropdown('SubOccSalEmp', 'Teacher')
		
		WebUI.enhancedClick(findTestObject('CustomerDetails/EIA'))
		
		dropdowns.selectAntDropdown('Nationality', 'Indian')
		
		WebUI.enhancedClick(findTestObject('CustomerDetails/EIA'))
		
		WebUI.enhancedClick(findTestObject('CustomerDetails/Next'))
		
		if (Life_Type == 'Other Life') {
			
			WebUI.waitForPageLoad(20)
			
			dropdowns.selectAntDropdown('TPR1', 'Mr.')
			
			WebUI.setText(findTestObject('ProposerDetails/FirstName'), Prop_FirstName)
			
			WebUI.setText(findTestObject('ProposerDetails/LastName'), Prop_LastName)
			
			WebUI.setText(findTestObject('ProposerDetails/ProposerDOB'), Prop_DOB)
			
			dropdowns.selectAntDropdown('PropRelLA', 'Father')
			
			WebUI.setText(findTestObject('ProposerDetails/PAN'), Prop_PAN)
			
			dropdowns.selectAntDropdown('PropEDuQual', 'Graduation')
			
			WebUI.setText(findTestObject('ProposerDetails/Occupation'), Prop_Occupation)
			
			WebUI.setText(findTestObject('ProposerDetails/AnnualIncome'), Prop_Income)
			
			dropdowns.selectAntDropdown('NT1', 'Indian')
			
			WebUI.setText(findTestObject('ProposerDetails/Mobile'), Mobile_Number)
			
			WebUI.enhancedClick(findTestObject('ProposerDetails/VerifyMobile'))
			
			addWait()
			
			WebUI.enhancedClick(findTestObject('ProposerDetails/DisabilityNo'))
			
			WebUI.enhancedClick(findTestObject('ProposerDetails/Next'))
			
		}
		
		WebUI.waitForPageLoad(20)
		
		WebUI.setText(findTestObject('AddressDetails/Doornumber'), Door_No)
		
		WebUI.setText(findTestObject('AddressDetails/Street'), Street)
		
		WebUI.setText(findTestObject('AddressDetails/Pincode'), Pincode)
		
		//WebUI.waitForPageLoad(20, FailureHandling.STOP_ON_FAILURE)
		addWait()
		
		dropdowns.selectAntDropdown('PerArea', 'Alwal')
		
		WebUI.enhancedClick(findTestObject('AddressDetails/CommAddressQues'))
		
		WebUI.setText(findTestObject('AddressDetails/LAMobilenumber'), Mobile_Number)
		
		WebUI.enhancedClick(findTestObject('AddressDetails/Verify'))
		
		addWait()
		
//		WebUI.getText(findTestObject('AddressDetails/Mobileverification'))
//		
//		WebUI.verifyMatch('Mobile Number Verified Successfully', 'Mobile Number Verified Successfully', false)
		
		WebUI.enhancedClick(findTestObject('AddressDetails/whatsappQues'))
		
		WebUI.setText(findTestObject('AddressDetails/EmailID'), EmaiID)
		
		WebUI.enhancedClick(findTestObject('AddressDetails/Next'))
		
		WebUI.waitForPageLoad(20)
		
		WebUI.enhancedClick(findTestObject('chooseproduct/ProductName', [('Plan_Name') : Plan_Name]))
		
		dropdowns.selectAntDropdown('AgeProofType', 'PAN Card - Standard')
			
		WebUI.callTestCase(findTestCase('Test Cases/Plan Details Page/ASPV05'),
			[
							('Life_Cover') : Life_Cover,
							('Maturity_Benefit') : Maturity_Benefit,
							('Death_Benefit') : Death_Benefit,
							('Frequency') : Frequency,
							('PT') : PT,
							('PPT') : PPT,
							('Premium') : Premium,
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
		
		if (BI_Required == 'Yes') {
		
			CustomKeywords.'methods.downloadBI.getBI'()
			
		} else {
		
			WebUI.enhancedClick(findTestObject('PlanDetails/BIDownloadBtn', [('BI_Required') : BI_Required]))
			
		}
		
		WebUI.enhancedClick(findTestObject('SourceDetails/PolicySourcebyMeYes'))
		
		dropdowns.selectAntDropdown('SourceBy1', 'Direct')
		
		//selectAntDropdown('SourceCode1', 'J02076-JEET YOGESH KANSARA\t')
		WebDriver driver = DriverFactory.getWebDriver()
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10))
		
		WebElement sourecCodeDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(('div[name=\'' + 'SourceCode1') + 
		            '\']')))
		
		sourecCodeDropdown.click()
		
		WebElement SourceCodeOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(('//div[contains(@class,\'ant-select-item-option-content\') and contains(text(),\'' + 
		            Username) + '\')]')))
		
		SourceCodeOption.click()
		
		//selectAntDropdown('SourceCode1', Username)
		WebUI.enhancedClick(findTestObject('SourceDetails/Next'))
		
		WebUI.enhancedClick(findTestObject('SourceDetails/Confirm'))
		
		WebUI.setText(findTestObject('Medicals Details/Weight'), Weight)
		
		WebUI.setText(findTestObject('Medicals Details/HeightCms'), Height)
		
		WebUI.enhancedClick(findTestObject('Medicals Details/SmokeQuestion'))
		
		WebUI.enhancedClick(findTestObject('Medicals Details/AlcoholQuestion'))
		
		WebUI.enhancedClick(findTestObject('Medicals Details/DeformityQuestion'))
		
		WebUI.enhancedClick(findTestObject('Medicals Details/DrugsQuestion'))
		
		WebUI.enhancedClick(findTestObject('Medicals Details/LeaveQuestion'))
		
		WebUI.enhancedClick(findTestObject('Medicals Details/MedicalTreatmentQuestion'))
		
		WebUI.enhancedClick(findTestObject('Medicals Details/AilmentsQuestion'))
		
		WebUI.enhancedClick(findTestObject('Medicals Details/HIVQuestion'))
		
		WebUI.enhancedClick(findTestObject('Medicals Details/RespiratoryQuestion'))
		
		WebUI.enhancedClick(findTestObject('Medicals Details/BPDiaQuestion'))
		
		WebUI.enhancedClick(findTestObject('Medicals Details/OtherIllQuestion'))
		
		WebUI.enhancedClick(findTestObject('Medicals Details/EarEyesDisorderQuestion'))
		
		WebUI.enhancedClick(findTestObject('Medicals Details/HospitalIlQuestion'))
		
		WebUI.enhancedClick(findTestObject('Medicals Details/AnaemiaQuestion'))
		
		if (Gender == 'Female') {
		    WebUI.enhancedClick(findTestObject('Medicals Details/PregnencyQuestion'))
		
		    WebUI.enhancedClick(findTestObject('Medicals Details/MisscarriageQuestion'))
		
		    WebUI.enhancedClick(findTestObject('Medicals Details/DeliveredBabyQuestion'))
		
		    WebUI.enhancedClick(findTestObject('Medicals Details/GynProblemQuestion'))
		
//		    WebUI.setText(findTestObject('Medicals Details/GynoProblemDetails'), 'Test')
		}
		
		WebUI.enhancedClick(findTestObject('Medicals Details/Next'))
		
		WebUI.waitForPageLoad(10)
		
		WebUI.delay(0.5)
		
		WebUI.enhancedClick(findTestObject('OtherDetails/otherDetailsQuestion1'))
		
		WebUI.enhancedClick(findTestObject('OtherDetails/otherDetailsQuestion2'))
		
		WebUI.enhancedClick(findTestObject('OtherDetails/OtherDetailsQuestion3No'))
		
		WebUI.enhancedClick(findTestObject('OtherDetails/OtherDetailsQuestion4No'))
		
		WebUI.enhancedClick(findTestObject('OtherDetails/FamilyHistory/Addmember'))
		
		WebUI.setText(findTestObject('OtherDetails/FamilyHistory/Name'), Family1_Name //String optionXpath = ('//div[contains(@class,\'ant-select-item-option-content\') and normalize-space(text())=\'' + valueToSelect) + 
		    )
		
		WebUI.setText(findTestObject('OtherDetails/FamilyHistory/Age'), Family1_Age)
		
		dropdowns.selectAntDropdown('status', 'Alive')
		
		dropdowns.selectAntDropdown('relationship', Family1_Name)
		
		dropdowns.selectAntDropdown('healthStatus', 'Good')
		
		WebUI.enhancedClick(findTestObject('OtherDetails/FamilyHistory/Done'), FailureHandling.STOP_ON_FAILURE)
		
		WebUI.enhancedClick(findTestObject('OtherDetails/FamilyHistory/Addmember'))
		
		WebUI.setText(findTestObject('OtherDetails/FamilyHistory/Name'), Family2_Name //String optionXpath = ('//div[contains(@class,\'ant-select-item-option-content\') and normalize-space(text())=\'' + valueToSelect) + 
		    )
		
		//String optionXpath = ('//div[contains(@class,\'ant-select-item-option-content\') and normalize-space(text())=\'' + valueToSelect) + 
		WebUI.setText(findTestObject('OtherDetails/FamilyHistory/Age'), Family2_Age)
		
		dropdowns.selectAntDropdown('status', 'Alive')
		
		dropdowns.selectAntDropdown('relationship', Family2_Name)
		
		dropdowns.selectAntDropdown('healthStatus', 'Good')
		
		WebUI.enhancedClick(findTestObject('OtherDetails/FamilyHistory/Done'))
		
		WebUI.enhancedClick(findTestObject('OtherDetails/FamilyHistory/Next'))
		
//		selectAntDropdown('Salut', 'Mrs.')
//		
//		WebUI.setText(findTestObject('NomineeDetails/Firstname'), Nominee_Name)
//		
//		WebUI.setText(findTestObject('NomineeDetails/DOB'), Nominee_DOB)
//		
//		WebUI.waitForPageLoad(10)
//		
//		WebUI.enhancedClick(findTestObject('NomineeDetails/Gender', [('Nominee_Gender') : Nominee_Gender]))
//		
//		addWait()
//		
//		selectAntDropdown('reln', 'Daughter')
//		
//		WebUI.enhancedClick(findTestObject('OtherDetails/FamilyHistory/Next'))
		
		//WebUI.setText(findTestObject('SmartcardDetails/LAshortName'), 'shortname')
		//
		//selectDropdownValueByLabel('Purpose Of Insurance', 'for Financial Security')
		//
		//selectDropdownValueByLabel('To my(Relationship of beneficiary)', 'Mother' // XPath for the dropdown input (based on label)
		//    )
		//
		//// Click to open the dropdown
		//// XPath for the option to select
		//// Wait for the option to be visible and clickable
		//WebUI.setText(findTestObject('SmartcardDetails/ProvideBeneficiaryName'), 'Testtwo')
		//
		//WebDriver driver = DriverFactory.getWebDriver()
		//
		//// Locate the hidden <input type="file"> element
		//WebElement uploadInput1 = driver.findElement(By.xpath('//input[@id=\'upload324\' and @type=\'file\']'))
		//
		//// Use sendKeys() to upload file directly
		////uploadInput1.sendKeys('C:\\Users\\Neeraja.Nalla\\Downloads\\Media.jpg')
		//uploadInput1.sendKeys('D:\\DummyDocument.png')
		//
		//WebUI.delay(2)
		//
		//WebUI.enhancedClick(findTestObject('SmartcardDetails/Next'))
//		WebUI.verifyElementPresent(findTestObject('Declaration/Photo'), 20)
//		
//		WebUI.delay(1)
		
		if (Life_Type == 'Own Life') {
			
			dropdowns.selectAntDropdown('Salut', 'Mrs.')
			
			WebUI.setText(findTestObject('NomineeDetails/Firstname'), Nominee_Name)
			
			WebUI.setText(findTestObject('NomineeDetails/DOB'), Nominee_DOB)
			
			WebUI.waitForPageLoad(10)
			
			WebUI.enhancedClick(findTestObject('NomineeDetails/Gender', [('Nominee_Gender') : Nominee_Gender]))
			
			addWait()
			
			dropdowns.selectAntDropdown('reln', 'Daughter')
			
			WebUI.enhancedClick(findTestObject('OtherDetails/FamilyHistory/Next'))
			
			WebUI.verifyElementPresent(findTestObject('Declaration/Photo'), 20)
			
			WebUI.delay(1)
		
			//WebDriver driver = DriverFactory.getWebDriver()       //driver is already deifned at source by page code.
			// Locate the hidden <input type="file"> element
			WebElement uploadInput2 = driver.findElement(By.xpath('//div[contains(@class,\'upload-photo\')]//input[@type=\'file\']'))
			
			// Use sendKeys() to upload file directly
			//uploadInput2.sendKeys('C:\\Users\\Neeraja.Nalla\\Downloads\\Media.jpg')
			uploadInput2.sendKeys('D:\\DummyDocument.png')
			
			WebUI.delay(1)
			
			dropdowns.selectAntDropdown('LAVerify', 'Signature of the LA')
			
			//WebDriver driver = DriverFactory.getWebDriver()
			// Locate the hidden <input type="file"> element
			WebElement uploadInput3 = driver.findElement(By.xpath('//span[text()=\'Signature of the LA\']/ancestor::div[contains(@class,\'ant-upload-select\')]//input[@type=\'file\']'))
			
			// Use sendKeys() to upload file directly
			uploadInput3.sendKeys('D:\\DummyDocument.png')
			
			WebUI.delay(1)
			
			dropdowns.selectAntDropdown('AgentVerify', 'Signature of the Agent')
			
			//WebDriver driver = DriverFactory.getWebDriver()
			// Locate the hidden <input type="file"> element
			WebElement uploadInput = driver.findElement(By.xpath('//span[text()=\'Signature of the Agent\']/ancestor::div[contains(@class,\'ant-upload-select\')]//input[@type=\'file\']'))
			
			// Use sendKeys() to upload file directly
			uploadInput.sendKeys('D:\\DummyDocument.png')
			
			WebUI.delay(1)
			
		} else {
			
			WebUI.waitForElementPresent(findTestObject('Declaration/Photo'), GlobalVariable.pageTimeout)
			
			WebUI.delay(1)
			
			WebElement uploadLAPhoto = driver.findElement(By.xpath('(//div[contains(@class,\'upload-photo\')]//input[@type=\'file\'])[1]'))
			
			// Use sendKeys() to upload file directly
			uploadLAPhoto.sendKeys('D:\\DummyDocument.png')
			
			WebUI.delay(1)
			
			WebElement uploadPropPhoto = driver.findElement(By.xpath('(//div[contains(@class,\'upload-photo\')]//input[@type=\'file\'])[2]'))
			
			// Use sendKeys() to upload file directly
			uploadPropPhoto.sendKeys('D:\\DummyDocument.png')
			
			WebUI.delay(1)
			
			dropdowns.selectAntDropdown('ProposerVerify', 'Signature of the Proposer')
			
			WebElement uploadPropSignature = driver.findElement(By.xpath('//span[text()=\'Signature of the Proposer\']/ancestor::div[contains(@class,\'ant-upload-select\')]//input[@type=\'file\']'))
			
			// Use sendKeys() to upload file directly
			uploadPropSignature.sendKeys('D:\\DummyDocument.png')
			
			WebUI.delay(1)
			
			dropdowns.selectAntDropdown('LAVerify', 'Signature of the LA')
			
			//WebDriver driver = DriverFactory.getWebDriver()
			// Locate the hidden <input type="file"> element
			WebElement uploadLASignature = driver.findElement(By.xpath('//span[text()=\'Signature of the LA\']/ancestor::div[contains(@class,\'ant-upload-select\')]//input[@type=\'file\']'))
			
			// Use sendKeys() to upload file directly
			uploadLASignature.sendKeys('D:\\DummyDocument.png')
			
			WebUI.delay(1)
			
			dropdowns.selectAntDropdown('AgentVerify', 'Signature of the Agent')
			
			//WebDriver driver = DriverFactory.getWebDriver()
			// Locate the hidden <input type="file"> element
			WebElement uploadAgentSignature = driver.findElement(By.xpath('//span[text()=\'Signature of the Agent\']/ancestor::div[contains(@class,\'ant-upload-select\')]//input[@type=\'file\']'))
			
			// Use sendKeys() to upload file directly
			uploadAgentSignature.sendKeys('D:\\DummyDocument.png')
			
			WebUI.delay(1)
			
		}
		
		WebUI.enhancedClick(findTestObject('Declaration/Consent1'))
		
		WebUI.enhancedClick(findTestObject('Declaration/Consent2'))
		
		WebUI.enhancedClick(findTestObject('Declaration/Next'))
		
		WebUI.enhancedClick(findTestObject('Declaration/Agree'))
		
		WebUI.waitForPageLoad(10)
		
		if (Life_Type == 'Other Life') {
			
			dropdowns.selectAntDropdown('PropoIDProf', 'Ration card')
			
//			WebUI.setText(findTestObject('DocumentsUpload/ProposerPAN'), Prop_PAN)
			
			WebElement uploadPropIDProof = driver.findElement(By.xpath('//span[text()=\'Proposer ID Proof Upload\']/ancestor::div[contains(@class,\'ant-upload-select\')]//input[@type=\'file\']'))
			
			uploadPropIDProof.sendKeys('D:\\DummyDocument.png')
			
			WebUI.delay(1)
			
			dropdowns.selectAntDropdown('PropoAddProf', 'Bank Passbook copy')
			
//			WebUI.setText(findTestObject('DocumentsUpload/PropAadhaar'), 'VI1234567')
			
			WebElement uploadPropAddressProof = driver.findElement(By.xpath('//span[text()=\'Proposer Address Proof Upload\']/ancestor::div[contains(@class,\'ant-upload-select\')]//input[@type=\'file\']'))
			
			uploadPropAddressProof.sendKeys('D:\\DummyDocument.png')
			
			WebUI.delay(1)
			
		}
		
		WebUI.setText(findTestObject('DocumentsUpload/PANNUM'), PAN)
		
		WebElement uploadAgeProof = driver.findElement(By.xpath('//span[text()=\'LA Age Proof Upload\']/ancestor::div[contains(@class,\'ant-upload-select\')]//input[@type=\'file\']'))
		
		uploadAgeProof.sendKeys('D:\\DummyDocument.png')
		
		WebUI.delay(1)
		
		dropdowns.selectAntDropdown('IDPRDoc', 'Pan Card')
		
		WebUI.setText(findTestObject('DocumentsUpload/IDProofID'), PAN)
		
		WebElement uploadIDProof = driver.findElement(By.xpath('//span[text()=\'LA ID Proof Upload\']/ancestor::div[contains(@class,\'ant-upload-select\')]//input[@type=\'file\']'))
		
		uploadIDProof.sendKeys('D:\\DummyDocument.png')
		
		WebUI.delay(1)
		
		dropdowns.selectAntDropdown('AddPRDoc', 'AADHAR CARD')
		
		WebUI.setText(findTestObject('DocumentsUpload/AddressProofID'), Aadhar)
		
		WebElement uploadAddressProof = driver.findElement(By.xpath('//span[text()=\'LA Address Proof Upload\']/ancestor::div[contains(@class,\'ant-upload-select\')]//input[@type=\'file\']'))
		
		uploadAddressProof.sendKeys('D:\\DummyDocument.png')
		
		WebUI.delay(1)
		
		WebElement uploadDeclaration = driver.findElement(By.xpath('//span[text()=\'Declaration Photo Copy\']/ancestor::div[contains(@class,\'ant-upload-select\')]//input[@type=\'file\']'))
		
		uploadDeclaration.sendKeys('D:\\DummyDocument.png')
		
		WebUI.delay(1)
		
		dropdowns.selectAntDropdown('IncoPRDoc', 'Form 16')
		
		WebElement uploadIncomeProof = driver.findElement(By.xpath('//span[text()=\'LA Income Proof Upload\']/ancestor::div[contains(@class,\'ant-upload-select\')]//input[@type=\'file\']'))
		
		uploadIncomeProof.sendKeys('D:\\DummyDocument.png')
		
		WebUI.delay(1)
		
		WebUI.enhancedClick(findTestObject('DocumentsUpload/Next'))
		
		WebUI.waitForPageLoad(30)
		
		dropdowns.selectAntDropdown('NachRegdr', 'Manual NACH')
		
		WebUI.delay(1)
		
		WebUI.enhancedClick(findTestObject('NACHRegistration/AddNACH'))
		
		WebUI.waitForPageLoad(20)
		
		WebUI.setText(findTestObject('NACHRegistration/AccHolderName'), First_Name)
		
		WebUI.setText(findTestObject('NACHRegistration/AccNumber'), Account_No)
		
		WebUI.setText(findTestObject('NACHRegistration/ReAccNumber'), Account_No)
		
		WebUI.setText(findTestObject('NACHRegistration/IFSC'), IFSC)
		
		WebUI.enhancedClick(findTestObject('NACHRegistration/IFSCSearch'))
		
		addWait()
		
		dropdowns.selectAntDropdown('AccountTypeMN', 'Saving')
		
		dropdowns.selectAntDropdown('PrefDebitDateMN', '2')
		
		WebUI.enhancedClick(findTestObject('NACHRegistration/SameBankBtn'))
		
		WebUI.enhancedClick(findTestObject('NACHRegistration/Save'))
		
		addWait()
		
		WebUI.enhancedClick(findTestObject('NACHRegistration/OkBtn'))
		
		addWait()
		
		WebElement uploadFront = driver.findElement(By.xpath('//span[text()=\'Take a Picture (Front Side)\']/ancestor::div[contains(@class,\'ant-upload-select\')]//input[@type=\'file\']'))
		
		uploadFront.sendKeys('D:\\DummyDocument.png')
		
		WebUI.delay(1)
		
		WebElement uploadBack = driver.findElement(By.xpath('//span[text()=\'Take a Picture (Back Side)\']/ancestor::div[contains(@class,\'ant-upload-select\')]//input[@type=\'file\']'))
		
		uploadBack.sendKeys('D:\\DummyDocument.png')
		
		WebUI.delay(1)
		
		WebElement uploadBankProof = driver.findElement(By.xpath('//span[text()=\'Copy of Bank Proof\']/ancestor::div[contains(@class,\'ant-upload-select\')]//input[@type=\'file\']'))
		
		uploadBankProof.sendKeys('D:\\DummyDocument.png')
		
		WebUI.delay(1)
		
		WebUI.enhancedClick(findTestObject('NACHRegistration/Next'))
		
		WebUI.delay(1)
		
		WebUI.enhancedClick(findTestObject('NACHRegistration/CheckBox'))
		
		WebUI.enhancedClick(findTestObject('NACHRegistration/Confirm'))
		
		WebUI.waitForPageLoad(20)
		
		WebUI.enhancedClick(findTestObject('Summary/Next'))
		
		WebUI.waitForPageLoad(20)
		
		WebUI.enhancedClick(findTestObject('PaymentDetails/PayOption'))
		
		WebUI.enhancedClick(findTestObject('PaymentDetails/SubmitBTN'))
		
		addWait()
		
		WebUI.enhancedClick(findTestObject('PaymentDetails/PaymentType'))
		
		addWait()
		
		WebUI.enhancedClick(findTestObject('PaymentDetails/InstrumentType'))
		
		WebUI.setText(findTestObject('PaymentDetails/ChequeNo'), Cheque_No)
		
		dropdowns.selectDropdownValueByLabel('Bank Name', 'A B E Co operative Bank')
		
		WebUI.setText(findTestObject('PaymentDetails/BankBranch'), Bank_Branch)
		
		dropdowns.selectDropdownValueByLabel('Deposit Bank', 'HDFC CMS')
		
		WebUI.enhancedClick(findTestObject('PaymentDetails/PayBTN'))
		
		addWait()
		
		Boolean error = WebUI.verifyTextPresent('credit limit', false, FailureHandling.OPTIONAL)
		
		if(error) {
			
			WebUI.takeScreenshot(GlobalVariable.screenshotDir + '/4_PaymentFail.jpg')
			
			WebUI.enhancedClick(findTestObject('CaptureProposal/BackBtn'))
			
			addWait()
			
			GlobalVariable.Actual = "Payment Failed. Credit Limit Issue"
			
			getApplicationNumber()
			
		} else {
		
			WebUI.enhancedClick(findTestObject('PaymentDetails/SubmitBTN'))
			
			addWait()
			
			getProposal()
			
			WebUI.takeScreenshot(GlobalVariable.screenshotDir + '/4_ProposalNo.jpg')
			
//			WebUI.enhancedClick(findTestObject('PaymentDetails/DoneBTN'))
			
			GlobalVariable.Actual = "Proposal Created Successfully"
			
//			writeReportRow(TC_ID, Username, Plan_Name, TestCase_Desc, TestCase_Type, Expected_Result)
			
			WriteExcel.writeReportRow(TC_ID, Username, Plan_Name, TestCase_Desc, TestCase_Type, Expected_Result)
			
			WebUI.closeBrowser()
			
		}
		
	} catch (Exception e) {
		
		WebUI.takeScreenshot(GlobalVariable.screenshotDir + '/Validation_Error.jpg')
		
		GlobalVariable.Status = "FAIL"
		
		KeywordUtil.markFailed("Step failed: " + e.message)
		
//		writeReportRow(TC_ID, Username, Plan_Name, TestCase_Desc, TestCase_Type, Expected_Result)
		
		WriteExcel.writeReportRow(TC_ID, Username, Plan_Name, TestCase_Desc, TestCase_Type, Expected_Result)
		
	} finally {
		
		WebUI.closeBrowser()
		
	}

}	


static def addWait() {
    WebDriver driver = DriverFactory.getWebDriver()

    while (driver.findElements(By.xpath('//span[contains(@class,\'ant-spin-dot-spin\')]')).size() > 0) {
        WebUI.delay(1)
    }
    
    WebUI.delay(0.5)
}

def getProposal() {
    String text = WebUI.getText(findTestObject('ProposalNumber/ProposalNo'))

    GlobalVariable.ProposalNo = text.substring(18)

    println('Proposal Number: ' + GlobalVariable.ProposalNo)

    return GlobalVariable.ProposalNo
	
}

def getApplicationNumber() {
	
	WebUI.enhancedClick(findTestObject('CaptureProposal/BackBtn'))
	
	addWait()
	
	String text = WebUI.getText(findTestObject('CaptureProposal/ApplicationNo'))
	
	GlobalVariable.ProposalNo = text.substring(16)
	
	println('Application Number: ' + GlobalVariable.ProposalNo)
	
	KeywordUtil.markFailed("Step failed: " + e.message)
	
//	writeReportRow(TC_ID, Username, Plan_Name, TestCase_Desc, TestCase_Type, Expected_Result)
	
	WriteExcel.writeReportRow(TC_ID, Username, Plan_Name, TestCase_Desc, TestCase_Type, Expected_Result)
	
}

def screenshotDir() {
    GlobalVariable.date = new Date().format('dd-MM-yyyy')
	GlobalVariable.time = new Date().format('HH:mm:ss')

    GlobalVariable.screenshotDir = "D:/Katalon/SLIC_Web/SLIC_Web/Screenshot/$GlobalVariable.date/$Plan_Name/$TC_ID"

    new File(GlobalVariable.screenshotDir).mkdirs()

//    return GlobalVariable.screenshotDir
}