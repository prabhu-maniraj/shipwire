package com.shipwire;

import java.io.IOException;
import org.apache.http.client.ClientProtocolException;
import org.json.JSONArray;
import org.json.JSONObject;


public class CreateOrder {
	//Test Util class which has all Http methods
	TestUtil http = new TestUtil();
	
	//method to access the create order API with body content
	public String[] method(String url,String input) throws ClientProtocolException, IOException
	{
		// accesssing the http methods
		String result[]=http.postMethod(url,input);
		return result;
	
	}
	//method to access the create order API without body content
	// this will return the id of the created order
	public int createOrderWithID(String url,String input) throws ClientProtocolException, IOException
	{
		String result[]=http.postMethod(url,input);
		JSONObject json = new JSONObject(result[1]);
		JSONObject resource = (json.getJSONObject("resource"));
		JSONArray items= resource.getJSONArray("items");
		JSONObject items_resource=(JSONObject) items.get(0);
		int id= items_resource.getJSONObject("resource").getInt("id");
		return id;
	
	}
    
    }


