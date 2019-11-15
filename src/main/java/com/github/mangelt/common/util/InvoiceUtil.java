package com.github.mangelt.common.util;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.zip.ZipFile;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class InvoiceUtil {

	public List<File> decompress(@NonNull File compressedFile) {
		try (ZipFile zipFile = new ZipFile(compressedFile)) {

			return zipFile.stream()
					.filter(zipEntry->{
						String fileExtension = FilenameUtils.getExtension(zipEntry.getName());
						return fileExtension.equalsIgnoreCase("xml");
					})
					.flatMap(zipEntry->{
						try {
							File file = this.createTmpFile(zipEntry.getName());
							FileUtils.copyInputStreamToFile(zipFile.getInputStream(zipEntry), file);
							return Stream.of(file);
						} catch (Exception e) {
							log.error("Something strange happened to get the File: {}" , zipEntry.getName());
							log.error("ERROR CLASS: {} ERROR CAUSE: {}" , e.getClass(), e.getCause());
							return Stream.empty();
						}
					})
					.collect(Collectors.toList());

		} catch (IOException e) {
			log.error("There was an error to read the file: {}" , compressedFile.getName());
		}
		return Collections.emptyList();
	}
	
	public File createTmpFile(String fileName) throws IOException {
		String baseName = FilenameUtils.getBaseName(fileName);
		String fileExtension = "."+FilenameUtils.getExtension(fileName);
		return File.createTempFile(baseName, fileExtension);
	}
	
}
