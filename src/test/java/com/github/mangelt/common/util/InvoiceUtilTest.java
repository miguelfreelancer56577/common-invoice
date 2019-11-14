package com.github.mangelt.common.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest()
@ContextConfiguration(classes = InvoiceUtil.class)
public class InvoiceUtilTest {

	@Autowired
	InvoiceUtil invoiceUtil;
	
	@Test(expected = NullPointerException.class)
	public void nullValues() {
		invoiceUtil.decompress(null, null);
	}
	
	@Test
	public void nonZipFileDir() {
		
	}
	
	@Test
	public void nonZipFile() {
		
	}
	
	@Test
	public void zipFileWithoutXmls() {
		
	}
	
	@Test
	public void zipFileWithDirs() {
		
	}
	
	@Test
	public void emptyZipFile() {
		
	}
	
	@Test
	public void rightZipFile() {
		
	}
	
	@Test
	public void rightZipFileWithCallback() {
		
	}
	
}
