package api.test;

import org.testng.Assert;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.userEndpoints;
import api.payload.User;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
public class userTests {
	
	
	Faker faker;
	User UserPayload;
	
	public Logger logger; // for logs
	
	@BeforeClass
	public void setup() {
		
		faker = new Faker();
		UserPayload = new User();
		
		UserPayload.setId(faker.idNumber().hashCode());
		UserPayload.setUsername(faker.name().username());
		UserPayload.setFirstName(faker.name().firstName());
		UserPayload.setLastName(faker.name().lastName());
		UserPayload.setEmail(faker.internet().emailAddress());
		UserPayload.setPassword(faker.internet().password(5,10));
		UserPayload.setPhone(faker.phoneNumber().cellPhone());
		
		//logs
		 logger = LogManager.getLogger(this.getClass());
	}
    
	// Create User
	@Test(priority=1)
	public void testPostUser() {
		
		logger.info("***********************Creating User ****************************");
		
		Response response =userEndpoints.createUser(UserPayload);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(),200);
		
		logger.info("***********************User Created ****************************");
	
	}
	
	//Read User
	@Test(priority=2)
	public void testgetUser() {
		
		logger.info("***********************Reading User ****************************");

		Response response =userEndpoints.readUser(this.UserPayload.getUsername());
		response.then().log().all();

		Assert.assertEquals(response.getStatusCode(),200);
		
		logger.info("***********************User Read ****************************");

	}
	
	// Update User
	@Test(priority=3)
	public void testPutUser() {
		
		logger.info("***********************Updating User ****************************");
		
		// update following details using Payload
		UserPayload.setFirstName(faker.name().firstName());
		UserPayload.setLastName(faker.name().lastName());
		UserPayload.setEmail(faker.internet().emailAddress());
		UserPayload.setPassword(faker.internet().password(5,10));
		UserPayload.setPhone(faker.phoneNumber().cellPhone());
		
		Response response =userEndpoints.updateUser(UserPayload, this.UserPayload.getUsername());
		Assert.assertEquals(response.getStatusCode(),200);
		
		logger.info("***********************User Updated ****************************");

		// Checking data after update
		Response responseAfterUpdate =userEndpoints.readUser(this.UserPayload.getUsername());
		responseAfterUpdate.then().log().all();

		Assert.assertEquals(responseAfterUpdate.getStatusCode(),200);
		
	}
	
	//Delete User
	@Test(priority=4)
	public void testdeleteUser() {
			
		logger.info("***********************Deleting User ****************************");

		Response response =userEndpoints.deleteUser(this.UserPayload.getUsername());
		
		Assert.assertEquals(response.getStatusCode(),200);
		
		logger.info("***********************User Deleted ****************************");
	
		}
	
	
}
