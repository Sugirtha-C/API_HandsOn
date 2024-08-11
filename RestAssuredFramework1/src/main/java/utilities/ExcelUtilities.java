package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/*
 * The ExcelUtils class provides methods for interacting with Excel files:
 * 
 * - setExcel(String path, String sheetName): Opens the specified Excel file and sheet for reading.
 * - getRowCount(): Returns the number of rows in the current sheet.
 * - getCellCount(int rowNum): Returns the number of cells in a specific row.
 * - getCellData(int rowNum, int colNum): Retrieves the data from a specific cell in the sheet.
 * - getDataArray(String filepath, String sheetName): Loads data from the specified Excel sheet into a 2D array.
 * - recordTestResult(String name, String status): Records the result of a test case in a HashMap.
 * - writeTestResultsToExcel(String filepath): Writes recorded test results to a new Excel file.
 * 
 * This class utilizes Apache POI for Excel file operations and supports both reading from and writing to Excel sheets.
 */

public class ExcelUtilities {

	private static Workbook workbook;
	private static Sheet sheet;
	private  Map<String, String> testresults = new HashMap<>();
	
	public static void setExcel(String path,String sheetName) throws IOException{
		
		FileInputStream file=new FileInputStream(path);
		workbook=new XSSFWorkbook(file);
		
		sheet=workbook.getSheet(sheetName);
		
	}
	
	public static int getRowCount() {
		return sheet.getPhysicalNumberOfRows();
	}
	
	public static int getCellCount(int rowNum) {
		return sheet.getRow(rowNum).getPhysicalNumberOfCells();
	}
	
	public static String getCellData(int rowNum, int colNum) {
		Row row=sheet.getRow(rowNum);
		Cell cell=row.getCell(colNum);
		
		DataFormatter formatter=new DataFormatter();
		return formatter.formatCellValue(cell);
	}
	
	public static Object[][] getDataArray(String filepath,String sheetName) throws IOException{
		
		setExcel(filepath,sheetName);
		 int rowCount=getRowCount();
		 int colCount=getCellCount(0);
		 
		 Object[][] arr= new Object[rowCount-1] [colCount];
		 
		 for(int i=1; i<rowCount; i++) {
			 for(int j=0; j< colCount; j++) {
				 arr[i-1][j]=getCellData(i,j);
			 }
		 }
		 return arr;
	}
}
