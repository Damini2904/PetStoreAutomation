package api.endpoints;

import static io.restassured.RestAssured.*;


import java.util.ResourceBundle;

import org.testng.annotations.Test;



import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class userEndpoints2 {
	
	
	public static ResourceBundle getURL() {
		
		// Load properties file 
		ResourceBundle Routes = ResourceBundle.getBundle("routes"); // routes --> Name of properties file
		return Routes;
		
	}
	
	// Create User
	@Test
	public static Response createUser(User Payload) {
		
		String post_URL =getURL().getString("user_post_URL");
		
		Response Response = given()
			.accept("application/json")
			.contentType(ContentType.JSON)
			.body(Payload)
			.log().all()
			
		.when()	
			.post(post_URL);
		
		return Response;
		
	}
	
	// Get User
    @Test
	public static Response readUser(String UserName) {
		
    	String get_URL = getURL().getString("user_get_URL");
    	
		Response Response = given()
			.pathParam("UserName",UserName)
		.when()
			.get(get_URL);
			
		return Response	;
	}
    
	// Update User
    @Test
	public static Response updateUser(User Payload,String UserName) {
		
    	String put_URL = getURL().getString("user_put_URL");
    	
		Response Response = given()
			.accept("application/json")
			.contentType(ContentType.JSON)
			.pathParam("UserName", UserName)
			.body(Payload)
			
		.when()	
			.put(put_URL);
		
		return Response;
		
	}
    
    // Delete User
    @Test
  	public static Response deleteUser(String UserName) {
  		
    	String delete_URL = getURL().getString("user_delete_URL");
    	
  		Response Response = given()
  			.pathParam("UserName",UserName)
  		.when()
  			.get(delete_URL);
  			
  		return Response	;
  	}
    

}
