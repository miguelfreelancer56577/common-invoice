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

// TODO: Auto-generated Javadoc
/**
 * The Class InvoiceUtilTest.
 */
@RunWith(SpringRunner.class)
@SpringBootTest()
@ContextConfiguration(classes = InvoiceUtil.class)

/** The Constant log. */
@Slf4j
public class InvoiceUtilTest {

	/** The invoice util. */
	@Autowired
	InvoiceUtil invoiceUtil;
	
	/** The dir file. */
	@Value("classpath:nonZipFile.txt")
	String dirFile;
	
	/** The dir file zip without. */
	@Value("classpath:nonZipFile.txt.zip")
	String dirFileZipWithout;
	
	/** The Zip file without. */
	@Value("classpath:nonZipFile.zip")
	String ZipFileWithout;
	
	/** The right zip F ile. */
	@Value("classpath:rightInvoices.zip")
	String rightZipFIle;
	
	/** The resource loader. */
	@Autowired
	ResourceLoader resourceLoader;
	
	/**
	 * Null values.
	 */
	@Test(expected = NullPointerException.class)
	public void nullValues() {
		invoiceUtil.decompress(null);
	}
	
	/**
	 * Non zip file.
	 */
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
	
	/**
	 * Zip file without xmls.
	 */
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
	
	/**
	 * Zip file with out XM ls.
	 */
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
	
	/**
	 * Right zip file.
	 */
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
	
	/**
	 * Test cases for files transformation from byte array to file object and vice versa. 
	 * 
	 * fromByteArrayToFile:
	 * 
	 * Test if the files is converted correctly from byte array to file.
	 * 
	 * 
	 * 
	 */
	@Test
	public void fromByteArrayToFile(){
		boolean assertion = false;
		try {
			File file = resourceLoader.getResource(rightZipFIle).getFile();
			Optional<byte[]> fileToByteArray = invoiceUtil.fileToByteArray(file);
			if(fileToByteArray.isPresent()) {
//				convert back to File
				Optional<File> byteArrayToFile = invoiceUtil.byteArrayToFile(fileToByteArray.get());
				if(byteArrayToFile.isPresent())
						assertion = true;
			}
		} catch (IOException e) {
			log.error("There was an erro: {}", e.getMessage());
		}
		assertTrue(assertion);
	}
	
	
	@Test
	public void fromFileToByteArray(){
		boolean assertion = false;
		try {
			File file = resourceLoader.getResource(rightZipFIle).getFile();
			Optional<byte[]> fileToByteArray = invoiceUtil.fileToByteArray(file);
			if(fileToByteArray.isPresent()) 
				assertion = true;
		} catch (IOException e) {
			log.error("There was an erro: {}", e.getMessage());
		}
		assertTrue(assertion);
	}
	
}
