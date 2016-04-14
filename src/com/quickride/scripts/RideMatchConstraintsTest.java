
/***********************************************************************
* @author 			:		Raghukiran MR
* @description		: 		Test scripts of Ride Matching Constraints
* @module			:		Ride Matching Constraints
* @testmethod		:	   	testSameGender()
* @testmethod		:		testSameCompany()
*/
package com.quickride.scripts;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.quickride.PO.FeedbackPO;
import com.quickride.PO.MyProfilePO;
import com.quickride.PO.NewUserRegPO;
import com.quickride.PO.QRProfilePO;
import com.quickride.PO.RidesPO;
import com.quickride.baselib.GenericLib;
import com.quickride.baselib.QRBaseLib;

public class RideMatchConstraintsTest extends QRBaseLib{
	public String sData[]=null;
	public String sTestCaseID=null;
	public Logger qrLog = Logger.getLogger(this.getClass());
	NewUserRegPO newUserRegPo;
	QRProfilePO qrprofile;
	MyProfilePO myProfilePo;
	FeedbackPO feedbackPo;
	RidesPO ridesPo;
	
	/*@Test id:RideMatch_01
	 * @description:Check Ride match filtering the options based on Gender constraint set
	 * @author:Raghukiran MR
	 * 
	 */
	@Test(priority=1,enabled=true)
	public void testSameGender(){ 
	sTestCaseID="RideMatch_01";
	newUserRegPo = new NewUserRegPO(driver);
	qrprofile=new QRProfilePO(driver);
	myProfilePo =new MyProfilePO(driver);
	feedbackPo=new FeedbackPO(driver);
	ridesPo = new RidesPO(driver);
	sData= GenericLib.toReadExcelData(sTestCaseID);
	try{
		//UserA set the profile as sameGender and create the ride
		newUserRegPo.signUPorLogin(sData[1], sData[2],sData[3]);
		myProfilePo.getEleMenuBar().click();
		Assert.assertTrue(myProfilePo.getEleSettingsBtn().isDisplayed(), "Profile page is not displayed");
		qrLog.info("Profile page is displayed");
		myProfilePo.getEleSettingsBtn().click();
		Assert.assertTrue(myProfilePo.getEleSameGender().isDisplayed(), "Tap on SameGender checkbox is not successfull");
		qrLog.info("Tap on SameGender checkbox is successfull");
		myProfilePo.getEleSameGender().click();
		myProfilePo.getEleSaveLnk().click();
		feedbackPo.createRide(sData[4],sData[5], ridesPo);
		qrprofile.logout();
		//UserB set the profile as sameGender
		newUserRegPo.signUPorLogin(sData[6], sData[7],sData[8]);
		myProfilePo.getEleMenuBar().click();
		Assert.assertTrue(myProfilePo.getEleSettingsBtn().isDisplayed(), "Profile page is not displayed");
		qrLog.info("Profile page is displayed");
		myProfilePo.getEleSettingsBtn().click();
		Assert.assertTrue(myProfilePo.getEleSameGender().isDisplayed(), "Tap on SameGender checkbox is  successfully");
		qrLog.info("Tap on SameGender checkbox  is successfull");
		myProfilePo.getEleSameGender().click();
		myProfilePo.getEleSaveLnk().click();
		feedbackPo.getEleSearchBtn().click();
		feedbackPo.getEleEnterAddTxtFld().sendKeys(sData[4]);
		feedbackPo.getEleFirstOption().click();
		feedbackPo.getEleRideNwBtn().click();
		feedbackPo.tohandleblackscreen();
		feedbackPo.getEleEndAddressTxtFld().click();
		feedbackPo.getEleEnterAddTxtFld().sendKeys(sData[5]);
		feedbackPo.getEleFirstOption().click();
		feedbackPo.getEleFindRideTab().click();
		Assert.assertTrue(feedbackPo.getElepassArrowIcn().isDisplayed(), "Ride is not displayed");
		qrLog.info("Ride is displayed");
		feedbackPo.getElepassArrowIcn().click();
		myProfilePo.getEleNoBtn().click();
		qrprofile.logout();
		//gender constraint removed
		newUserRegPo.signupMale(sData[10],sData[11],sData[12]);
		myProfilePo.getEleMenuBar().click();
		Assert.assertTrue(myProfilePo.getEleSettingsBtn().isDisplayed(), "Profile page is not displayed");
		qrLog.info("Profile page is displayed");
		myProfilePo.getEleSettingsBtn().click();
		Assert.assertTrue(myProfilePo.getEleSameGender().isDisplayed(), "Tap on SameGender checkbox is  successfully");
		qrLog.info("Tap on SameGender checkbox  is successfull");
		myProfilePo.getEleSameGender().click();
		myProfilePo.getEleSaveLnk().click();
		feedbackPo.getEleSearchBtn().click();
		feedbackPo.getEleEnterAddTxtFld().sendKeys(sData[4]);
		feedbackPo.getEleFirstOption().click();
		feedbackPo.getEleRideNwBtn().click();
		feedbackPo.tohandleblackscreen();
		feedbackPo.getEleEndAddressTxtFld().click();
		feedbackPo.getEleEnterAddTxtFld().sendKeys(sData[5]);
		feedbackPo.getEleFirstOption().click();
		feedbackPo.getEleFindRideTab().click();
		Assert.assertTrue(feedbackPo.getElepassArrowIcn().isDisplayed(), "Ride is displayed");
		qrLog.info("ride is not displayed");
		feedbackPo.getElepassArrowIcn().click();
		myProfilePo.getEleNoBtn().click();
		////Clean up code
		myProfilePo.getEleMenuBar().click();
		myProfilePo.getEleSettingsBtn().click();
		myProfilePo.getEleSameGender().click();
		myProfilePo.getEleSaveLnk().click();
		qrprofile.logout();
		//Clean Up code
		newUserRegPo.getEleLoginBtn().click();
		newUserRegPo.getEleLoginPhTxtFld().sendKeys(sData[6]);
		newUserRegPo.getEleLoginPwdTxtFld().sendKeys(sData[7]);
		driver.hideKeyboard();
		newUserRegPo.getEleLoginBtn().click();
		myProfilePo.getEleMenuBar().click();
		myProfilePo.getEleSettingsBtn().click();
		myProfilePo.getEleSameGender().click();
		myProfilePo.getEleSaveLnk().click();
		qrprofile.logout();
		//Clean up code
		newUserRegPo.getEleLoginBtn().click();
		newUserRegPo.getEleLoginPhTxtFld().sendKeys(sData[1]);
		newUserRegPo.getEleLoginPwdTxtFld().sendKeys(sData[2]);
		driver.hideKeyboard();
		newUserRegPo.getEleLoginBtn().click();
		feedbackPo.getEleArrowIcn().click();
		myProfilePo.getEleMenuBar().click();
		myProfilePo.getEleSettingsBtn().click();
		myProfilePo.getEleSameGender().click();
		myProfilePo.getEleSaveLnk().click();
		myProfilePo.getEleMenuBar().click();
		qrprofile.getEleMyRidesBtn().click();
		ridesPo.getEleCancelinNotilnk().click();
		ridesPo.getEleCancelBtn().click();
		feedbackPo.getEleYesBtn().click();
		qrprofile.logout();
		}catch(Exception e){
		qrLog.error("Exception in  case testSameGender()");
		e.printStackTrace();
		}

}
	/*@Test id:RideMatch_02
	 * @description:Check Ride match filtering the options based on Gender constraint set
	 * @author:Raghukiran MR
	 * 
	 */
	@Test(priority=2,enabled=true)
	public void testSameCompany(){
		sTestCaseID="RideMatch_02";
		newUserRegPo = new NewUserRegPO(driver);
		qrprofile=new QRProfilePO(driver);
		myProfilePo =new MyProfilePO(driver);
		feedbackPo=new FeedbackPO(driver);
		ridesPo = new RidesPO(driver);
		sData= GenericLib.toReadExcelData(sTestCaseID);
		try{
			//UserA set the profile as sameCompany
			newUserRegPo.signUPorLogin(sData[1], sData[2],sData[3]);
			myProfilePo.getEleMenuBar().click();
			myProfilePo.getEleArrowBtn().click();
			myProfilePo.getEleEditLnk().click();
			myProfilePo.getEleCompanyIcn().click();
			myProfilePo.getEleRemoveCompanyTxt().click();
			myProfilePo.getEleCompanyTxtFld().sendKeys(sData[9]);
			driver.navigate().back();
			myProfilePo.getEleSaveLnk().click();
			try{
				driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);	
				if(feedbackPo.getEleProfileArrowIcn().isDisplayed()){
				feedbackPo.getEleProfileArrowIcn().click();
				myProfilePo.getEleMenuBar().click();
				}
			}catch(Exception e){
				if(myProfilePo.getEleMenuBar().isDisplayed()){
					myProfilePo.getEleMenuBar().click();
				}
			}
			Assert.assertTrue(myProfilePo.getEleSettingsBtn().isDisplayed(), "Profile page is not displayed");
			qrLog.info("Profile page is displayed");
			myProfilePo.getEleSettingsBtn().click();
			Assert.assertTrue(myProfilePo.getEleSameCompany().isDisplayed(), "Tap on SameCompany checkbox  is not successfull");
			qrLog.info("Tap on SameCompany checkbox is successfull.");
			myProfilePo.getEleSameCompany().click();
			myProfilePo.getEleSaveLnk().click();
			feedbackPo.createRide(sData[4],sData[5], ridesPo);
			qrprofile.logout();
			//UserB set the profile as sameCompany
			newUserRegPo.signUPorLogin(sData[6], sData[7],sData[8]);
			myProfilePo.getEleMenuBar().click();
			myProfilePo.getEleArrowBtn().click();
			myProfilePo.getEleEditLnk().click();
			myProfilePo.getEleCompanyIcn().click();
			myProfilePo.getEleRemoveCompanyTxt().click();
			myProfilePo.getEleCompanyTxtFld().sendKeys(sData[9]);
			driver.navigate().back();
			myProfilePo.getEleSaveLnk().click();
			try{
				driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);	
				if(feedbackPo.getEleProfileArrowIcn().isDisplayed()){
				feedbackPo.getEleProfileArrowIcn().click();
					myProfilePo.getEleMenuBar().click();
				}
			}catch(Exception e){
				if(myProfilePo.getEleMenuBar().isDisplayed()){
					myProfilePo.getEleMenuBar().click();
				}
			}
			Assert.assertTrue(myProfilePo.getEleSettingsBtn().isDisplayed(), "Profile page is not displayed");
			qrLog.info("Profile page is displayed");
			myProfilePo.getEleSettingsBtn().click();
			Assert.assertTrue(myProfilePo.getEleSameCompany().isDisplayed(), "Tap on SameCompany checkbox  is not successfull");
			qrLog.info("Tap on SameCompany checkbox is successfull");
			myProfilePo.getEleSameCompany().click();
			myProfilePo.getEleSaveLnk().click();
			feedbackPo.getEleSearchBtn().click();
			feedbackPo.getEleEnterAddTxtFld().sendKeys(sData[4]);
			feedbackPo.getEleFirstOption().click();
			feedbackPo.getEleRideNwBtn().click();
			feedbackPo.tohandleblackscreen();
			feedbackPo.getEleEndAddressTxtFld().click();
			feedbackPo.getEleEnterAddTxtFld().sendKeys(sData[5]);
			feedbackPo.getEleFirstOption().click();
			feedbackPo.getEleFindRideTab().click();
			Assert.assertTrue(feedbackPo.getElepassArrowIcn().isDisplayed(), "Rider name is not displayed");
			qrLog.info("Rider name is displayed");
			feedbackPo.getElepassArrowIcn().click();
			myProfilePo.getEleNoBtn().click();
			myProfilePo.getEleMenuBar().click();
			myProfilePo.getEleArrowBtn().click();
			myProfilePo.getEleEditLnk().click();
			myProfilePo.getEleCompanyIcn().click();
			myProfilePo.getEleRemoveCompanyTxt().click();
			myProfilePo.getEleCompanyTxtFld().sendKeys(sData[10]);
			driver.navigate().back();
			myProfilePo.getEleSaveLnk().click();
			feedbackPo.getEleProfileArrowIcn().click();
			feedbackPo.getEleSearchBtn().click();
			feedbackPo.getEleEnterAddTxtFld().sendKeys(sData[4]);
			feedbackPo.getEleFirstOption().click();
			feedbackPo.getEleRideNwBtn().click();
			feedbackPo.tohandleblackscreen();
			feedbackPo.getEleEndAddressTxtFld().click();
			feedbackPo.getEleEnterAddTxtFld().sendKeys(sData[5]);
			feedbackPo.getEleFirstOption().click();
			Assert.assertTrue(feedbackPo.getEleFindRideTab().isDisplayed(), "Rider name is displayed");
			qrLog.info("Rider name is not displayed");
			////Clean up code
			feedbackPo.getElepassArrowIcn().click();
			myProfilePo.getEleNoBtn().click();
			myProfilePo.getEleMenuBar().click();
			myProfilePo.getEleSettingsBtn().click();
			myProfilePo.getEleSameCompany().click();
			myProfilePo.getEleSaveLnk().click();
			qrprofile.logout();
			//Clean up code
			newUserRegPo.getEleLoginBtn().click();
			newUserRegPo.getEleLoginPhTxtFld().sendKeys(sData[1]);
			newUserRegPo.getEleLoginPwdTxtFld().sendKeys(sData[2]);
			driver.hideKeyboard();
			newUserRegPo.getEleLoginBtn().click();
			feedbackPo.getEleArrowIcn().click();
			myProfilePo.getEleMenuBar().click();
			myProfilePo.getEleSettingsBtn().click();
			myProfilePo.getEleSameCompany().click();
			myProfilePo.getEleSaveLnk().click();
			myProfilePo.getEleMenuBar().click();
			qrprofile.getEleMyRidesBtn().click();
			ridesPo.getEleCancelinNotilnk().click();
			ridesPo.getEleCancelBtn().click();
			feedbackPo.getEleYesBtn().click();
			qrprofile.logout();
			}
		catch(Exception e){
			qrLog.error("Exception in  case testSameCompany()");
			e.printStackTrace();
			}

	}
}
