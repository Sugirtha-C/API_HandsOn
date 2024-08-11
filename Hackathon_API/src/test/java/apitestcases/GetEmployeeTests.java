package apitestcases;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


import endpoints.UserEndPoints;
import io.restassured.response.Response;
import utilities.ExtentReportManager;


/**
 * The GetEmployeeTests class contains test cases for retrieving employee data
 * from the API. It includes tests for fetching all employees and a specific
 * employee by ID.
 */
@Listeners(ExtentReportManager.class)

public class GetEmployeeTests {
	
	   /**
     * Test case to fetch all employee data from the API.
     * 
     * This test performs a GET request to retrieve all employee records, validates
     * the response status code, response headers, response time, and checks if
     * the response body matches the expected JSON schema.
     */

@Test(priority=0)
	
	public void getAllEmployeeData(){
		
		Response response=UserEndPoints.getAllEmployees();
		
		// Optionally print the response body for debugging if required
		//System.out.println(response.getBody().asPrettyString());
		
		long responseTime=response.getTime();
		System.out.println("Response time for get all objects:"+responseTime);		
		System.out.println("Response code for Get all employee data is: "+response.getStatusCode());
		assertEquals(response.getStatusCode(),200);
		assertEquals(response.getHeader("Content-Type"),"application/json");
		assertTrue(responseTime < 2000);
		response.then().assertThat().body(matchesJsonSchemaInClasspath("jsonSchemas/GetAllEmployeeData.json"));		
					
	}
	
	/**
	 * Test case to fetch a specific employee by ID from the API.
	 * 
	 * This test performs a GET request to retrieve a specific employee record
	 * by its ID. The ID is hardcoded as "1" because the website does not allow
	 * dynamic passing of the ID; it restricts it to a fixed value. The test then
	 * validates the response status code and checks if the response body matches
	 * the expected JSON schema.
	 * 
	 * Validations:
	 * - The response status code is asserted to be 200.
	 * - The response body is validated against the "GetEmployeeData.json" JSON schema.
	 */

@Test(priority=1)
public void fetchSpecificEmployeeByID() {
	
	Response response = UserEndPoints.getSpecificEmployeeByID("1");
	
	int response_id = response.path("data.id");
    String response_employee_name = response.path("data.employee_name");
    String response_profile_image = response.path("data.profile_image");
	
    System.out.println("Response code for GET one employee ID is: "+response.getStatusCode());
	assertEquals(response.getStatusCode(), 200);
	response.then().assertThat().body(matchesJsonSchemaInClasspath("jsonSchemas/GetEmployeeData.json"));
	assertEquals(response_id, 1, "The ID in the response does not match the expected value.");
	assertEquals(response_employee_name, "Tiger Nixon", "The employee name does not match the expected value.");	 
    assertEquals(response_profile_image, "", "The profile image field is not as expected.");
    
}
}
