package com.cjj.learn.java.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.httpclient.params.HttpMethodParams;

/**
 * @Description: 通过HttpClient调用ws，服务端不限制,java的
 */
public class HttpClientUtilWebservice {

	

	/**
	 * 	StringBuilder soapRequest = new StringBuilder();
		soapRequest.append("<?xml version='1.0' encoding='utf-8'?>")
				.append("<soap:Envelope xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance' xmlns:xsd='http://www.w3.org/2001/XMLSchema' xmlns:soap='http://schemas.xmlsoap.org/soap/envelope/'>")

				.append("<soap:Body>")
				.append("<GetCXInfoByV xmlns='http://tempuri.org/'>")

				.append("<v>fdsfsdf</v>")
				.append("</GetCXInfoByV>").append("</soap:Body>")
				.append("</soap:Envelope>");

		String serviceEpr = "http://3.21.57.103:8088/webService/VCXService.asmx";
		String contentType = "text/xml; charset=utf-8";
	 * @param soapRequest
	 * @param serviceEpr
	 * @param contentType
	 * @return
	 */
	public static String callAspWebService(String soapRequest, String serviceEpr,
			String contentType) {
		PostMethod postMethod = new PostMethod(serviceEpr);
		postMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 3000);
		InputStream inputStream = null;
		HttpClient httpClient = null;
		try {
			byte[] b = soapRequest.getBytes("utf-8");
			inputStream = new ByteArrayInputStream(b, 0, b.length);
			RequestEntity re = new InputStreamRequestEntity(inputStream,b.length, contentType);
			postMethod.setRequestEntity(re);

			httpClient = new HttpClient();
			HttpConnectionManagerParams managerParams = httpClient.getHttpConnectionManager().getParams();
			// 设置连接超时时间(单位毫秒)
			managerParams.setConnectionTimeout(3000);
			// 设置读数据超时时间(单位毫秒)
			managerParams.setSoTimeout(3000);
			int statusCode = httpClient.executeMethod(postMethod);
			if (statusCode != HttpStatus.SC_OK){
				throw new IllegalStateException("调用webservice错误 : "
						+ postMethod.getStatusLine());
			}

			String soapRequestData = postMethod.getResponseBodyAsString();
			return soapRequestData;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return "errorMessage 1: " + e.getMessage();
		} catch (HttpException e) {
			e.printStackTrace();
			return "errorMessage 2: " + e.getMessage();
		} catch (IOException e) {
			e.printStackTrace();
			return "3";
		} finally {
			postMethod.releaseConnection();
			if(inputStream!=null){
				try{
					inputStream.close();
				}catch(IOException ex){
					ex.printStackTrace();
				}
			}
		}
	}
	
	
//	public static void main(String[] args) throws Exception {
//		StringBuilder soapRequest = new StringBuilder();
//		soapRequest.append("<?xml version='1.0' encoding='utf-8'?>")
//				.append("<soap:Envelope xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance' xmlns:xsd='http://www.w3.org/2001/XMLSchema' xmlns:soap='http://schemas.xmlsoap.org/soap/envelope/'>")
//
//				.append("<soap:Body>")
//				.append("<GetCXInfoByV xmlns='http://tempuri.org/'>")
//
//				.append("<v>fdsfsdf</v>")
//				.append("</GetCXInfoByV>").append("</soap:Body>")
//				.append("</soap:Envelope>");
//
//		String serviceEpr = "http://3.21.57.103:8088/webService/VCXService.asmx";
//		String contentType = "text/xml; charset=utf-8";
//
//		System.out.println(callAspWebService(soapRequest.toString(), serviceEpr,
//				contentType));
//
//	}
}
