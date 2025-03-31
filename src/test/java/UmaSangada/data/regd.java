package UmaSangada.data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class regd {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		String fileName = "C:\\Users\\umasa\\Downloads\\download.xlsx";
		
		FileInputStream file = new FileInputStream(fileName);
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.getSheet("Sheet1");
		
		System.out.println(sheet.getSheetName());
	}

}
