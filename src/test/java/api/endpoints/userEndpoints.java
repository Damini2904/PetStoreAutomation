package api.endpoints;

import static io.restassured.RestAssured.*;

import org.testng.annotations.Test;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class userEndpoints {
	
	
	// Create User
	@Test
	public static Response createUser(User Payload) {
		
		Response Response = given()
			.accept("application/json")
			.contentType(ContentType.JSON)
			.body(Payload)
			.log().all()
			
		.when()	
			.post(Routes.user_post_URL);
		
		return Response;
		
	}
	
	// Get User
    @Test
	public static Response readUser(String UserName) {
		
		Response Response = given()
			.pathParam("UserName",UserName)
		.when()
			.get(Routes.user_get_URL);
			
		return Response	;
	}
    
	// Update User
    @Test
	public static Response updateUser(User Payload,String UserName) {
		
		Response Response = given()
			.accept("application/json")
			.contentType(ContentType.JSON)
			.pathParam("UserName", UserName)
			.body(Payload)
			
		.when()	
			.put(Routes.user_put_URL);
		
		return Response;
		
	}
    
    // Delete User
    @Test
  	public static Response deleteUser(String UserName) {
  		
  		Response Response = given()
  			.pathParam("UserName",UserName)
  		.when()
  			.get(Routes.user_delete_URL);
  			
  		return Response	;
  	}
    

}
