package payload;

import java.io.File;

import org.json.JSONObject;

/*
 * This class handles variables for creating a 
 * booking through api.
 */
public class Booking {

	private String firstName;
	private String lastName;
	private String totalPrice;
	private String depositPaid;
	private BookingDates bookingDate;
	private String additionalNeeds;
	
	
	//getter and setter methods 
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String isDepositPaid() {
		return depositPaid;
	}
	public void setDepositPaid(String depositPaid) {
		this.depositPaid = depositPaid;
	}
	public BookingDates getBookingDate() {
		return bookingDate;
	}
	public void setBookingDate(BookingDates bookingDate) {
		this.bookingDate = bookingDate;
	}
	public String getAdditionalNeeds() {
		return additionalNeeds;
	}
	public void setAdditionalNeeds(String additionalNeeds) {
		this.additionalNeeds = additionalNeeds;
	}
	
	/*converting the booking object to json to read into the body of request
	 * for createaccount request
	 */

	/*public String createOrUpdateAccountData_toJson() {
		JSONObject json=new JSONObject();
		json.put("firstName", this.firstName);
		json.put("lastName", this.lastName);
		json.put("totalprice", this.totalPrice);
	    json.put("depositpaid", this.depositPaid);
	    
	    JSONObject bookingDatesJSON=new JSONObject();
	    bookingDatesJSON.put("checkin", this.bookingDate.getCheckin());
	    bookingDatesJSON.put("checkout",this.bookingDate.getCheckout());    
	    
	    json.put("bookingDates",bookingDatesJSON);
	    json.put("additionalNeeds", this.additionalNeeds);
		return json.toString();
	    	    
	}
	
	/*
	 * converting the bookign object to json for partial update
	 */
	/*public String partialUpdate_toJson() {
		
		JSONObject json=new JSONObject();
		
	if(this.firstName != null)
		json.put("firstName", this.firstName);
	if(this.lastName != null)
		json.put("lastName", this.lastName);
	if(this.totalPrice != null)
		json.put("totalprice", this.totalPrice);
	if(this.depositPaid!=null)
		json.put("depositpaid", this.depositPaid);
	
	if(this.bookingDate != null) {
		
		 	JSONObject bookingDatesJSON=new JSONObject();
		 	if(this.bookingDate.getCheckin() != null)
		 		bookingDatesJSON.put("checkin", this.bookingDate.getCheckin());
		 	if(this.bookingDate.getCheckout() != null)
		 		bookingDatesJSON.put("checkout",this.bookingDate.getCheckout());
		 	json.put("bookingDates",bookingDatesJSON);
	}
	
	 	
	if(this.additionalNeeds != null)
		json.put("additionalNeeds", this.additionalNeeds);	
		
		
	return json.toString();
	}*/
	
}
