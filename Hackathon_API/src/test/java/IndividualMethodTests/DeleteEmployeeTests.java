package IndividualMethodTests;

import org.testng.annotations.Test;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.testng.Assert.assertEquals;


import endpoints.UserEndPoints;
import io.restassured.response.Response;
import payload.EmployeeIDHolder;


/**
 * The DeleteEmployeeTests class contains test cases for deleting employee records
 * and validating the responses from the API.
 * 
 * This class performs API operations to delete an employee by its ID and verifies
 * the success of the operation.
 */

public class DeleteEmployeeTests {

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
	
	System.out.println("Stored Non-Null Employee ID: " + EmployeeIDHolder.getNonNullEmployeeID());
	
	Integer id_generated_from_post_request = EmployeeIDHolder.getNonNullEmployeeID();
		
		Response response=UserEndPoints.deleteEmployee(id_generated_from_post_request);	
		
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
