package com.app.util;

import java.io.IOException;
import java.net.URI;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.app.utils.HttpClientUtil;

public class HttpTest {

	private static final String HEADER_CONTENT_TYPE = "Content-Type";
	private static final String MIME_APPLICATION_JSON = "text/plain";

	private static final String HEADER_ACCEPT = "Accept";
	private static final String ENCODE_8859 = "ISO-8859-1";
	private static final String ENCODE_UTF = "UTF-8";

	public String post(String url, String data) {
		final HttpPost post = new HttpPost(url);
		final DefaultHttpClient httpClient = new DefaultHttpClient();
		post.setHeader(HEADER_ACCEPT, MIME_APPLICATION_JSON);
		post.setHeader(HEADER_CONTENT_TYPE, MIME_APPLICATION_JSON);
		StringEntity reqEntity = null;
		String result = "";
		try {

			reqEntity = new StringEntity(data, MIME_APPLICATION_JSON, ENCODE_UTF);
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
	
	public String get(String url) throws Exception{
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

	public static void main(String[] args) {
		String getUrl = "http://localhost:8080/wx_app/?signature=1&timestamp=1&nonce=1&echostr=abcd";
		String postUrl = "http://localhost:8080/wx_app/";
		String data = "<xml>" + " <ToUserName><![CDATA[toUser]]></ToUserName>" + " <FromUserName><![CDATA[fromUser]]></FromUserName>" + " <CreateTime>1348831860</CreateTime>" + " <MsgType><![CDATA[text]]></MsgType>" + "<Content><![CDATA[this is a test]]></Content>" + " <MsgId>1234567890123456</MsgId>" + " </xml>";
		new HttpTest().post(postUrl, data);
		try {
			new HttpTest().get(getUrl);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
