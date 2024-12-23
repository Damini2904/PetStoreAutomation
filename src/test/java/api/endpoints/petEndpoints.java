package api.endpoints;

import org.testng.annotations.Test;

import api.payload.Pet;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class petEndpoints {
	
	
	//Add new pet
	@Test(priority=1)
	public static Response addPet(Pet Payload) {
		
		Response response =given()
			.accept("application/json")
			.header("Content-Type","application/json")
			.body(Payload)
			.log().all()
			
		.when()
			.post(Routes.pet_post_URL);
		
		return response;
				
	}
	
	//Read Pet
	@Test(priority=2)
	public static Response readpet(int PetId) {
		
		Response response=given()
			.pathParam("PetId", PetId)
			
		.when()
			.get(Routes.pet_get_URL);
		
		return response;
	}
	
	//Update Pet
	@Test(priority=3)
	public static Response updatePet(int PetId, String Name,String Status) {
		
		Response response=given()
			.accept("application/json")
			.header("Content-Type","application/x-www-form-urlencoded")
			.pathParam("PetId", PetId)
			.param("name",Name)
			.param("status", Status)
			
		.when()
			.post(Routes.pet_update_post_URL);
		
		return response;
	}
	
	//Delete Pet
	@Test(priority=4)
	public static Response deletePet(int PetId)
	{
		
		Response response=given()
			.pathParam("PetId", PetId)
			
		.when()
			.delete(Routes.pet_delete_URL);
		
		return response;
		
		
	}
}
