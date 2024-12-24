package api.test;

import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.storeEndpoints;
import api.payload.Store;
import io.restassured.response.Response;

public class storeTests {
	
	Faker faker;
	Store storePayload;
	
	@BeforeTest
	void setUp() {
		faker = new Faker();
		storePayload = new Store();
		
		
		// Set up store details without petId (petId will be set later)
		storePayload.setId(faker.number().randomDigitNotZero());
		//storePayload.setPetId(faker.number().numberBetween(1, 10));
		storePayload.setQuantity(faker.number().numberBetween(1, 10));
		storePayload.setShipDate(faker.date().future(10, TimeUnit.DAYS).toInstant().toString());
		storePayload.setStatus(faker.options().option("placed", "approved", "delivered"));
		storePayload.setComplete(faker.bool().bool());	
	}
	
	
	//Create Order
	@Test(priority=1)
	void testpostOrder(ITestContext context) {
		
		 // Retrieve petId from ITestContext
		int PetId =(int) context.getAttribute("PetId"); // Retrieve the petId
		
		// Set the petId in the storePayload
		storePayload.setPetId(PetId);
		
		Response response= storeEndpoints.createOrder(storePayload);
		response.then().log().all();
		
		Assert.assertEquals(response.statusCode(), 200);
	}
	
	//Read order
	@Test(priority=2)
	void testgetOrder() {
		
		Response response = storeEndpoints.readOrder(this.storePayload.getId());
		response.then().log().all();
		
		Assert.assertEquals(response.statusCode(), 200);
	}
	
	//Delete order
	@Test(priority=3)
	void testdeletOrder() {
		
		Response response = storeEndpoints.deleteOrder(this.storePayload.getId());
		response.then().log().all();
		
		Assert.assertEquals(response.statusCode(), 200);
	}
}
