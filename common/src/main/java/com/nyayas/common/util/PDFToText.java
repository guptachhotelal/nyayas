package com.nyayas.common.util;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.tools.PDFText2HTML;

public class PDFToText {

	public static final int TO_TEXT = 1;
	public static final int TO_HTML = 2;

	public static final String pdfToText(String filePath) throws IOException {
		return pdfTo(TO_TEXT, new File(filePath));
	}

	public static final String pdfToHtml(String filePath) throws IOException {
		return pdfTo(TO_HTML, new File(filePath));
	}

	private static String pdfTo(int to, File file) throws IOException {
		PDDocument document = PDDocument.load(file);
		switch (to) {
		case TO_TEXT:
			return new PDFTextStripper().getText(document);
		case TO_HTML:
			return new PDFText2HTML().getText(document);
		default:
			return new PDFTextStripper().getText(document);
		}
	}
}
