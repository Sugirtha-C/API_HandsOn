package sampletestmethods;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;



public class RestAssuredDemo {

	RequestSpecification requestSpecification;
	Response response;
	ValidatableResponse validateResponse;
	
	@Test
	public void verifyStatusCode() {
		
		RestAssured.baseURI="https://reqres.in";
		
		//create a request spec		
		requestSpecification=given();
		
		//calling get method		
		response=requestSpecification.get();
		
		//print the response
		String resString=response.prettyPrint();
		System.out.println(resString);
		
		//to perform validation on response,we need get validateResponse
		
		validateResponse=response.then();
		ResponseBody resBody=response.getBody();
		
		System.out.println(resBody.asString());
		
		//get status code
		validateResponse.statusCode(200);
		
		//check status line as expected
		validateResponse.statusLine("HTTP/1.1 200 OK");
				
	}
	
	@Test
	public void testcase2_BDD() {
		RestAssured.given().baseUri("https://reqres.in/api/users")
		.when().get("/2")
		.then()
		.statusCode(200)
		.statusLine("HTTP/1.1 200 OK");
	
	}
	
	
}

