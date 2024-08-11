package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

import utilities.ExcelUtilities;

public class DataProviders {

	@DataProvider(name="Create_booking")
	public static Object[][] getCreateAccountData() throws IOException{
	String filepath=System.getProperty("user.dir") + "/src/test/resources/testdata/CreateBooking.xlsx";
	String sheetName="Sheet1";
	return ExcelUtilities.getDataArray(filepath, sheetName);
}
	
	@DataProvider(name="Partial_booking")
	public static Object[][] getPartialUpdateAccountData() throws IOException{
	String filepath=System.getProperty("user.dir") + "/src/test/resources/testdata/PartialUpdate.xlsx";
	String sheetName="Sheet2";
	return ExcelUtilities.getDataArray(filepath, sheetName);
}
}
