/***********************************************************************
* @author 			:		LAKSHMI BS
* @description		: 		Test scripts of Social Networks module.
* @module			:		Social Networks
* @testmethod		:		testGuestSignUp()
* @testmethod		:		testFBLogin()
* @testmethod		:		testLoginOthrUser()
* 
*/

package com.quickride.scripts;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.quickride.PO.NewUserRegPO;
import com.quickride.PO.QRProfilePO;

import com.quickride.baselib.GenericLib;
import com.quickride.baselib.QRBaseLib;

public class SocialGuestTest extends QRBaseLib
{
	public String sData[]=null;
	public String sTestCaseID=null;
	NewUserRegPO newUserRegPo;
	QRProfilePO qrProfilePo;
	public Logger qrLog = Logger.getLogger(this.getClass());
	
	/*
	 * @description: Test Guest user able to check Ride Options
	 * @author: LAKSHMI BS
	 */	
	@Test(priority=1,enabled=true)
	public void testGuestSignUp()
	{
		sTestCaseID="SocialGuest_01";
		sData= GenericLib.toReadExcelData(sTestCaseID);
		String sVerificationCode = null;
		newUserRegPo = new NewUserRegPO(driver);
		qrProfilePo = new QRProfilePO(driver);
		try
		{ 
			if(newUserRegPo.getEleGuestLnk().isDisplayed())
			{
				newUserRegPo.getEleGuestLnk().click();
				while(true)
				{	if(newUserRegPo.getEleCurrentLocTxt().isDisplayed())
					{		break;
					}
				}
				Assert.assertTrue(newUserRegPo.getEleCurrentLocTxt().isDisplayed(), "New Ride page is not displayed");
				qrLog.info("New Ride page is displayed");
				qrProfilePo.getEleMenuLst().click();
				qrProfilePo.getEleMyRewardsBtn().click();
				Assert.assertTrue(qrProfilePo.getEleSignUpBtn().isDisplayed(), "Register popup is not displayed");
				qrLog.info("Register popup is displayed");
				qrProfilePo.getEleSignUpBtn().click();
				
				newUserRegPo.getElePhoneNOTxtFld().sendKeys(sData[1]);
				newUserRegPo.getElePwdTxtFld().sendKeys(sData[2]);
				newUserRegPo.getEleNameTxtFld().sendKeys(sData[3]);
				driver.hideKeyboard();
				newUserRegPo.getEleFemaleRdBtn().click();
				newUserRegPo.getEleSignUpRegBtn().click();
				
				try{
					if(newUserRegPo.getElePopupLoginBtn().isDisplayed())
					{
						qrLog.info("User already exists");
						newUserRegPo.getElePopupLoginBtn().click();
						newUserRegPo.getEleLoginPwdTxtFld().sendKeys(sData[2]);
						newUserRegPo.getEleLoginBtn().click();
					}
				}catch(Exception e)
				{
					Assert.assertTrue(newUserRegPo.getEleThankYouTxt().isDisplayed(), "SignUp is incomplete");
					qrLog.info("Sign Up is completed");
					
					Thread.sleep(QRBaseLib.iMediumSleep);
					sVerificationCode=GenericLib.getDBdata(GenericLib.getCongigValue(QRBaseLib.sConfigFile, "VERIFICATION"), "verifycode", "subject", sData[1]);
					System.out.println(sVerificationCode);
					newUserRegPo.getEleActivationTxtFld().sendKeys(sVerificationCode);			
					newUserRegPo.getEleActivateBtn().click();
					Assert.assertTrue(newUserRegPo.getEleAccActivatedTxt().isDisplayed(), "Account activated popup is not displayed");
					qrLog.info("Account Activated popup is successfully displayed");
					newUserRegPo.getEleSkipBtn().click();
				}
				
				
			
				Assert.assertTrue(newUserRegPo.getEleCurrentLocTxt().isDisplayed(), "New Ride page is not displayed");
				qrLog.info("New Ride page is succesfully displayed");
			}
		}catch(Exception e)
		{
			qrLog.info("Exception in testGuestSignUp");
			e.getStackTrace();
		}
	}
	

	/*
	 * @description: User choose to login with facebook and enter accurate credentials. 
	 * @author: LAKSHMI BS
	 * 
	 * 
	 */	
	@Test(priority=2,enabled=true)
	public void testFBLogin()
	{
		sTestCaseID="SocialGuest_02";
		sData= GenericLib.toReadExcelData(sTestCaseID);
		newUserRegPo = new NewUserRegPO(driver);
		qrProfilePo = new QRProfilePO(driver);
		
		try
		{ if(newUserRegPo.getEleFaceBkLnk().isDisplayed())
			{
				newUserRegPo.getEleFaceBkLnk().click();
				Thread.sleep(5000);
				newUserRegPo.getelELoginAnothrAccLnk().click();
				Assert.assertTrue(newUserRegPo.getEleFBLoginBtn().isDisplayed(), "FaceBook page is not displayed");
				qrLog.info("FaceBook page is displayed");
				
				
			
				newUserRegPo.getEleFBuserTxtFld().sendKeys(sData[4]);
				newUserRegPo.getEleFBpwdTxtFld().sendKeys(sData[2]); 
				newUserRegPo.getEleFBLoginBtn().click();
			
				try{
					if(newUserRegPo.getEleFBIncorrectEmailTxt().isDisplayed())
					{
						qrLog.info("email is not registered");
						newUserRegPo.getEleOKBtn().click();
						newUserRegPo.getEleFBSignUpLnk().click();
						newUserRegPo.getEleFBNextBtn().click();
						newUserRegPo.getEleFBMobileNumberTxtFld().sendKeys(sData[1]);
						newUserRegPo.getEleFBNextBtn().click();
						
						newUserRegPo.getEleFbFirstNameTxtFld().sendKeys("Tom");
						newUserRegPo.getEleFbLastNameTxtFld().sendKeys("Quick");
						newUserRegPo.getEleFBNextBtn().click();
						newUserRegPo.getEleFBNextBtn().click();
						newUserRegPo.getEleFBFemaleRdBtn().click();
						newUserRegPo.getEleFBNextBtn().click();
						newUserRegPo.getEleFBSignUppwdTxtFld().click();
						newUserRegPo.getEleFBSkipLnk().click();
						newUserRegPo.getEleFBGetStartedBtn().click();
						newUserRegPo.getEleFBDoneLnk().click();
						driver.navigate().back();
						
						
					}
					
				}
				catch(Exception e)
				{
					
				}

				Assert.assertTrue(newUserRegPo.getEleCurrentLocTxt().isDisplayed(), "New Ride page is not displayed");
				qrLog.info("New Ride page is succesfully displayed");
			}
		}catch(Exception e)
		{
			qrLog.info("Exception in testFBLogin");
			e.getStackTrace();
		}
		
		
	}

	
	/*
	 * @description: Test User login through different device, when he loose/misplace/change his mobile
	 * @author: LAKSHMI BS
	 * 
	 */	
	@Test(priority=3,enabled=true)
	public void testLoginOthrUser()
	{
		sTestCaseID="SocialGuest_04";
		sData= GenericLib.toReadExcelData(sTestCaseID);
		newUserRegPo = new NewUserRegPO(driver);
		String sVerificationCode=null;
		try
		{
			newUserRegPo.signUPorLogin(sData[1],sData[2],sData[3]);
			qrLog.info(" Test User login through different device");
		} catch (Exception e) {
			qrLog.error("Exception in  case testLoginOthrUser");
			e.printStackTrace();
			Assert.fail();
		}
		
	}
	



}
