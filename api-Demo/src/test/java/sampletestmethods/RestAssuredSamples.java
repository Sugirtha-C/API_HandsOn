package sampletestmethods;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import java.io.File;
import java.util.concurrent.TimeUnit;
import org.json.JSONObject;

public class RestAssuredSamples {

	RequestSpecification request;
	Response response;
	ValidatableResponse validateResponse;
	
	@Test
	public void Test() {
		
		//baseuri
		
		RestAssured.baseURI="https://reqres.in";
		
		
		//request Specification -> used to specify the type of request
		request=given();
		response=request.get();
		System.out.println(response.getStatusCode());
		validateResponse=response.then();
		validateResponse.assertThat().statusCode(200);
		validateResponse.log().all();
		
	}
	
	@Test
	public void test2() {
		
		RestAssured.baseURI="https://reqres.in";
		Response response=RestAssured.
				given().when().get("/api/users?page=2");
		
		validateResponse=response.then();
		validateResponse.assertThat().statusCode(200);
		validateResponse.body("data[0].id",equalTo(7));
		validateResponse.body("total",equalTo(12));
		
						
	}
	
	@Test
	public void test3() {
		
		given()
		.baseUri("https://reqres.in")
		.when()
		.get("/api/users?page=2")
		.then()
		.assertThat()
		.statusCode(200)
		.body("data[0].id",equalTo(7));

	}
	@Test	
	public void test4_postRequest()
	{
		RestAssured.baseURI="https://reqres.in";
		 String body="{\r\n" + 
					"    \"name\": \"morpheus\",\r\n" + 
					"    \"job\": \"leader\"\r\n" + 
					"}";
		
		given()
		//baseUri("https://reqres.in")
		.body(body)// or declare it as a string: string body=" "
		.when()
		.post("/api/users")
		.then()
		.assertThat()
		.statusCode(201);
	}

	@Test
	public void test5() {
	RestAssured.baseURI="https://reqres.in";
	JSONObject requestParams=new JSONObject();
	requestParams.put("name","sugi");
	requestParams.put("job","QA Engg");
	
	Response response=RestAssured.given().headers("Content-Type","application/json")
			.body(requestParams.toString()).when().post("/api/users");
	
	validateResponse=response.then();
	validateResponse.assertThat().statusCode(201);
	validateResponse.log().all();

	}
	
	@Test
	public void test6() {
		
		RestAssured.baseURI="https://reqres.in";
		File jsonData=new File("src/test/resources/testdata/post.json");	
		
		//System.out.println("Request JSON data:"+jsonData.toString());
		
		Response response=RestAssured.given().headers("Content-Type","application/json")
				.body(jsonData).when().post("/api/users");
		
		// Printing the response status code
	    System.out.println("Response Status Code: " + response.getStatusCode());
	    
	    // Printing the response body
	    System.out.println("Response Body: " + response.getBody().asString());
	    
	    // Printing response headers
	    System.out.println("Response Headers: " + response.getHeaders());
	    
		validateResponse=response.then();
		validateResponse.assertThat().statusCode(201);
		validateResponse.log().all();
		validateResponse.body("name", equalTo("Sugirtha"));
		validateResponse.body("job", equalTo("QA"));
		validateResponse.header("Content-Type", "application/json; charset=utf-8");
		validateResponse.header("Server", "cloudflare");
		validateResponse.time(lessThan(5L),TimeUnit.SECONDS);
		
			
	}
	
}
	
