package com.shipwire;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

public class RetrieveOrder {	
TestUtil http = new TestUtil();
	//method to retrieve this order API
	public String[] method(String url) throws ClientProtocolException, IOException
	{	
		String result[]=http.getMethod(url);
		System.out.println(result);
		return result;
	
	}
}
