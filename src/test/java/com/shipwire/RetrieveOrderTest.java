package com.shipwire;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class RetrieveOrderTest {
	
String url,input;
int id;
TestUtil testutil= new TestUtil();
CreateOrder order =new CreateOrder();
String cancelUrl;
	//method to retrieve the order
	// asserting the status code and unique orderID
	@Test
	public void retrieveOrder() throws ClientProtocolException, IOException
	{
		RetrieveOrder order = new RetrieveOrder();
		String result[]=order.method(url+id);
		JSONObject json = new JSONObject(result[1]);
		JSONObject resource = (json.getJSONObject("resource"));
		int response_Id=resource.getInt("id");
		Assert.assertEquals(result[0], "200");
		Assert.assertEquals(response_Id, id);
	
	}
	
	
	//creating a new order, fetching the Id and use the id of the order to retirve information
	@BeforeTest
	public void setup() throws ClientProtocolException, IOException
	{
		String sample=testutil.randomGeneratorUtil();
		url = "https://api.shipwire.com/api/v3/orders/";
		cancelUrl=url;
		input = "{ \"orderNo\": \""+sample+"\", \"externalId\": \"E"+sample+"\", \"processAfterDate\": \"2014-06-10T16:30:00-07:00\", \"items\": [ { \"sku\": \"Laura-s_Pen\", \"quantity\": 4, \"commercialInvoiceValue\": 4.5, \"commercialInvoiceValueCurrency\": \"USD\" }, { \"sku\": \"TwinPianos\", \"quantity\": 4, \"commercialInvoiceValue\": 6.5, \"commercialInvoiceValueCurrency\": \"USD\" } ], \"options\": { \"warehouseId\": 56, \"warehouseExternalId\": null, \"warehouseRegion\": \"LAX\", \"warehouseArea\": null, \"serviceLevelCode\": \"1D\", \"carrierCode\": null, \"sameDay\": \"NOT REQUESTED\", \"channelName\": \"My Channel\", \"forceDuplicate\": 0, \"forceAddress\": 0, \"referrer\": \"Foo Referrer\", \"affiliate\": null, \"currency\": \"USD\", \"canSplit\": 1, \"note\": \"notes\", \"hold\": 1, \"holdReason\": \"test reason\", \"discountCode\": \"FREE STUFF\", \"server\": \"Production\" }, \"shipFrom\": {\"company\": \"We Sell'em Co.\"}, \"shipTo\": { \"email\": \"audrey.horne@greatnothern.com\", \"name\": \"Audrey Horne\", \"company\": \"Audrey's Bikes\", \"address1\": \"6501 Railroad Avenue SE\", \"address2\": \"Room 315\", \"address3\": \"\", \"city\": \"Snoqualmie\", \"state\": \"WA\", \"postalCode\": \"98065\", \"country\": \"US\", \"phone\": \"4258882556\", \"isCommercial\": 0, \"isPoBox\": 0 }, \"commercialInvoice\": { \"shippingValue\": 4.85, \"insuranceValue\": 6.57, \"additionalValue\": 8.29, \"shippingValueCurrency\": \"USD\", \"insuranceValueCurrency\": \"USD\", \"additionalValueCurrency\": \"USD\" }, \"packingList\": { \"message1\": { \"body\": \"This must be where pies go when they die. Enjoy!\", \"header\": \"Enjoy this product!\" } } }";
		id=order.createOrderWithID("https://api.shipwire.com/api/v3/orders", input);
		cancelUrl=cancelUrl+id+"/cancel";
	}

	
	@AfterTest
	public void tearDown() throws ClientProtocolException, IOException
	{
		// finally canceling the created order and asserting the response message from order cancel API
		CancelOrder cancel = new CancelOrder();
		String results[]=cancel.method(cancelUrl);
		Assert.assertEquals(results[0], "200");
		JSONObject json = new JSONObject(results[1]);
		Assert.assertEquals(json.get("message"), "Order cancelled");
	}
	

}
