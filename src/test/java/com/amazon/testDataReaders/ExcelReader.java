package com.amazon.testDataReaders;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.amazon.utils.ConfigReader;

public class ExcelReader {
	
	private String filePath;
	
	public ExcelReader(String filePath)
	{
		this.filePath=filePath;
	}
	
	public List<String> getFieldNamesFromExcel(String sheetName) throws IOException
	{
		ConfigReader configReader = new ConfigReader();
		List<String> fieldNames = new ArrayList<String>();
		FileInputStream fileInputStream = new FileInputStream(filePath);
		XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
		XSSFSheet sheet=workbook.getSheet(sheetName);
		int rows = sheet.getPhysicalNumberOfRows();
		for(int row =1;row<rows;row++)
		{
			fieldNames.add(sheet.getRow(row).getCell(0).getStringCellValue());
		}
			fileInputStream.close();
		
		return fieldNames;
		
		
	}

}
