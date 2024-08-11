package endpoints;

/*
 * The Routes class holds the API endpoint paths used in the application.
 * 
 */

public class Routes {

	 // Base URL for the API
	public static final String BASE_URL="https://api.restful-api.dev/";

	// Endpoint to retrieve a list of all objects
	public static final String GET_ALL_OBJECTS_ENDPOINT = "/objects";
	
	 // Endpoint to retrieve a single object by its ID
	public static final String GET_SINGLE_OBJECT_ENDPOINT = "/objects/{id}";
	
	 // Endpoint to create a new object
	public static final String CREATE_OBJECT_ENDPOINT = "/objects";
	
	// Endpoint to update an existing object by its ID
	public static final String UPDATE_OBJECT_ENDPOINT = "/objects/{id}";
	
	//Endpoint for partial update of an existing object by its ID
	public static final String PARTIALUPDATE_OBJECT_ENDPOINT = "/objects/{id}";
	
	 // Endpoint to delete an object by its ID
	public static final String DELETE_OBJECT_ENDPOINT = "/objects/{id}";


}
