package com.github.mangelt.common.util;

import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.github.mangelt.common.util.component.XmlReader;
import com.github.mangelt.common.util.exception.XmlReaderException;
import com.github.mangelt.data.entity.Comprobante;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = XmlReader.class)
@Slf4j
public class XmlReaderTest {

	/** The dir file. */
	@Value("classpath:invoice1.xml")
	String xmlFile;
	
	/** The resource loader. */
	@Autowired
	ResourceLoader resourceLoader;
	
	@Autowired
	XmlReader xmlReader;
	
	@Test
	public void rigthMarshall(){
		Comprobante createComprobante = null;
		try {
			File file = resourceLoader.getResource(xmlFile).getFile();
			createComprobante = xmlReader.createComprobante(file);
		} catch (IOException | XmlReaderException e) {
			e.printStackTrace();
		}
		assertNotNull(createComprobante);
	}
	
}
