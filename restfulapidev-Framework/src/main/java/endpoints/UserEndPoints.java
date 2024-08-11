package endpoints;

import io.restassured.response.Response;
import payload.Details;

import static io.restassured.RestAssured.given;

import io.restassured.http.ContentType;

/*
 * The UserEndPoints class provides methods for interacting with the API.
 * 
 * This class includes methods to perform CRUD operations on objects, such as 
 * retrieving all objects, getting a specific object by ID, creating, updating, 
 * partially updating, and deleting objects.
 */
public class UserEndPoints {
	
	
	  // Get all objects
	public 	static Response getAllObjects() {
		
		return given().baseUri(Routes.BASE_URL).when().get(Routes.GET_ALL_OBJECTS_ENDPOINT);
		
	}
	
	 // Get a specific object by ID
	
	public static Response getSpecificObject(String id) {
		return given()
				.baseUri(Routes.BASE_URL)
				.pathParam("id", id)
				.when()
				.get(Routes.GET_SINGLE_OBJECT_ENDPOINT);
	}

	
	 // Create a new object
	
	public static Response createObject(Details details) {
		
		return given()
				.baseUri(Routes.BASE_URL)
				.contentType(ContentType.JSON)
				.body(details)
				.when()
				.post(Routes.CREATE_OBJECT_ENDPOINT)
				.then()
				.log().all()
				.extract()
				.response();
	}
	
	// Update an existing object by ID
	
	public static Response updateObject(String id,Details details) {
		
		return given()
				.contentType(ContentType.JSON)
				.baseUri(Routes.BASE_URL)
				.pathParam("id",id)
				.body(details)
				.when()
				.put(Routes.UPDATE_OBJECT_ENDPOINT);
	}
	
    // Partially update an existing object by ID

	
	public static Response partialUpdateObject(String id, Details details) {
		
				
		return given()
				.contentType(ContentType.JSON)
				.baseUri(Routes.BASE_URL)
				.pathParam("id",id)
				.body(details)
				.when()
				.patch(Routes.PARTIALUPDATE_OBJECT_ENDPOINT);
	}
	
    // Delete an object by ID
	
	public static Response deleteObject(String id) {
		
		return given()
				.contentType(ContentType.JSON)
				.baseUri(Routes.BASE_URL)
				.pathParam("id",id)
				.when()
				.delete(Routes.DELETE_OBJECT_ENDPOINT);
	}

}
