package com.github.mangelt.common.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import org.assertj.core.util.Arrays;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest()
@ContextConfiguration(classes = InvoiceUtil.class)
@Slf4j
public class InvoiceUtilTest {

	@Autowired
	InvoiceUtil invoiceUtil;
	
	@Value("classpath:nonZipFile.txt")
	String dirFile;
	
	@Value("classpath:nonZipFile.txt.zip")
	String dirFileZipWithout;
	
	@Value("classpath:nonZipFile.zip")
	String ZipFileWithout;
	
	@Value("classpath:rightInvoices.zip")
	String rightZipFIle;
	
	@Autowired
	ResourceLoader resourceLoader;
	
	@Test(expected = NullPointerException.class)
	public void nullValues() {
		invoiceUtil.decompress(null);
	}
	
	@Test
	public void nonZipFile() {
		try {
			File file = resourceLoader.getResource(dirFile).getFile();
			assertTrue("This files doesn't exit", file.exists());
			List<File> decompress = invoiceUtil.decompress(file);
			assertTrue(Arrays.isNullOrEmpty(decompress.toArray()));
		} catch (IOException e) {
			log.error("There was an error to get the the file from the resource dir: {}", e.getMessage());
			assertTrue(false);
		}
	}
	
	@Test
	public void zipFileWithoutXmls() {
		try {
			File file = resourceLoader.getResource(dirFileZipWithout).getFile();
			assertTrue("This files doesn't exit", file.exists());
			List<File> decompress = invoiceUtil.decompress(file);
			assertTrue(Arrays.isNullOrEmpty(decompress.toArray()));
		} catch (IOException e) {
			log.error("There was an error to get the the file from the resource dir: {}", e.getMessage());
			assertTrue(false);
		}
	}
	
	@Test
	public void ZipFileWithOutXMLs() {
		try {
			File file = resourceLoader.getResource(ZipFileWithout).getFile();
			assertTrue("This files doesn't exit", file.exists());
			List<File> decompress = invoiceUtil.decompress(file);
			assertTrue(Arrays.isNullOrEmpty(decompress.toArray()));
		} catch (IOException e) {
			log.error("There was an error to get the the file from the resource dir: {}", e.getMessage());
			assertTrue(false);
		}
	}
	
	@Test
	public void rightZipFile() {
		try {
			File file = resourceLoader.getResource(rightZipFIle).getFile();
			assertTrue("This files doesn't exit", file.exists());
			List<File> decompress = invoiceUtil.decompress(file);
			assertFalse(Arrays.isNullOrEmpty(decompress.toArray()));
		} catch (IOException e) {
			log.error("There was an error to get the the file from the resource dir: {}", e.getMessage());
			assertTrue(false);
		}
	}
	
	@Test
	public void rightZipFileWithCallback() {
		List<String> listOfListWithError = new ArrayList();  
		Optional<Consumer<String>> callbackError = Optional.of((fileName->{
			listOfListWithError.add(fileName);
			throw new NullPointerException("demo");
		}));
		
		try {
			File file = resourceLoader.getResource(rightZipFIle).getFile();
			assertTrue("This files doesn't exit", file.exists());
			List<File> decompress = invoiceUtil.decompress(file);
			assertFalse(Arrays.isNullOrEmpty(listOfListWithError.toArray()));
		} catch (IOException e) {
			log.error("There was an error to get the the file from the resource dir: {}", e.getMessage());
			assertTrue(false);
		}
	}
	
}
