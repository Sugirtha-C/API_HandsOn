package apitestcases;

import static org.testng.Assert.assertEquals;

import java.io.File;

import org.testng.annotations.Test;

import endpoints.UserEndPoints;
import io.restassured.response.Response;
import payload.Booking;
import payload.BookingDates;
import utilities.DataProviders;

public class APiTests {
	
	@Test
	
	public void testCase_01(){
		
		Response response=UserEndPoints.getBooking();
		
		assertEquals(response.getStatusCode(),200);
		assertEquals(response.getHeader("Content-Type"),"application/json; charset=utf-8");
				
	}
	
	/*@Test
	
	public void testCase_02() {
		

		//File jsonData=new File("src/test/resources/testdata/createBooking.json");
		
		String jsonData ="  {\n"
				+ "    \"firstname\" : \"Jim\",\n"
				+ "    \"lastname\" : \"Brown\",\n"
				+ "    \"totalprice\" : 111,\n"
				+ "    \"depositpaid\" : true,\n"
				+ "    \"bookingdates\" : {\n"
				+ "        \"checkin\" : \"2018-01-01\",\n"
				+ "        \"checkout\" : \"2019-01-01\"\n"
				+ "    },\n"
				+ "    \"additionalneeds\" : \"Breakfast\"\n"
				+ "  }";
		
		Response response=UserEndPoints.createBooking(jsonData);
		
		System.out.println("Response Body: "+ response.getBody().asString());
		System.out.println("Response Code: "+ response.getStatusCode());
		assertEquals(response.getStatusCode(),200);
		//assertEquals(response.getHeader("Content-Type"), "application/json; charset=utf-8");		

}*/
	
	@Test(dataProvider="Create_booking",dataProviderClass=DataProviders.class)
	
	public void testCase_02(String firstName, String lastName, String totalPrice, String depositPaid, String checkin, String checkout, String additionalNeeds) {	
		
		Booking booking=new Booking();
		BookingDates bookingDates=new BookingDates();
		
		booking.setFirstName(firstName);
		booking.setLastName(lastName);
		booking.setTotalPrice(totalPrice);
		booking.setDepositPaid(depositPaid);
		
		bookingDates.setCheckin(checkin);
		bookingDates.setCheckout(checkout);
		booking.setBookingDate(bookingDates);
		
		booking.setAdditionalNeeds(additionalNeeds);		
		
		
		Response response=UserEndPoints.createBooking(booking);
		
		System.out.println("Response Body: "+ response.getBody().asString());
		System.out.println("Response Code: "+ response.getStatusCode());
		assertEquals(response.getStatusCode(),200);	
		
	}
	
	@Test(dataProvider="Partial_booking",dataProviderClass=DataProviders.class)
	
	public void testcase_03(String id, String firstName, String lastName) {
		
		Booking booking=new Booking();
				
		booking.setFirstName(firstName);
		booking.setLastName(lastName);
		
		Response response=UserEndPoints.partialUpdateBooking(id,booking);
		
		System.out.println("Response Code:"+response.getStatusCode());
	}
	
}
