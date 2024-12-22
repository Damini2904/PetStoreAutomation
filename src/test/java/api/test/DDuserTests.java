package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.userEndpoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DDuserTests {
	
	
	
	@Test(priority=1,dataProvider = "Data",dataProviderClass=DataProviders.class)
    public void testPostUser(String UserId,String UserName,String FirstName,String LastName,String Email, String Password, String Phone) {
	
		User userPayload = new User();
		
        userPayload.setId(Integer.parseInt(UserId)); 
        userPayload.setUsername(UserName);
        userPayload.setFirstName(FirstName);
        userPayload.setLastName(LastName);
        userPayload.setEmail(Email);
        userPayload.setPassword(Password);
        userPayload.setPhone(Phone);
		
		Response response =userEndpoints.createUser(userPayload);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(),200);
		
	}	
	
	@Test(priority=2,dataProvider="UserNames",dataProviderClass=DataProviders.class)
	public void testDeleteUser(String UserName) {
		
		Response response = userEndpoints.deleteUser(UserName);
		
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	

}
