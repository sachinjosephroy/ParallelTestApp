package com.paralleltestapp.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.paralleltestapp.qa.base.Testbase;

public class ParallelTest extends Testbase {
	
	@Test
	public void testTitle() {
		String actual = login.getTitle();
		String expected = "#1 Free CRM software in the cloud for sales and service.";
		Assert.assertEquals(actual, expected);
	}

}
