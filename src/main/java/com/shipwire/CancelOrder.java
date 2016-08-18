package com.shipwire;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

public class CancelOrder {
	TestUtil http = new TestUtil();
	//Post mthod to access cancel order API
		public String[] method(String url) throws ClientProtocolException, IOException
		{
			String result[]=http.postMethodWithoutInput(url);
			System.out.println(result.toString());
			return result;
		
		}
	}


