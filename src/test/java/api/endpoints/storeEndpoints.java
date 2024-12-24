package api.endpoints;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import api.payload.Store;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class storeEndpoints {
	
	//Create Order
	@Test(priority=1)
	public static Response createOrder(Store Payload) {
		
		Response response=given()
			.accept("application/json")
			.contentType(ContentType.JSON)
			.body(Payload)
			.log().all()
			
		.when()
			.post(Routes.store_post_URL);
		
		return response;
	}
	
	//Read Order by OrderId
	@Test(priority=2)
	public static Response readOrder(int OrderId) {
		
		Response response=given()
			.pathParam("OrderId", OrderId)
			
		.when()
			.get(Routes.store_get_URL);
		
		return response;
	}
	
	//Delete order by OrderId
	@Test(priority=3)
	public static Response deleteOrder(int OrderId) {
		
		Response response =given()
			.pathParam("OrderId", OrderId)
			
		.when()
			.delete(Routes.store_delete_URL);
		
		return response;
	}
	

}
