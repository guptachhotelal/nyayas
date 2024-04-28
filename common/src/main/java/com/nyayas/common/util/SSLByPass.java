package com.nyayas.common.util;

import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class SSLByPass {

    public static final SSLSocketFactory bypassSSL() {
	TrustManager[] trustAll = new TrustManager[] { new X509TrustManager() {

	    @Override
	    public X509Certificate[] getAcceptedIssuers() {
		return null;
	    }

	    @Override
	    public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
	    }

	    @Override
	    public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
	    }

	} };

	SSLContext sslcontext = null;
	try {
	    sslcontext = SSLContext.getInstance("SSL");
	    sslcontext.init(null, trustAll, new SecureRandom());
	} catch (Exception e) {
	    throw new RuntimeException(e);
	}

	SSLSocketFactory scoFactory = sslcontext.getSocketFactory();

	HostnameVerifier allverify = new HostnameVerifier() {
	    @Override
	    public boolean verify(String arg0, SSLSession arg1) {
		return true;
	    }
	};

	HttpsURLConnection.setDefaultHostnameVerifier(allverify);
	return scoFactory;
    }
}
