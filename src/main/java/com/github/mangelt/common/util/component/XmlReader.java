package com.github.mangelt.common.util.component;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.springframework.stereotype.Component;

import com.github.mangelt.common.util.exception.XmlReaderException;
import com.github.mangelt.data.entity.Comprobante;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class XmlReader {
	

	public Comprobante createComprobante(File invoiceFile) throws XmlReaderException {
		
		log.info("XmlReader; File name \n {}", invoiceFile.getName());
		
		Comprobante comprobante = null;
		
		try {
			JAXBContext jc = JAXBContext.newInstance( "com.github.mangelt.data.entity" );
			Unmarshaller u = jc.createUnmarshaller();		
			comprobante = (Comprobante)u.unmarshal(invoiceFile);
			return comprobante;
		} catch (Exception e) {
			log.error("XmlReader; Unmarshall Error \n {}", e.getMessage());
			throw new XmlReaderException(e);
		}
	}
	
}
