package com.nyayas.common.util;

import java.io.IOException;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;

public class JSoupHelper {

	public static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.122 Safari/537.36";
	public static final String XREQWITH = "x-requested-with";
	public static final String XHTTPREQ = "XMLHttpRequest";
	public static final String CONTENT_TYPE = "Content-Type";
	public static final String CONTENT_JSON = "application/json; charset=utf8";
	public static final String CONTENT_XML = "application/xml";
	public static final String CONTENT_PDF = "application/pdf";

	public static final int TIME_OUT = 10000;

	public static final Connection connection(String url, int timeOut) {
		Connection con = Jsoup.connect(url);
		if (url.startsWith("https")) {
			con = con.sslSocketFactory(SSLByPass.bypassSSL());
		}
		return con.header(XREQWITH, XHTTPREQ).ignoreContentType(true).ignoreHttpErrors(true).maxBodySize(0)
				.timeout(timeOut);
	}

	public static final Connection getConnection(String url, int timeOut) {
		return connection(url, timeOut).method(Method.GET);
	}

	public static final Connection getConnection(String url, String headerKey, String headerValue, int timeOut) {
		return getConnection(url, timeOut).header(headerKey, headerValue);
	}

	public static final Connection getConnection(String url, Map<String, String> headers, int timeOut) {
		return getConnection(url, timeOut).headers(headers);
	}

	public static final Connection getConnection(String url, Map<String, String> headers, Map<String, String> data,
			int timeOut) {
		return getConnection(url, headers, timeOut).data(data);
	}

	public static final Response getResponse(String url, int timeOut) throws IOException {
		return getConnection(url, timeOut).execute();
	}

	public static final Response getResponse(String url, String headerKey, String headerValue, int timeOut)
			throws IOException {
		return getConnection(url, headerKey, headerValue, timeOut).execute();
	}

	public static final Response getResponse(String url, Map<String, String> headers, int timeOut) throws IOException {
		return getConnection(url, headers, timeOut).execute();
	}

	public static final Connection postConnection(String url, int timeOut) {
		return connection(url, timeOut).method(Method.POST);
	}

	public static final Connection postConnection(String url, String headerKey, String headerValue, int timeOut) {
		return postConnection(url, timeOut).header(headerKey, headerValue);
	}

	public static final Connection postConnection(String url, Map<String, String> headers, int timeOut) {
		return postConnection(url, timeOut).headers(headers);
	}

	public static final Connection postConnection(String url, Map<String, String> headers, Map<String, String> data,
			int timeOut) {
		return postConnection(url, headers, timeOut).data(data);
	}

	public static final Response postResponse(String url, int timeOut) throws IOException {
		return postConnection(url, timeOut).execute();
	}

	public static final Response postResponse(String url, String headerKey, String headerValue, int timeOut)
			throws IOException {
		return postConnection(url, headerKey, headerValue, timeOut).execute();
	}

	public static final Response postResponse(String url, Map<String, String> headers, int timeOut) throws IOException {
		return postConnection(url, headers, timeOut).execute();
	}

	public static final Response postResponse(String url, Map<String, String> headers, Map<String, String> data,
			int timeOut) throws IOException {
		return postConnection(url, headers, data, timeOut).execute();
	}

	public static final Response postResponse(String url, String reqBody, int timeOut) throws IOException {
		return postConnection(url, timeOut).requestBody(reqBody).execute();
	}

	public static final Response postResponse(String url, String headerKey, String headerValue, String reqBody,
			int timeOut) throws IOException {
		return postConnection(url, headerKey, headerValue, timeOut).requestBody(reqBody).execute();
	}

	public static final Response postResponse(String url, Map<String, String> headers, String reqBody, int timeOut)
			throws IOException {
		return postConnection(url, headers, timeOut).requestBody(reqBody).execute();
	}

}