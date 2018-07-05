package com.cjj.learn.java.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

public class HttpClientUtil {

	public HttpClientUtil() {
	}

	/**
	 * 向指定URL发送GET方法的请求
	 * 
	 * @param url 发送请求的URL
	 * @param param 请求参数。
	 * @param headers 需要添加的httpheader参数
	 * @param timeout 请求超时时间
	 */
	public static void get(String url, String param, Map<String, String> headers, int timeout) {
		CloseableHttpClient client = null;
		String reqUrl = null;
		if (StringUtil.isNotNullOrEmpty(param)) {
			reqUrl = url + "?" + param;
		} else {
			reqUrl = url;
		}
		try {
			// 构造httprequest设置
			RequestConfig config = RequestConfig.custom().setConnectTimeout(timeout)
					.setConnectionRequestTimeout(timeout).build();
			client = HttpClientBuilder.create().setDefaultRequestConfig(config).build();
			HttpGet htGet = new HttpGet(reqUrl);
			// 添加http headers
			if (headers != null && headers.size() > 0) {
				for (String key : headers.keySet()) {
					htGet.addHeader(key, headers.get(key));
				}
			}
			// 执行get请求.    
			CloseableHttpResponse response = client.execute(htGet);  
			try {  
				// 获取响应实体    
				HttpEntity entity = response.getEntity();  
				System.out.println("--------------------------------------");  
				// 打印响应状态    
				System.out.println(response.getStatusLine());  
				if (entity != null) {  
					// 打印响应内容长度    
					System.out.println("Response content length: " + entity.getContentLength());  
					// 打印响应内容    
					System.out.println("Response content: " + EntityUtils.toString(entity));  
				}  
				System.out.println("------------------------------------");  
			} finally {  
				response.close();  
			}  
		} catch (Exception e) {
			System.out.println("发送GET请求出现异常！" + e);
			e.printStackTrace();
		} finally {  
			// 关闭连接,释放资源    
			try {  
				if (client != null) {
					client.close();  
				}
			} catch (IOException e) {  
				e.printStackTrace();  
			}  
		}
	} 
	
	/**
	 * 向指定URL发送GET方法的请求
	 * 
	 * @param url 发送请求的URL
	 * @param param 请求参数。
	 * @param headers 需要添加的httpheader参数
	 * @param timeout 请求超时时间
	 */
	public static void post(String url, String param, Map<String, String> headers, int timeout) {
		CloseableHttpClient client = null;
		try {
			// 构造httprequest设置
			RequestConfig config = RequestConfig.custom().setConnectTimeout(timeout)
					.setConnectionRequestTimeout(timeout).build();
			client = HttpClientBuilder.create().setDefaultRequestConfig(config).build();
			HttpPost htPost = new HttpPost(url);
			RequestEntity re = null;
			htPost.setEntity(new StringEntity(param,"UTF-8"));
			// 添加http headers
			if (headers != null && headers.size() > 0) {
				for (String key : headers.keySet()) {
					htPost.addHeader(key, headers.get(key));
				}
			}
			// 执行get请求.    
			CloseableHttpResponse response = client.execute(htPost);  
			try {  
				// 获取响应实体    
				HttpEntity entity = response.getEntity();  
				System.out.println("--------------------------------------");  
				// 打印响应状态    
				System.out.println(response.getStatusLine());  
				if (entity != null) {  
					// 打印响应内容长度    
					System.out.println("Response content length: " + entity.getContentLength());  
					// 打印响应内容    
					System.out.println("Response content: " + EntityUtils.toString(entity));  
				}  
				System.out.println("------------------------------------");  
			} finally {  
				response.close();  
			}  
		} catch (Exception e) {
			System.out.println("发送GET请求出现异常！" + e);
			e.printStackTrace();
		} finally {  
			// 关闭连接,释放资源    
			try {  
				if (client != null) {
					client.close();  
				}
			} catch (IOException e) {  
				e.printStackTrace();  
			}  
		}
	} 

	public static void main(String[] args) throws Exception {
		// 测试的 url 见PSB psbHeaderFlow
		String url = null;
		String param = null;
		Map<String,String> headers = new HashMap<String,String>();
		int timeout = 3000;
		
		System.out.println("***************HttpClientUtil.get*********************");
		url = "http://127.0.0.1:8081/";
		param = null;
		headers.put("type", "rest");
		HttpClientUtil.get(url, param, headers, timeout);
		
		System.out.println("***************HttpClientUtil.post*********************");
		url = "http://127.0.0.1:8081/";
		param = "<tsh:OrderTshirt xmlns:tsh=\"http://mulesoft.org/tshirt-service\">"
				+ "<size>L</size>"
				+ "<email>cjj@cjj1234.com</email>"
				+ "<name>cjj</name>"
				+ "<address1>address1.....</address1>"
				+ "<address2>address2.....</address2>"
				+ "<city>city..hz</city>"
				+ "<stateOrProvince>stateOrProvince..zj</stateOrProvince>"
				+ "<postalCode>331400</postalCode>"
				+ "<country>china</country>"
				+ "</tsh:OrderTshirt>";
		headers.put("type", "orderTshirt");
		HttpClientUtil.post(url, param, headers, timeout);
	}

}
