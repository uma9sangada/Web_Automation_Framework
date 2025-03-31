package UmaSangada.data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.poi.ss.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ExcelTest {
	DataFormatter formatter = new DataFormatter();

	@Test(dataProvider="testdata")
	public void testdata(String a,String b,String c) {
		System.out.println(a+b+c);

	}

	@DataProvider(name = "testdata")
	public Object[][] getdata() throws FileNotFoundException {
		FileInputStream file = new FileInputStream("C:\\Users\\umasa\\Downloads\\download.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook();
		int sheets = workbook.getNumberOfSheets();
		
//		for (int i = 0; i < sheets; i++) {
//			if (workbook.getSheetName(i).equalsIgnoreCase("umasheet")) {
//				XSSFSheet sheet = workbook.getSheetAt(i);
		XSSFSheet sheet = workbook.getSheet("s");
	int rowcount = sheet.getPhysicalNumberOfRows();

		XSSFRow row = sheet.getRow(0);
		int columnCount = row.getLastCellNum();
		Object data[][] = new Object[rowcount - 1][columnCount];
		for (int j = 0; j < rowcount; j++) {
			row = sheet.getRow(j + 1);
			for (int k = 0; k < columnCount; k++) {
				XSSFCell cell = row.getCell(k);
				if (cell != null) { // Check for null cell before formatting
					data[j][k] = formatter.formatCellValue(cell);
				} else {
					data[j][k] = ""; // Handle empty cells with empty string (optional)
				}
			}

		}
		return data;
	}
}
