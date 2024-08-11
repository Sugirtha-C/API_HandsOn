package apitestcases;


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


public class CreatePutDeleteTests {
	
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
	    
		System.out.println("ID_from_post request with non null values: "+newEmployeeID);
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



@Test(dataProvider="update_employee",dataProviderClass=DataProviders.class)
	
	public void updateEmployeeByID(String employeeName, String employeeAge, String employeeSalary) {
		
	   
	System.out.println("____________Starting PUT Method request______________");
	// Retrieve the ID from EmployeeIDHolder
		Integer id_generated_from_post_request = EmployeeIDHolder.getNonNullEmployeeID();
		System.out.println("id_generated_from_post_request: "+id_generated_from_post_request);
    
		EmployeeDetails details=new EmployeeDetails();
		details.setName(employeeName);
		details.setAge(employeeAge);	
		details.setSalary(employeeSalary);
		
		Response response=UserEndPoints.updateEmployee(id_generated_from_post_request,details);
		
		String update_response_body=response.getBody().asPrettyString();
		
		System.out.println("status code from put request is:"+response.getStatusCode());
		//System.out.println("Response body from update request is:" + update_response_body);
		
		String put_Response_id=response.path("data.id").toString();
		String put_Response_name=response.path("data.name").toString();
		String put_Response_age = response.path("data.age").toString();
	    String put_Response_salary = response.path("data.salary").toString();
		String put_Response_status_message=response.jsonPath().getString("status");
		
		//Debugging to view the actual results in the console 
		System.out.println("Id from post passed to put request is: "+id_generated_from_post_request);
		System.out.println("put_Response_id is: "+put_Response_id);
		System.out.println("put_Response_name is: "+put_Response_name);
		System.out.println("put_Response_age is: " + put_Response_age);
        System.out.println("put_Response_salary is: " + put_Response_salary);
		System.out.println("Response Code: "+response.getStatusCode());
		System.out.println("Put_Response_status_message: "+put_Response_status_message);
		
		//validations
		
		assertEquals(put_Response_id,id_generated_from_post_request.toString(),"ID received from POST is different to ID passed for PUT request");
		assertEquals(put_Response_status_message,"success","Response received for PUT request is not success");			
		assertEquals(put_Response_name, employeeName, "Updated employee name does not match the input");
        assertEquals(put_Response_age, employeeAge, "Updated employee age does not match the input");
        assertEquals(put_Response_salary, employeeSalary, "Updated employee salary does not match the input");
        assertTrue(response.getTime() < 2000, "Response time is greater than 2000 ms");
        response.then().assertThat().body(matchesJsonSchemaInClasspath("updateResponse.json"));

	}

/**
 * Test case to verify the deletion of an employee record by ID.
 * 
 * This test performs a DELETE request to remove an employee record by its ID.
 * It validates the response status code, checks if the ID in the response matches
 * the ID used in the DELETE request, and ensures that the response status code is 200.
 * 
 * Note: The employee ID is retrieved from the EmployeeIDHolder class and is expected
 * to be a non-null value that was previously set during a create operation.
 */

@Test

public void verifyDeleteEmployeeByID() {

	System.out.println("____________Starting DELETE Method request______________");
	System.out.println("Stored Non-Null Employee ID: " + EmployeeIDHolder.getNonNullEmployeeID());

	Integer id_generated_from_post_request = EmployeeIDHolder.getNonNullEmployeeID();
	System.out.println("id_generated_from_post_request: "+id_generated_from_post_request);
	
	Response response=UserEndPoints.deleteEmployee(id_generated_from_post_request);	
	
	
	System.out.println("status code from delete response: "+response.getStatusCode());
	String response_message = response.path("message");
	String response_value_of_id_from_delete=response.path("data");
	
	System.out.println("Response message:"+ response_message);
	System.out.println("Response_value_of_id_from_delete:"+ response_value_of_id_from_delete);
	
	assertEquals(response.getStatusCode(),200);	
	assertEquals(response_message, "Successfully! Record has been deleted.", "The response message does not match the expected message.");		
	assertEquals(response_value_of_id_from_delete,id_generated_from_post_request,"ID generated from post request is different to the Id received in reponse body");
	response.then().assertThat().body(matchesJsonSchemaInClasspath("deleteResponse.json"));
	
}

	


}
