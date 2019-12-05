package com.github.mangelt.common.util.core.impl;

import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.mangelt.common.util.component.XmlReader;
import com.github.mangelt.common.util.core.InvoiceReaderI;
import com.github.mangelt.common.util.exception.XmlReaderException;
import com.github.mangelt.data.entity.Comprobante;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CommonInvoiceReader implements InvoiceReaderI<Comprobante>{
	
	@Autowired
	XmlReader xmlReader;
	
	@Override
	public List<Comprobante> read(@NonNull List<File> invoices, BiConsumer<Exception, File> callbackError) {
		Optional<BiConsumer<Exception, File>> callback = Optional.ofNullable(callbackError);
		return invoices.stream().flatMap(xmlFile->{
			try {
				
				Comprobante comprobante = xmlReader.createComprobante(xmlFile);
				comprobante.setUUID(comprobante.getComplemento().getTimbreFiscalDigital().getUuid());
				return Stream.of(comprobante);
			} catch (XmlReaderException e) {
				if(callback.isPresent())
					callback.get().accept(e, xmlFile);
				log.error("CommonInvoiceReader - There was an error to read the content of the file: " + xmlFile.getName());
				log.error("CommonInvoiceReader - ERROR CLASS: {} \n ERROR CAUSE: {}", e.getClass() , e.getCause() );
				
			}
			return Stream.empty();
		}).collect(Collectors.toList());
	}
	
}
