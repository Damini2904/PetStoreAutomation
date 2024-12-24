package api.test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.petEndpoints;
import api.payload.Category;
import api.payload.Pet;
import api.payload.Tags;
import io.restassured.response.Response;

public class petTests {
	
	Faker faker;
	Pet petPayload;
	Category category;
	ArrayList<String> photoURLs;
	List<Tags> tags;
	
	@BeforeClass
	public void setData() {
		
		// Initialize Faker and objects
		faker = new Faker();
		petPayload = new Pet();
		category = new Category();
		tags = new ArrayList<Tags>();
		
		// Set category details
		category.setId(faker.idNumber().hashCode());
		category.setName(faker.dog().breed());
		
		// Set photo URLs
		ArrayList<String> photoURLs = new ArrayList<String>();
		photoURLs.add(faker.internet().url()); //photoURLs 1
		photoURLs.add(faker.internet().url()); //photoURLs 2
		
		// Create and add tags
		for(int i=0;i<2;i++) 
		{	
		// Generate 2 tags as an example
		Tags tag = new Tags();
		tag.setId(faker.number().randomDigitNotZero());;
		tag.setName(faker.dog().name());
		tags.add(tag);
		}
		
		petPayload.setId(faker.number().randomDigitNotZero()); // Random ID
		petPayload.setCategory(category);
		petPayload.setName(faker.dog().name());  // Random dog name
		petPayload.setPhotoUrls(photoURLs); // Assign photo URLs
		petPayload.setTags(tags); // Assign tags
		petPayload.setStatus(faker.options().option("available","unavailable"));
	}
	
	//Add Pet
	@Test(priority=1)
	public void testpostPet(ITestContext context) {
		
		Response response = petEndpoints.addPet(petPayload);
		response.then().log().all();
		
		Assert.assertEquals(response.statusCode(), 200);
		
		 // Store petId in the ITestContext
		context.setAttribute("PetId", this.petPayload.getId());
		
	}
	
	//Upload Image
	@Test(priority=2)
	void testuploadImage() {
		
		File MyFile = new  File("C:\\Users\\damin\\Downloads\\Dog.jpg");
		String additionalMetadata= "jpg";
		
		Response response = petEndpoints.uploadPetImage(this.petPayload.getId(),additionalMetadata, MyFile);
		response.then().log().all();
		
	Assert.assertEquals(response.statusCode(), 200);
	
	}
	
	
	//Get Pet
	@Test(priority=3)
	public void testgetPet() {
		
		Response response = petEndpoints.readpet(this.petPayload.getId());
		response.then().log().all();
		
		Assert.assertEquals(response.statusCode(), 200);
		Assert.assertEquals(response.jsonPath().getInt("id"), this.petPayload.getId());
	
	}
	
	//Update Pet
	@Test(priority=4)
	public void testupdatePet() {
		
		String PetName=faker.dog().name();  // Update dog name
		String Status = "unavailable"; // Update status
		
		Response response = petEndpoints.updatePet(this.petPayload.getId(),PetName,Status);
		response.then().log().all();
		
		Assert.assertEquals(response.statusCode(), 200);
		
		//Get details after updating pet details
		Response responseAterUpdate = petEndpoints.readpet(this.petPayload.getId());
		responseAterUpdate.then().log().all();
		
		Assert.assertEquals(responseAterUpdate.statusCode(), 200);
		
	}
	
	//Delete Pet
	@Test(priority=5)
	public void testdeletePet() {
		
		Response response = petEndpoints.deletePet(this.petPayload.getId());
		response.then().log().all();
		
		Assert.assertEquals(response.statusCode(), 200);
		
	}
}
