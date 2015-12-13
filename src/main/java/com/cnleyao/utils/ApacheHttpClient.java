package com.cnleyao.utils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.ByteArrayRequestEntity;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApacheHttpClient {
	
	private final static Logger log = LoggerFactory.getLogger(ApacheHttpClient.class);
	
	private MultiThreadedHttpConnectionManager connectionManager;
	private HttpClient client;
	
	private int maxSize;
	private Map<String, String> defaultHeaders;
	
	public static class UTF8PostMethod extends PostMethod{     
		public UTF8PostMethod(String url) {
			super(url);     
		}
		
		public String getRequestCharSet() { 
			return "UTF-8";     
		}
	}
		
	public ApacheHttpClient() {
		this(150, 1000, 1000, 1024 * 1024);
	}
	
  	public ApacheHttpClient(int maxConPerHost, int conTimeOutMs, int soTimeOutMs, int maxSize) {
  		connectionManager = new MultiThreadedHttpConnectionManager(); 
  		HttpConnectionManagerParams params = connectionManager.getParams();
  		params.setDefaultMaxConnectionsPerHost(maxConPerHost);
  		params.setConnectionTimeout(conTimeOutMs);
  		params.setSoTimeout(soTimeOutMs);
  		
  		client = new HttpClient(connectionManager);
  		
  		this.maxSize = maxSize;
  	}
  	
  	public HttpResponse get(String url) {
  		return get(url, null);
  	}
  	public HttpResponse get(String url, Map<String, String> headers) {
  		HttpResponse response = new HttpResponse();
  		ByteArrayOutputStream out = new ByteArrayOutputStream(); 
  		try {
			if (log.isDebugEnabled()) {
				long t = System.currentTimeMillis();
				response.setStatusCode(get(url, out, headers));
				response.setResponse(new String(out.toByteArray(), "utf-8"));
				log.debug("getURL:" + url + ", response:" + response + ", time:" + (System.currentTimeMillis() - t));
			} else {
				response.setStatusCode(get(url, out, headers));
				response.setResponse(new String(out.toByteArray(), "utf-8"));
			}
		} catch (Exception e) {
			response.setStatusCode(500);
			log.warn("getURL error, URL:" + url, e);
		}
		
		return response;
  	}
  	
  	public int get(String url, OutputStream out, Map<String, String> headers) {
  		int readLen = 0;
  		HttpMethod get = new GetMethod(url);
  		if (defaultHeaders != null && !defaultHeaders.isEmpty()) {
    		for (Map.Entry<String, String> entry : defaultHeaders.entrySet())
    			get.addRequestHeader(entry.getKey(), entry.getValue());
    	}
    	
    	if (headers != null && !headers.isEmpty()) {
    		for (Map.Entry<String, String> entry : headers.entrySet())
    			get.addRequestHeader(entry.getKey(), entry.getValue());
    	}
    	
        try {
            client.executeMethod(get);
            InputStream in = get.getResponseBodyAsStream();
			byte[] b = new byte[1024];
			int len = 0;
			while ((len = in.read(b)) > 0) {
				out.write(b, 0, len);
				readLen += len;
				if (readLen > maxSize)
					break;
			}
			in.close();
			return get.getStatusCode();
        } catch (Exception ex) {
        	log.warn("HTTP GET " + url + " RETURN " + ex.getMessage());
        	return 500;
        } finally {
            get.releaseConnection();
        }
  	}
  	
  	public HttpResponse post(String url, Map<String, String> nameValues) {
  		ByteArrayOutputStream out = new ByteArrayOutputStream(); 
  		HttpResponse response = new HttpResponse();
  		try {
			if (log.isDebugEnabled()) {
				long t = System.currentTimeMillis();
				response.setStatusCode(post(url, nameValues, out));
				response.setResponse(new String(out.toByteArray(), "utf-8"));
				if (log.isDebugEnabled())
					log.debug("getURL:" + url + ", nameValues:" + replacePwd(String.valueOf(nameValues)) + ", result:" + response + ", time:" + (System.currentTimeMillis() - t));
			} else {
				response.setStatusCode(post(url, nameValues, out));
				response.setResponse(new String(out.toByteArray(), "utf-8"));
			}
		} catch (Exception e) {
			response.setStatusCode(500);
		}
		
		return response;
  	}
  	
  	private static String replacePwd(String str) {
  		String s = str.toLowerCase();
  		int pos = s.indexOf("pwd=");
  		if (pos < 0)
  			pos = s.indexOf("password=");
  		if (pos >= 0) {
  			int pos1 = s.indexOf("=", pos + 1);
  			int pos2 = s.indexOf(", ", pos + 1);
  			if (pos1 > 0 && pos2 > 0)
  				return str.substring(0, pos1 + 1) + "***" + str.substring(pos2);
  		}
  		return str;
  	}
  	
  	public int post(String url, Map<String, String> nameValues, OutputStream out) {
  		return post(url, null, nameValues, out);
  	}
  	
  	public int post(String url, Map<String, String> headers, Map<String, String> nameValues, OutputStream out) {
  		int readLen = 0;
  		PostMethod post = new UTF8PostMethod(url);
  		
  		if (defaultHeaders != null && !defaultHeaders.isEmpty()) {
    		for (Map.Entry<String, String> entry : defaultHeaders.entrySet())
    			post.addRequestHeader(entry.getKey(), entry.getValue());
    	}
    	
    	if (headers != null && !headers.isEmpty()) {
    		for (Map.Entry<String, String> entry : headers.entrySet())
    			post.addRequestHeader(entry.getKey(), entry.getValue());
    	}
    	
  		if (nameValues != null && !nameValues.isEmpty()) {
  			List<NameValuePair> list = new ArrayList<NameValuePair>(nameValues.size());
  			for (Map.Entry<String, String> entry : nameValues.entrySet()) {
  				list.add(new NameValuePair(entry.getKey(), entry.getValue()));
			}
  			post.setRequestBody(list.toArray(new NameValuePair[list.size()]));
  		}
  		
        try {
            client.executeMethod(post);
            InputStream in = post.getResponseBodyAsStream();
			byte[] b = new byte[1024];
			int len = 0;
			while ((len = in.read(b)) > 0) {
				out.write(b, 0, len);
				readLen += len;
				if (readLen > maxSize)
					break;
			}
			in.close();
			return post.getStatusCode();
        } catch (Exception ex) {
        	log.warn("HTTP GET " + url + " RETURN " + ex.getMessage());
        	return 500;
        } finally {
            post.releaseConnection();
        }
  	}
  	
  	public int post(String url, Map<String, String> headers, byte[] data, OutputStream out) {
  		int readLen = 0;
  		PostMethod post = new PostMethod(url);
  		if (headers != null && !headers.isEmpty()) {
    		for (Map.Entry<String, String> entry : headers.entrySet())
    			post.addRequestHeader(entry.getKey(), entry.getValue());
    	}
  		post.setRequestEntity(new ByteArrayRequestEntity(data));
        try {
            client.executeMethod(post);
            InputStream in = post.getResponseBodyAsStream();
			byte[] b = new byte[1024];
			int len = 0;
			while ((len = in.read(b)) > 0) {
				out.write(b, 0, len);
				readLen += len;
				if (readLen > maxSize)
					break;
			}
			in.close();
			return post.getStatusCode();
        } catch (Exception ex) {
        	log.warn("HTTP POST " + url + " RETURN " + ex.getMessage());
        	return 500;
        } finally {
            post.releaseConnection();
        }
  	}
  	
	public void setProxyHostPort(String proxyHostPort) {
		if (log.isInfoEnabled())
			log.info("setProxyHostPort:" + proxyHostPort);
		
		String host = proxyHostPort;
		int port = 80;
		int pos = proxyHostPort.indexOf(':');
		if (pos > 0) {
			host = proxyHostPort.substring(0, pos);
			port = Integer.parseInt(proxyHostPort.substring(pos + 1).trim());
		}
        client.getHostConfiguration().setProxy(host, port);
	}

	public void setDefaultHeaders(Map<String, String> defaultHeaders) {
		this.defaultHeaders = defaultHeaders;
	}
}
