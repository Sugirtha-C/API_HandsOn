package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

import utilities.ExcelUtilities;

public class DataProviders {

	@DataProvider(name="Create_object")
	public static Object[][] getCreateAccountData() throws IOException{
	String filepath=System.getProperty("user.dir") + "/src/test/resources/testdata/PostNewObject.xlsx";
	String sheetName="Sheet2";
	return ExcelUtilities.getDataArray(filepath, sheetName);
}
	
	@DataProvider(name="Partial_update_object")
	public static Object[][] getPartialUpdateAccountData() throws IOException{
		String filepath=System.getProperty("user.dir") + "/src/test/resources/testdata/PartialUpdate.xlsx";
		String sheetName="Sheet3";
	return ExcelUtilities.getDataArray(filepath, sheetName);
}
}
