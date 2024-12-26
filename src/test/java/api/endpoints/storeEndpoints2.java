package api.endpoints;

import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;

import org.testng.annotations.Test;

import api.payload.Store;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class storeEndpoints2 {
	
	public static ResourceBundle getURL() {
		
		ResourceBundle Routes = ResourceBundle.getBundle("routes");
		return Routes;
		
	}
	
	//Create Order
	@Test(priority=1)
	public static Response createOrder(Store Payload) {
		
		String post_URL =getURL().getString("store_post_URL");
		
		Response response=given()
			.accept("application/json")
			.contentType(ContentType.JSON)
			.body(Payload)
			.log().all()
			
		.when()
			.post(post_URL);
		
		return response;
	}
	
	//Read Order by OrderId
	@Test(priority=2)
	public static Response readOrder(int OrderId) {
		
		String get_URL =getURL().getString("store_get_URL");
		
		Response response=given()
			.pathParam("OrderId", OrderId)
			
		.when()
			.get(get_URL);
		
		return response;
	}
	
	//Delete order by OrderId
	@Test(priority=3)
	public static Response deleteOrder(int OrderId) {
		
		String delete_URL=getURL().getString("store_delete_URL");
		
		Response response =given()
			.pathParam("OrderId", OrderId)
			
		.when()
			.delete(delete_URL);
		
		return response;
	}
	

}
