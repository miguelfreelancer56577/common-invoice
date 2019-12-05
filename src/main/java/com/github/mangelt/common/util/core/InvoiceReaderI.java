package com.github.mangelt.common.util.core;

import java.io.File;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

public interface InvoiceReaderI<T> {
	List<T> read(List<File> invoices, BiConsumer<Exception, File> callbackError);
}
