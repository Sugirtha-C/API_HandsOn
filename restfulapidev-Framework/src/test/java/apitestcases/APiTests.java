package apitestcases;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.File;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import endpoints.UserEndPoints;
import io.restassured.response.Response;
import payload.Data;
import payload.Details;
import utilities.DataProviders;
import utilities.ExtentReportManager;


import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;



@Listeners(ExtentReportManager.class)


public class APiTests {
	
	private static String id_from_post_request;
	
	@Test
	
	public void testCase_01(){
		
		Response response=UserEndPoints.getAllObjects();
		long responseTime=response.getTime();
		System.out.println("Response time for get all objects:"+responseTime);
		
		
		assertEquals(response.getStatusCode(),200);
		assertEquals(response.getHeader("Content-Type"),"application/json");
		assertTrue(responseTime < 1000);
		//System.out.println(response.getBody().asPrettyString());
		
		
				
	}
	

	
	@Test(dataProvider="Create_object",dataProviderClass=DataProviders.class)
	
	public void testCase_02(String name, String year, String price, String cpuModel, String hardDiskSize) {	
		
		Data data=new Data();
		Details detail=new Details();
		
		detail.setName(name);
		
		data.setYear(year);
		data.setPrice(price);
		data.setCpuModel(cpuModel);
		data.setHardDiskSize(hardDiskSize);
		
		detail.setData(data);
		
		
		Response response=UserEndPoints.createObject(detail);
		
		id_from_post_request=response.path("id");
		System.out.println("id_from_post request: "+id_from_post_request);
		System.out.println("Response Body from post request: "+ response.getBody().asString());
		System.out.println("Response Code from post request: "+ response.getStatusCode());
		assertEquals(response.getStatusCode(),200);	
		assertTrue(response.getBody().toString().contains("createdAt"));
		
	}
	
	@Test
	public void testcase_03() {
		Response response = UserEndPoints.getSpecificObject(id_from_post_request);
		assertEquals(response.getStatusCode(), 200);
		response.then().assertThat().body(matchesJsonSchemaInClasspath("Data.json"));
	}
	
	@Test(dataProvider="Partial_update_object",dataProviderClass=DataProviders.class)
	
	public void testcase_04(String name) {
		
		Details details=new Details();
		details.setName(name);
		
		Response response=UserEndPoints.partialUpdateObject(id_from_post_request,details);
		
		String partial_update_response_body=response.getBody().asPrettyString();
		
		System.out.println("Response body from patch is:" +partial_update_response_body);
		
		String patch_Response_id=response.path("id").toString();
		String patch_Response_name=response.path("name").toString();
		
		System.out.println("Id from post passed to patch request is: "+id_from_post_request);
		System.out.println("patch_Response_id is: "+patch_Response_id);
		System.out.println("patch_Response_name is: "+patch_Response_name);
		
		System.out.println("Response Code: "+response.getStatusCode());
		
		assertEquals(patch_Response_id,id_from_post_request,"ID received from post is different to ID passed for PATCH request");
		assertTrue(partial_update_response_body.contains("updatedAt"));
		
		
	}
	
	@Test
	
	public void testcase_05() {
		
		Response response=UserEndPoints.getAllObjects();

		System.out.println("Response Code: "+ response.getStatusCode());
		assertEquals(response.getStatusCode(),200);	
		
		
	}


	@Test
	
	public void testcase_06() {
		
		Response response=UserEndPoints.deleteObject(id_from_post_request);
		
		assertEquals(response.getStatusCode(),200);
		
	}
	
}
