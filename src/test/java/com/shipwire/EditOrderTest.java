package com.shipwire;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class EditOrderTest {
	
	
	String url;
	int id;
	String input;
	String emailId,myName,myCompany;
	TestUtil testutil= new TestUtil();
	String cancelUrl;
	// method to edit the order
	// asserting the status code, response_id, ship to user emailId,
	//ship to user name , ship to user company name
	@Test
	public void editOrder() throws ClientProtocolException, IOException
	{
		EditOrder order = new EditOrder();
		String[] result=order.method(url+id,input);
		JSONObject json = new JSONObject(result[1]);
		JSONObject resource = (json.getJSONObject("resource"));
		int response_Id=resource.getInt("id");
	
		String response_email=resource.getJSONObject("shipTo").getJSONObject("resource").getString("email");
		String response_name=resource.getJSONObject("shipTo").getJSONObject("resource").getString("name");
		String response_company=resource.getJSONObject("shipTo").getJSONObject("resource").getString("company");
		Assert.assertEquals(result[0], "200");
		Assert.assertEquals(response_Id, id);
		Assert.assertEquals(emailId, response_email);
		Assert.assertEquals(myName, response_name);
		Assert.assertEquals(myCompany, response_company);
		cancelUrl=cancelUrl+id+"/cancel";

	}
		
	// in this method we are creating an order 
	@BeforeTest
	public void setup() throws ClientProtocolException, IOException
	{
		//creating a new order and that will return the ID
		CreateOrder order =new CreateOrder();
		String sample=testutil.randomGeneratorUtil();
		input = "{ \"orderNo\": \""+sample+"\", \"externalId\": \"E"+sample+"\", \"processAfterDate\": \"2014-06-10T16:30:00-07:00\", \"items\": [ { \"sku\": \"Laura-s_Pen\", \"quantity\": 4, \"commercialInvoiceValue\": 4.5, \"commercialInvoiceValueCurrency\": \"USD\" }, { \"sku\": \"TwinPianos\", \"quantity\": 4, \"commercialInvoiceValue\": 6.5, \"commercialInvoiceValueCurrency\": \"USD\" } ], \"options\": { \"warehouseId\": 56, \"warehouseExternalId\": null, \"warehouseRegion\": \"LAX\", \"warehouseArea\": null, \"serviceLevelCode\": \"1D\", \"carrierCode\": null, \"sameDay\": \"NOT REQUESTED\", \"channelName\": \"My Channel\", \"forceDuplicate\": 0, \"forceAddress\": 0, \"referrer\": \"Foo Referrer\", \"affiliate\": null, \"currency\": \"USD\", \"canSplit\": 1, \"note\": \"notes\", \"hold\": 1, \"holdReason\": \"test reason\", \"discountCode\": \"FREE STUFF\", \"server\": \"Production\" }, \"shipFrom\": {\"company\": \"We Sell'em Co.\"}, \"shipTo\": { \"email\": \"audrey.horne@greatnothern.com\", \"name\": \"Audrey Horne\", \"company\": \"Audrey's Bikes\", \"address1\": \"6501 Railroad Avenue SE\", \"address2\": \"Room 315\", \"address3\": \"\", \"city\": \"Snoqualmie\", \"state\": \"WA\", \"postalCode\": \"98065\", \"country\": \"US\", \"phone\": \"4258882556\", \"isCommercial\": 0, \"isPoBox\": 0 }, \"commercialInvoice\": { \"shippingValue\": 4.85, \"insuranceValue\": 6.57, \"additionalValue\": 8.29, \"shippingValueCurrency\": \"USD\", \"insuranceValueCurrency\": \"USD\", \"additionalValueCurrency\": \"USD\" }, \"packingList\": { \"message1\": { \"body\": \"This must be where pies go when they die. Enjoy!\", \"header\": \"Enjoy this product!\" } } }";
		id=order.createOrderWithID("https://api.shipwire.com/api/v3/orders", input);
		//creating random email,company name and user name and trying to update
		emailId="TestMail"+sample+"@gmail.com";
		myCompany="myCompanyName"+sample;
		myName="myName"+sample;
		url = "https://api.shipwire.com/api/v3/orders/";
		cancelUrl=url;
		input = "{ \"orderNo\": \""+sample+"\", \"externalId\": \"E"+sample+"\", \"processAfterDate\": \"2014-06-10T16:30:00-07:00\", \"items\": [ { \"sku\": \"Laura-s_Pen\", \"quantity\": 4, \"commercialInvoiceValue\": 4.5, \"commercialInvoiceValueCurrency\": \"USD\" }, { \"sku\": \"TwinPianos\", \"quantity\": 4, \"commercialInvoiceValue\": 6.5, \"commercialInvoiceValueCurrency\": \"USD\" } ], \"options\": { \"warehouseId\": 56, \"warehouseExternalId\": null, \"warehouseRegion\": \"LAX\", \"warehouseArea\": null, \"serviceLevelCode\": \"1D\", \"carrierCode\": null, \"sameDay\": \"NOT REQUESTED\", \"channelName\": \"My Channel\", \"forceDuplicate\": 0, \"forceAddress\": 0, \"referrer\": \"Foo Referrer\", \"affiliate\": null, \"currency\": \"USD\", \"canSplit\": 1, \"note\": \"notes\", \"hold\": 1, \"holdReason\": \"test reason\", \"discountCode\": \"FREE STUFF\", \"server\": \"Production\" }, \"shipFrom\": {\"company\": \"We Sell'em Co.\"}, \"shipTo\": { \"email\": \""+emailId+"\", \"name\": \""+myName+"\", \"company\": \""+myCompany+"\", \"address1\": \"6501 Railroad Avenue SE\", \"address2\": \"Room 315\", \"address3\": \"\", \"city\": \"Snoqualmie\", \"state\": \"WA\", \"postalCode\": \"98065\", \"country\": \"US\", \"phone\": \"4258882556\", \"isCommercial\": 0, \"isPoBox\": 0 }, \"commercialInvoice\": { \"shippingValue\": 4.85, \"insuranceValue\": 6.57, \"additionalValue\": 8.29, \"shippingValueCurrency\": \"USD\", \"insuranceValueCurrency\": \"USD\", \"additionalValueCurrency\": \"USD\" }, \"packingList\": { \"message1\": { \"body\": \"This must be where pies go when they die. Enjoy!\", \"header\": \"Enjoy this product!\" } } }";
	}
	@AfterTest
	public void tearDown() throws ClientProtocolException, IOException
	{
		//finally canceling the order and assering the response from order cancel API
		CancelOrder cancel = new CancelOrder();
		String results[]=cancel.method(cancelUrl);
		Assert.assertEquals(results[0], "200");
		JSONObject json = new JSONObject(results[1]);
		Assert.assertEquals(json.get("message"), "Order cancelled");
	}

}
