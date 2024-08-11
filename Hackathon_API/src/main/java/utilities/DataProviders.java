package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

import utilities.ExcelUtilities;

public class DataProviders {

	@DataProvider(name="create_employee")
	public static Object[][] getCreateEmployeeData() throws IOException{
	String filepath=System.getProperty("user.dir") + "/src/test/resources/testdata/create_employee.xlsx";
	String sheetName="Sheet3";
	return ExcelUtilities.getDataArray(filepath, sheetName);
}
	
	@DataProvider(name="update_employee")
	public static Object[][] getUpdateEmployeeData() throws IOException{
		String filepath=System.getProperty("user.dir") + "/src/test/resources/testdata/update_employee.xlsx";
		String sheetName="Sheet3";
	return ExcelUtilities.getDataArray(filepath, sheetName);
}
}
