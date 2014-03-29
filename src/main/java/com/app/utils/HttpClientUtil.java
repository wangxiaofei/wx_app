package com.app.utils;
import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

/**
 * 
 * 使用HttpClient工具包进行REST通讯，发送请求、接收响应；接收请求、回复响应。
 * 
 * @author Yao Jiaqing,leiwen[leiwen@miaozhen.com]
 */
public class HttpClientUtil {

	private static final String HEADER_CONTENT_TYPE = "Content-Type";
	private static final String MIME_APPLICATION_JSON = "text/plain";

	private static final String HEADER_ACCEPT = "Accept";
	private static final String ENCODE_8859 = "ISO-8859-1";
	private static final String ENCODE_UTF = "UTF-8";

	public static String getMethod(final String url) throws HttpException,
			IOException,Exception {
		String result = "";
		final HttpGet httpGet = new HttpGet(url);
		final DefaultHttpClient httpClient = new DefaultHttpClient();
		httpGet.setHeader(HEADER_ACCEPT, MIME_APPLICATION_JSON);
		try {
			HttpResponse response;
			try {
				response = httpClient.execute(httpGet);
			} catch (Exception e) {
				throw new Exception("服务器连接出错，请联系管理员！");
			}
			final HttpEntity entity = response.getEntity();
			final String status = response.getStatusLine().toString();
			final String body = EntityUtils.toString(entity);
			result = body;

		} catch (final ClientProtocolException e) {
			e.printStackTrace();
		} catch (final IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * data写在http请求的body里，而不是header
	 * 
	 * @param url
	 * @param data
	 * @return
	 * @throws HttpException
	 * @throws IOException
	 */
	public static String postMethod(final String url, final String data)
			throws HttpException, IOException {
		final HttpPost post = new HttpPost(url);
		final DefaultHttpClient httpClient = new DefaultHttpClient();
		post.setHeader(HEADER_ACCEPT, MIME_APPLICATION_JSON);
		post.setHeader(HEADER_CONTENT_TYPE, MIME_APPLICATION_JSON);
		StringEntity reqEntity = null;
		String result = "";
		try {
			reqEntity = new StringEntity(data, MIME_APPLICATION_JSON,
					ENCODE_UTF);
			post.setEntity(reqEntity);
			final HttpResponse response = httpClient.execute(post);
			final HttpEntity entity = response.getEntity();
			final String status = response.getStatusLine().toString();
			final String body = EntityUtils.toString(entity);
			result = status + "\n" + body;
			result = new String(result.getBytes(ENCODE_8859), ENCODE_UTF);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static String putMethod(final String url, final String data)
			throws HttpException, IOException {
		final HttpPut putMethod = new HttpPut(url);
		final DefaultHttpClient httpClient = new DefaultHttpClient();
		putMethod.setHeader(HEADER_ACCEPT, MIME_APPLICATION_JSON);
		putMethod.setHeader(HEADER_CONTENT_TYPE, MIME_APPLICATION_JSON);
		StringEntity reqEntity = null;
		String result = "";
		try {
			reqEntity = new StringEntity(data, MIME_APPLICATION_JSON,
					ENCODE_UTF);
			putMethod.setEntity(reqEntity);
			final HttpResponse response = httpClient.execute(putMethod);
			final HttpEntity entity = response.getEntity();
			final String status = response.getStatusLine().toString();
			final String body = EntityUtils.toString(entity);
			result = status + "\n" + body;
			result = new String(result.getBytes(ENCODE_8859), ENCODE_UTF);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
