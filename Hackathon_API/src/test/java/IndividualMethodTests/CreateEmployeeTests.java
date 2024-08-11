package IndividualMethodTests;


import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.Listeners;

import endpoints.UserEndPoints;
import io.restassured.response.Response;

import payload.EmployeeDetails;
import payload.EmployeeIDHolder;
import utilities.DataProviders;
import utilities.ExtentReportManager;


import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

/**
 * The CreateEmployeeTests class contains test cases for creating employee records
 * and validating the responses from the API.
 * 
 * This class uses TestNG listeners to integrate with ExtentReports for reporting
 * and DataProviders for parameterized testing.
 */


@Listeners(ExtentReportManager.class)


public class CreateEmployeeTests {
	
	 /**
     * Test case to create a new employee record.
     * 
     * This test case:
     * 1. Creates an EmployeeDetails object with provided data.
     * 2. Sends a POST request to create the employee.
     * 3. Stores the generated employee ID in EmployeeIDHolder for use in other tests.
     * 4. Logs the ID, response body, and status code for debugging.
     * 
     * Validations:
     * - The response body is validated against the "createdEmployeeResponse.json" JSON schema.
     * - The status code of the response is asserted to be 200.
     * - The response body contains the message "Successfully! Record has been added."
     * - The response time is asserted to be less than 2000 milliseconds.
     * - The employee name in the response body matches the expected employee name.
     * - The employee age in the response body matches the expected employee age.
     * - The employee salary in the response body matches the expected employee salary.
     * - The Content-Type header of the response is asserted to be "application/json".
     */
	
@Test(dataProvider="create_employee",dataProviderClass=DataProviders.class,priority=0)
	
	public void createNewEmployee(String employeeName, String employeeAge, String employeeSalary){	
		
		EmployeeDetails detail=new EmployeeDetails();
		
		detail.setName(employeeName);
		detail.setAge(employeeAge);	
		detail.setSalary(employeeSalary);		
		
		
		Response response=UserEndPoints.createEmployee(detail);
		
		 // Store the ID from the POST request in EmployeeIDHolder
	   
	    Integer newEmployeeID=response.path("data.id");
	    EmployeeIDHolder.setNonNullEmployeeID(newEmployeeID);
	    
		System.out.println("ID_from_post request: "+newEmployeeID);
		// Print the stored ID to verify
	    System.out.println("Stored Non-Null Employee ID: " + EmployeeIDHolder.getNonNullEmployeeID());
		System.out.println("Response Body from post request: "+ response.getBody().asString());
		System.out.println("Response Code from post request: "+ response.getStatusCode());
		
		//Validations 
		response.then().assertThat().body(matchesJsonSchemaInClasspath("jsonSchemas/createdEmployeeResponse.json"));	
		assertEquals(response.getStatusCode(),200,"Response code is not 200.Check the logs for the code received.");	
		assertEquals(response.path("message"), "Successfully! Record has been added.", "The response message does not match the expected message.");
		assertTrue(response.getTime() < 2000, "Response time is greater than 2000 ms");
		assertEquals(response.path("data.name"), employeeName, "Employee name does not match");
		assertEquals(response.path("data.age"), employeeAge, "Employee age does not match");
		assertEquals(response.path("data.salary"), employeeSalary, "Employee salary does not match");
		assertEquals(response.getHeader("Content-Type"), "application/json","Content-Type header is not application/json");		
	}

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
	
@Test(priority=1)
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

    System.out.println("ID_from_post request: " + null_post_id);
    System.out.println("Response Body from post request: " + response.getBody().asString());
    System.out.println("Response Code from post request: " + response.getStatusCode());

    // Validate the response
    response.then().assertThat().body(matchesJsonSchemaInClasspath("jsonSchemas/createdEmployeeResponse.json"));
    assertEquals(response.getStatusCode(), 200);
    assertEquals(response.path("message"), "Successfully! Record has been added.", "The response message does not match the expected message.");  
    assertTrue(response.getTime() < 2000, "Response time is greater than 2000 ms");
    assertEquals(response.getHeader("Content-Type"), "application/json", "Content-Type header is not application/json");
}
	

}
