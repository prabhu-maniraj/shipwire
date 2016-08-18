package com.shipwire;

import java.io.IOException;
import org.apache.http.client.ClientProtocolException;
public class EditOrder {
// Test util class with all the http methods	
TestUtil http = new TestUtil();
	// method to access API which allows the user to modify the order
	public String[] method(String url,String input) throws ClientProtocolException, IOException
	{
		String result[]=http.putMethod(url,input);
		System.out.println(result[1]);
		return result;
	
	}
}
