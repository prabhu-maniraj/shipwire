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

public class CreateOrderTest {
	
	String url;
	int id;
	String emailId;
	String input;
	TestUtil testutil= new TestUtil();
	String cancelUrl;
	// method to create an order
	// asserting the response with status code and then asserting with emailID
	@Test
	public void createOrder() throws ClientProtocolException, IOException
	{
		
		CreateOrder order = new CreateOrder();
		String result[]=order.method(url,input);
		JSONObject json = new JSONObject(result[1]);
		JSONObject resource = (json.getJSONObject("resource"));
		JSONArray items= resource.getJSONArray("items");	
		System.out.println("-------------------------------------");
		JSONObject items_resource=(JSONObject) items.get(0);
		int id= items_resource.getJSONObject("resource").getInt("id");
		JSONObject items_2=items.getJSONObject(0);
		String response_email=items_2.getJSONObject("resource").getJSONObject("shipTo").getJSONObject("resource").getString("email");
		Assert.assertEquals(result[0], "200");
		Assert.assertEquals(emailId, response_email);
		cancelUrl=cancelUrl+id+"/cancel";
		

	}
	
	// this set up will create a random email ID, random orderID and a random externaId 
	// which then appended to the input
	@BeforeTest
	public void setup()
	{
		String sample=testutil.randomGeneratorUtil();
		emailId="TestMail"+sample+"@gmail.com";
		url = "https://api.shipwire.com/api/v3/orders";
		cancelUrl=url+"/";
		input = "{ \"orderNo\": \""+sample+"\", \"externalId\": \"E"+sample+"\", \"processAfterDate\": \"2014-06-10T16:30:00-07:00\", \"items\": [ { \"sku\": \"Laura-s_Pen\", \"quantity\": 4, \"commercialInvoiceValue\": 4.5, \"commercialInvoiceValueCurrency\": \"USD\" }, { \"sku\": \"TwinPianos\", \"quantity\": 4, \"commercialInvoiceValue\": 6.5, \"commercialInvoiceValueCurrency\": \"USD\" } ], \"options\": { \"warehouseId\": 56, \"warehouseExternalId\": null, \"warehouseRegion\": \"LAX\", \"warehouseArea\": null, \"serviceLevelCode\": \"1D\", \"carrierCode\": null, \"sameDay\": \"NOT REQUESTED\", \"channelName\": \"My Channel\", \"forceDuplicate\": 0, \"forceAddress\": 0, \"referrer\": \"Foo Referrer\", \"affiliate\": null, \"currency\": \"USD\", \"canSplit\": 1, \"note\": \"notes\", \"hold\": 1, \"holdReason\": \"test reason\", \"discountCode\": \"FREE STUFF\", \"server\": \"Production\" }, \"shipFrom\": {\"company\": \"We Sell'em Co.\"}, \"shipTo\": { \"email\": \""+emailId+"\", \"name\": \"Audrey Horne\", \"company\": \"Audrey's Bikes\", \"address1\": \"6501 Railroad Avenue SE\", \"address2\": \"Room 315\", \"address3\": \"\", \"city\": \"Snoqualmie\", \"state\": \"WA\", \"postalCode\": \"98065\", \"country\": \"US\", \"phone\": \"4258882556\", \"isCommercial\": 0, \"isPoBox\": 0 }, \"commercialInvoice\": { \"shippingValue\": 4.85, \"insuranceValue\": 6.57, \"additionalValue\": 8.29, \"shippingValueCurrency\": \"USD\", \"insuranceValueCurrency\": \"USD\", \"additionalValueCurrency\": \"USD\" }, \"packingList\": { \"message1\": { \"body\": \"This must be where pies go when they die. Enjoy!\", \"header\": \"Enjoy this product!\" } } }";
		
	}
	// after all the tests, this method will cancel the created order
	@AfterTest
	public void tearDown() throws ClientProtocolException, IOException
	{
		CancelOrder cancel = new CancelOrder();
		String results[]=cancel.method(cancelUrl);
		Assert.assertEquals(results[0], "200");
		JSONObject json = new JSONObject(results[1]);
		Assert.assertEquals(json.get("message"), "Order cancelled");
	}

}
