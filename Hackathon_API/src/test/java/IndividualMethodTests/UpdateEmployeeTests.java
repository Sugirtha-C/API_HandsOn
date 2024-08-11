package IndividualMethodTests;

import org.testng.annotations.Test;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import endpoints.UserEndPoints;
import io.restassured.response.Response;
import payload.EmployeeDetails;
import payload.EmployeeIDHolder;
import utilities.DataProviders;

public class UpdateEmployeeTests {

@Test(dataProvider="update_employee",dataProviderClass=DataProviders.class)
	
	public void updateEmployeeByID(String employeeName, String employeeAge, String employeeSalary) {
		
	   // Retrieve the ID from EmployeeIDHolder
		Integer id_generated_from_post_request = EmployeeIDHolder.getNonNullEmployeeID();
		System.out.println("id_generated_from_post_request: "+id_generated_from_post_request);
    
		EmployeeDetails details=new EmployeeDetails();
		details.setName(employeeName);
		details.setAge(employeeAge);	
		details.setSalary(employeeSalary);
		
		Response response=UserEndPoints.updateEmployee(id_generated_from_post_request,details);
		
		String update_response_body=response.getBody().asPrettyString();
		
		System.out.println("Response body from update request is:" + update_response_body);
		
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
	

}
