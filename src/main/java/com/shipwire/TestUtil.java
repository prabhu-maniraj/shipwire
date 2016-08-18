package com.shipwire;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;

public class TestUtil {

	public String[] postMethod(String url,String input) throws ClientProtocolException, IOException
	{
		String []result = new String[2];
		
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpPost postRequest = new HttpPost(url);		
		
		HttpEntity entity = new ByteArrayEntity(input.getBytes("UTF-8"));
	    postRequest.setEntity(entity);
		
		
		
		postRequest.setHeader("Authorization", "Basic c2hpcHRlc3RAbWFpbGluYXRvci5jb20gOnRlc3QxMjM0");

		HttpResponse response = httpClient.execute(postRequest);

		System.out.println(response.getStatusLine().getStatusCode());
		result[0]= ""+response.getStatusLine().getStatusCode();
		BufferedReader br = new BufferedReader(
	                    new InputStreamReader((response.getEntity().getContent())));

		String output;
		StringBuilder sb = new StringBuilder();
		System.out.println("Output from Server .... \n");
		
		while ((output = br.readLine()) != null) {
			sb.append(output);
			
		}
		result[1]= sb.toString();
		System.out.println("inter"+sb.toString());
		httpClient.getConnectionManager().shutdown();
		return result;
	}

	public String[] postMethodWithoutInput(String url) throws ClientProtocolException, IOException
	{
		String []result = new String[2];
		
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpPost postRequest = new HttpPost(url);

		postRequest.setHeader("Authorization", "Basic c2hpcHRlc3RAbWFpbGluYXRvci5jb20gOnRlc3QxMjM0");

		HttpResponse response = httpClient.execute(postRequest);

		System.out.println(response.getStatusLine().getStatusCode());
		result[0]= ""+response.getStatusLine().getStatusCode();
		BufferedReader br = new BufferedReader(
	                    new InputStreamReader((response.getEntity().getContent())));

		String output;
		StringBuilder sb = new StringBuilder();
		System.out.println("Output from Server .... \n");
		
		while ((output = br.readLine()) != null) {
			sb.append(output);
			
		}
		result[1]= sb.toString();
		System.out.println(sb.toString());
		httpClient.getConnectionManager().shutdown();
		return result;
	}

	public String[] getMethod(String url) throws ClientProtocolException, IOException
	{
		String []result = new String[2];
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpGet getRequest = new HttpGet(url);

		
		getRequest.setHeader("Authorization", "Basic c2hpcHRlc3RAbWFpbGluYXRvci5jb20gOnRlc3QxMjM0");

		HttpResponse response = httpClient.execute(getRequest);

		System.out.println(response.getStatusLine().getStatusCode());
		
		result[0]= ""+response.getStatusLine().getStatusCode();
		BufferedReader br = new BufferedReader(
	                    new InputStreamReader((response.getEntity().getContent())));


		
		String output;
		StringBuilder sb = new StringBuilder();
		System.out.println("Output from Server .... \n");
		
		while ((output = br.readLine()) != null) {
			sb.append(output);
			
		}
		
		result[1]= sb.toString();
		System.out.println("inter"+sb.toString());
		httpClient.getConnectionManager().shutdown();
		return result;
	}
	

	public String[] putMethod(String url,String input) throws ClientProtocolException, IOException
	{
		String []result = new String[2];
		
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpPut putRequest = new HttpPut(url);		
		
		HttpEntity entity = new ByteArrayEntity(input.getBytes("UTF-8"));
	    putRequest.setEntity(entity);
		
		
		
		putRequest.setHeader("Authorization", "Basic c2hpcHRlc3RAbWFpbGluYXRvci5jb20gOnRlc3QxMjM0");

		HttpResponse response = httpClient.execute(putRequest);

		System.out.println(response.getStatusLine().getStatusCode());
		result[0]= ""+response.getStatusLine().getStatusCode();
		BufferedReader br = new BufferedReader(
	                    new InputStreamReader((response.getEntity().getContent())));

		String output;
		StringBuilder sb = new StringBuilder();
		System.out.println("Output from Server .... \n");
		
		while ((output = br.readLine()) != null) {
			sb.append(output);
			
		}
		result[1]= sb.toString();
		System.out.println("inter"+sb.toString());
		httpClient.getConnectionManager().shutdown();
		return result;
	}
	
	
	public String randomGeneratorUtil()
	{
		String value = "a"+(int)(Math.random() * 9999999 + 1);
		return  value;
	}

}
