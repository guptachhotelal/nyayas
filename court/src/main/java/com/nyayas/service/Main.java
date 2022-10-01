package com.nyayas.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import java.util.zip.GZIPInputStream;

public class Main {

	public static void main(String[] args) throws IOException {
		URL url = new URL("https://hcservices.ecourts.gov.in/ecourtindiaHC/cases/qs_civil_advocate_qry.php");
		HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
		httpConn.setRequestMethod("POST");

		httpConn.setRequestProperty("User-Agent",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:103.0) Gecko/20100101 Firefox/103.0");
		httpConn.setRequestProperty("Accept", "*/*");
		httpConn.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
		httpConn.setRequestProperty("Accept-Encoding", "gzip, deflate, br");
		httpConn.setRequestProperty("Referer", "https://hcservices.ecourts.gov.in/");
		httpConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		httpConn.setRequestProperty("Origin", "https://hcservices.ecourts.gov.in");
		httpConn.setRequestProperty("DNT", "1");
		httpConn.setRequestProperty("Connection", "keep-alive");
		httpConn.setRequestProperty("Cookie", "PHPSESSID=tp3sv5v92squii5ui4qs9es11b");
		httpConn.setRequestProperty("Sec-Fetch-Dest", "empty");
		httpConn.setRequestProperty("Sec-Fetch-Mode", "cors");
		httpConn.setRequestProperty("Sec-Fetch-Site", "same-origin");

		httpConn.setDoOutput(true);
		OutputStreamWriter writer = new OutputStreamWriter(httpConn.getOutputStream());
		writer.write(
				"__csrf_magic=sid:b812ee59b9210640b0a02b292f597bbe0995de3e,1659195874&action_code=showRecords&state_code=1&dist_code=1&court_code=1&advocate_name=gaurav sharma&search_type=1&f=Pending&captcha=vdvn5");
		writer.flush();
		writer.close();
		httpConn.getOutputStream().close();

		InputStream responseStream = httpConn.getResponseCode() / 100 == 2 ? httpConn.getInputStream()
				: httpConn.getErrorStream();
		if ("gzip".equals(httpConn.getContentEncoding())) {
			responseStream = new GZIPInputStream(responseStream);
		}
		Scanner s = new Scanner(responseStream).useDelimiter("\\A");
		String response = s.hasNext() ? s.next() : "";
		System.out.println(response);
		s.close();
	}
}
