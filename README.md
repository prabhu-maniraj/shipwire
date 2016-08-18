## Shipwire coding Challenge

This project involves Java,Maven,and TestNG. All the dependencies are in the pom.xml

## This project has 4 test cases

1. CreateOrder:
	In this test a new order is created and then it is asserted with the recepient emailId and status code,
	finally after test the created order is canceled and asserted
2. Retrive Order:	
	For this test a new order is created then it is asserted with the response order ID and the status code,
	and then the created order is canceled.
3. Edit order:
	A new order is created then, we generate random values for eamailId,Company and user name where the product is shipped,
	then we perform update operation and finally asserting all the updated values in the respose. And as usual canceling the dumped order.
4. Cancel order:
	A seperate test to test the cancel order API, here a new order is created then asserted with response order ID ad status code and finally 
	order cancellation is performed

###Test case results can be viewed in the test-output folder