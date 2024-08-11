package endpoints;

import io.restassured.response.Response;
import payload.Booking;

import static io.restassured.RestAssured.given;

import io.restassured.http.ContentType;


public class UserEndPoints {
	//get
	
	public 	static Response getBooking() {
		
		return given().baseUri(Routes.BASE_URL).when().get(Routes.GETBOOKING_ENDPOINT);
		
	}
	
	//post
	
	public static Response createBooking(Booking booking) {
		
		return given()
				.baseUri(Routes.BASE_URL)
				.contentType(ContentType.JSON)
				.body(booking)
				.when()
				.post(Routes.CREATEBOOKING_ENDPOINT)
				.then()
				.log().all()
				.extract()
				.response();
	}
	
	//put
	
	public static Response updateBooking(Booking booking) {
		
		return given().baseUri(Routes.BASE_URL).when().put(Routes.UPDATEBOOKING_ENDPOINT);
	}
	
	//patch
	
	public static Response partialUpdateBooking(String id,Booking booking) {
		
		//String jsonData=booking.partialUpdate_toJson();
		
		return given()
				.contentType(ContentType.JSON)
				.baseUri(Routes.BASE_URL)
				.pathParam("id",id)
				.body(booking)
				.when()
				.patch(Routes.PARTIALUPDATEBOOKING_ENDPOINT);
	}
	
	//delete
	
	public static Response deleteBooking(Booking booking) {
		
		return given().baseUri(Routes.BASE_URL).when().delete(Routes.DELETEBOOKING_ENDPOINT);
	}

}
