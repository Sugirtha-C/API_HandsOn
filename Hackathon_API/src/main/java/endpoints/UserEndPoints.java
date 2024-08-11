package endpoints;

import io.restassured.response.Response;
import payload.EmployeeDetails;

import static io.restassured.RestAssured.given;

import io.restassured.http.ContentType;

/*
 * The UserEndPoints class provides methods for interacting with the API.
 * 
 * This class includes methods to perform CRUD operations on employee details, such as 
 * retrieving all employees, getting a specific employee by ID, creating an employee, updating, 
 * and deleting an employee record with the ID.
 */

public class UserEndPoints {
	
	
	// Method to hold the GET request to retrieve all employee records
	
	public 	static Response getAllEmployees() {
		
		return given().baseUri(Routes.BASE_URL).when().get(Routes.GET_ALL_EMPLOYEES_ENDPOINT);
		
	}
	
	/* Method to hold the GET request to retrieve a specific employee record by ID.
	 * Takes an employee ID as a path parameter to identify the employee.
	*/
	
	public static Response getSpecificEmployeeByID(String employee_id) {
		return given()
				.baseUri(Routes.BASE_URL)
				.pathParam("id", employee_id)
				.when()
				.get(Routes.GET_SINGLE_EMPLOYEE_ENDPOINT);
	}

	
	/* Method to hold the POST request to create a new employee record.
	 * Accepts an EmployeeDetails object as the request body.
	*/
	
	public static Response createEmployee(EmployeeDetails details) {
		
		return given()
				.baseUri(Routes.BASE_URL)
				.contentType(ContentType.JSON)
				.body(details)
				.when()
				.post(Routes.CREATE_EMPLOYEE_ENDPOINT)
				.then()
				.log().all()
				.extract()
				.response();
	}
	
	
	/*
	 * Method to hold the PUT request to update an existing employee record by ID.
	 Accepts the employee ID as a path parameter and an EmployeeDetails object as the request body.
	 */
	
	public static Response updateEmployee(Integer id,EmployeeDetails details) {
		
		return given()
				.contentType(ContentType.JSON)
				.baseUri(Routes.BASE_URL)
				.pathParam("id",id)
				.body(details)
				.when()
				.put(Routes.UPDATE_EMPLOYEE_ENDPOINT);
	}
	
   
    /*
     * Method to hold the DELETE request to remove an employee record by ID.
	   Takes an employee ID as a path parameter to identify the employee to be deleted.
     */
	
	public static Response deleteEmployee(Integer id) {
		
		return given()
				.contentType(ContentType.JSON)
				.baseUri(Routes.BASE_URL)
				.pathParam("id",id)
				.when()
				.delete(Routes.DELETE_EMPLOYEE_ENDPOINT);
	}

}
