package com.shipwire;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CancelOrderTest {
	public String url;
	int id;
	String input;
	TestUtil testutil= new TestUtil();
	// test case to cancel the order
	// asserting the status code and the response message given by the API
	@Test
	public void cancelOrder() throws ClientProtocolException, IOException
	{
		
		CancelOrder order = new CancelOrder();
		url=url+id+"/cancel";
		System.out.println(url);
		String result[]=order.method(url);
		Assert.assertEquals(result[0], "200");
		JSONObject json = new JSONObject(result[1]);
		Assert.assertEquals(json.get("message"), "Order cancelled");
	}
	
	// Here a new order is created and a id is retrieved that particular order
	// then for the Id the the order is cancelled
	@BeforeTest
	public void setup() throws ClientProtocolException, IOException
	{
		String sample=testutil.randomGeneratorUtil();
		System.out.println(sample);
		url = "https://api.shipwire.com/api/v3/orders/";
		input = "{ \"orderNo\": \""+sample+"\", \"externalId\": \"E"+sample+"\", \"processAfterDate\": \"2014-06-10T16:30:00-07:00\", \"items\": [ { \"sku\": \"Laura-s_Pen\", \"quantity\": 4, \"commercialInvoiceValue\": 4.5, \"commercialInvoiceValueCurrency\": \"USD\" }, { \"sku\": \"TwinPianos\", \"quantity\": 4, \"commercialInvoiceValue\": 6.5, \"commercialInvoiceValueCurrency\": \"USD\" } ], \"options\": { \"warehouseId\": 56, \"warehouseExternalId\": null, \"warehouseRegion\": \"LAX\", \"warehouseArea\": null, \"serviceLevelCode\": \"1D\", \"carrierCode\": null, \"sameDay\": \"NOT REQUESTED\", \"channelName\": \"My Channel\", \"forceDuplicate\": 0, \"forceAddress\": 0, \"referrer\": \"Foo Referrer\", \"affiliate\": null, \"currency\": \"USD\", \"canSplit\": 1, \"note\": \"notes\", \"hold\": 1, \"holdReason\": \"test reason\", \"discountCode\": \"FREE STUFF\", \"server\": \"Production\" }, \"shipFrom\": {\"company\": \"We Sell'em Co.\"}, \"shipTo\": { \"email\": \"audrey.horne@greatnothern.com\", \"name\": \"Audrey Horne\", \"company\": \"Audrey's Bikes\", \"address1\": \"6501 Railroad Avenue SE\", \"address2\": \"Room 315\", \"address3\": \"\", \"city\": \"Snoqualmie\", \"state\": \"WA\", \"postalCode\": \"98065\", \"country\": \"US\", \"phone\": \"4258882556\", \"isCommercial\": 0, \"isPoBox\": 0 }, \"commercialInvoice\": { \"shippingValue\": 4.85, \"insuranceValue\": 6.57, \"additionalValue\": 8.29, \"shippingValueCurrency\": \"USD\", \"insuranceValueCurrency\": \"USD\", \"additionalValueCurrency\": \"USD\" }, \"packingList\": { \"message1\": { \"body\": \"This must be where pies go when they die. Enjoy!\", \"header\": \"Enjoy this product!\" } } }";
		CreateOrder order =new CreateOrder();
		id=order.createOrderWithID("https://api.shipwire.com/api/v3/orders", input);
	}
	

}
