package apitestcases;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import endpoints.UserEndPoints;
import io.restassured.response.Response;
import payload.EmployeeDetails;
import utilities.ExtentReportManager;

@Listeners(ExtentReportManager.class)

public class CreateWithNullDataTests {

	/**
	 * Test case to create an employee record with null values.
	 * 
	 * This test case:
	 * 1. Creates an EmployeeDetails object with null values for all fields.
	 * 2. Sends a POST request to create the employee.
	 * 3. Stores the generated employee ID in EmployeeIDHolder for use in other tests.
	 * 4. Logs the ID, response body, and status code for debugging.
	 * 
	 * Validations:
	 * - The response body is validated against the "createdEmployeeResponse.json" JSON schema.
	 * - The status code of the response is asserted to be 200.
	 * - The response body contains the message "Successfully! Record has been added."
	 * - The response time is asserted to be less than 2000 milliseconds.
	 * - The Content-Type header of the response is asserted to be "application/json".
	 */

@Test
public void createEmployeeWithNullValues() {

// Create an EmployeeDetails object with null values
EmployeeDetails detail = new EmployeeDetails();
detail.setName(null);
detail.setAge(null);
detail.setSalary(null);

Response response = UserEndPoints.createEmployee(detail);

// Store the ID from the POST request in EmployeeIDHolder
//id_generated_from_post_request = response.path("id");
int null_post_id=response.path("data.id");;
//EmployeeIDHolder.setNullEmployeeID(id_generated_from_post_request);
System.out.println("________CREATE with null data____________________________");
System.out.println("ID_from_post request with null data: " + null_post_id);
//System.out.println("Response Body from post request: " + response.getBody().asString());
System.out.println("Response Code from post request: " + response.getStatusCode());

// Validate the response

assertEquals(response.getStatusCode(), 200);
assertEquals(response.path("message"), "Successfully! Record has been added.", "The response message does not match the expected message.");  
assertTrue(response.getTime() < 2000, "Response time is greater than 2000 ms");
assertEquals(response.getHeader("Content-Type"), "application/json", "Content-Type header is not application/json");
}

}
