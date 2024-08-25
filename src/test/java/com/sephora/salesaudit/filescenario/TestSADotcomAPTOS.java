package com.sephora.salesaudit.filescenario;

import java.nio.file.NoSuchFileException;

import org.testng.annotations.Test;

import com.sephora.salesaudit.annotation.TestRailID;
import com.sephora.salesaudit.file.Sampletest;

public class TestSADotcomAPTOS extends Sampletest {
	
	
   @Test(groups={"SanityTest"}, enabled = true, description = "Verify file is created at ESB in APTOS for Sale BoarderFree Invoice XML and logs getting update", priority = 0)
	@TestRailID(id = {"C10743927", "C10743924", "C12044046", "C13400681", "C13400688"})
	public void verifyDCAPTOSSaleBoarderFreeFlow() throws Exception
	{
		printLog("<-----------Start of Test Case : ----------->");
		
		s_Assert.assertAll();
	}
	
	
	
   
}