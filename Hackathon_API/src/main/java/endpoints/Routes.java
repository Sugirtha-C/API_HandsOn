package endpoints;

/*
 * The Routes class holds the API endpoint paths used in the application.
 * 
 */

public class Routes {

	// Base URL for the API
	public static final String BASE_URL="https://dummy.restapiexample.com/api/v1";

	// Endpoint to retrieve a list of all employees
	public static final String GET_ALL_EMPLOYEES_ENDPOINT = "/employees";
	
	 // Endpoint to retrieve a single employee details by its ID
	public static final String GET_SINGLE_EMPLOYEE_ENDPOINT = "/employee/{id}";
	
	 // Endpoint to create a new employee
	public static final String CREATE_EMPLOYEE_ENDPOINT = "/create";
	
	// Endpoint to update an existing employee by its ID
	public static final String UPDATE_EMPLOYEE_ENDPOINT = "/update/{id}";
	
	// Endpoint to delete an employee by its ID
	public static final String DELETE_EMPLOYEE_ENDPOINT = "/delete/{id}";


}
