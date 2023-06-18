package com.nyayas.common.util;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class PDFToTextTest {

	private static final String FILE_PATH = "d:/ChhotelalGuptaResume.pdf";

	@Test
	void testPdfToText() throws Exception {
		String output = PDFToText.pdfToText(FILE_PATH);
		assertNotNull(output);
		assertTrue(output.length() > 0);
	}

	@Test
	void testPdfToHtml() throws Exception {
		String output = PDFToText.pdfToHtml(FILE_PATH);
		assertNotNull(output);
		assertTrue(output.length() > 0);
	}
}
