package com.github.mangelt.common.util.core.impl;

import java.util.List;

import com.github.mangelt.common.util.core.InvoiceProcesorI;
import com.github.mangelt.data.entity.Comprobante;
import com.github.mangelt.data.repository.InvoiceRepositoryI;

public class CommonInvoiceProcesor implements InvoiceProcesorI<Comprobante> {

	private InvoiceRepositoryI repository;
	
	@Override
	public void process(List<Comprobante> invoices) {
		invoices.forEach(repository::updateOrInsert);
	}


}
