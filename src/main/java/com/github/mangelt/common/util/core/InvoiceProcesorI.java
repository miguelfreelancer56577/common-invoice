package com.github.mangelt.common.util.core;

import java.util.List;

public interface InvoiceProcesorI<T> {
	void process(List<T> invoices);
}
