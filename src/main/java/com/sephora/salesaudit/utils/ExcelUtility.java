package com.sephora.salesaudit.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ExcelUtility {
	/**
	 * This method reads data from an input EXCEL file and stores the same into
	 * a list.
	 * 
	 * @param fileName
	 *            Input filename from where the data is to be read.
	 * @return cellDataList This returns the Excel data in a list.
	 */
	public List readExcelFile(String fileName) {

		List cellDataList = new ArrayList();
		try {
			FileInputStream fileInputStream = new FileInputStream(fileName);
			POIFSFileSystem fsFileSystem = new POIFSFileSystem(fileInputStream);
			HSSFWorkbook workBook = new HSSFWorkbook(fsFileSystem);
			HSSFSheet hssfSheet = workBook.getSheetAt(0);
			Iterator rowIterator = hssfSheet.rowIterator();

			// rowIterator.next();
			// rowIterator.next();
			rowIterator.next();
			while (rowIterator.hasNext()) {
				int previousCell = 0;
				int currentCell = 0;
				HSSFRow hssfRow = (HSSFRow) rowIterator.next();
				Iterator iterator = hssfRow.cellIterator();
				List cellTempList = new ArrayList();
				while (iterator.hasNext()) {
					HSSFCell hssfCell = (HSSFCell) iterator.next();
					// System.out.println(hssfCell.getColumnIndex());
					currentCell = hssfCell.getColumnIndex();
					if (previousCell < currentCell - 1) {
						for (int kk = 1; kk < (currentCell - previousCell); kk++) {
							cellTempList.add("");
						}
					}
					cellTempList.add(hssfCell.toString());
					previousCell = currentCell;
				}
				cellDataList.add(cellTempList);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			// JOptionPane.showMessageDialog(null,
			// "File does not exists at location " + fileName);

		} catch (NullPointerException e) {
			e.printStackTrace();
			// JOptionPane.showMessageDialog(null, "File is empty");

		} catch (Exception e) {
			return null;
		}
		return cellDataList;
	}

	/**
	 * This method reads data from an input EXCEL file and stores the same into
	 * a list.
	 * 
	 * @param fileName
	 *            Input filename from where the data is to be read.
	 * @return cellDataList This returns the Excel data in a list.
	 */
	
	public static ArrayList<String> readExcelBasedOnSheet(String filePath,String sheetName,String ...Seperator) throws Exception
	{
		ArrayList<String> data= new ArrayList<String>();	
		FileInputStream inputStream = new FileInputStream(new File(filePath));
		Workbook workbook = new XSSFWorkbook(inputStream);
		Sheet firstSheet = workbook.getSheet(sheetName);
		Iterator<Row> iterator = firstSheet.iterator();
		String divide=",";
		if(Seperator.length>0)
		{
			divide=";";
		}
		try{	
			while (iterator.hasNext())
			{
				Row nextRow = iterator.next();
				Iterator<Cell> cellIterator = nextRow.cellIterator();
				String out="";
				while (cellIterator.hasNext()) 
				{
					Cell cell = cellIterator.next();
					cell.setCellType(Cell.CELL_TYPE_STRING);
					switch (cell.getCellType()) {
					case Cell.CELL_TYPE_STRING:
						out=out+cell.getStringCellValue()+divide;
						break;
					case Cell.CELL_TYPE_BOOLEAN:
						out=out+cell.getStringCellValue()+divide;
						break;
					case Cell.CELL_TYPE_NUMERIC:
						out=out+((int)cell.getNumericCellValue())+divide;
						break;
					}
				}
				if(out.length()>0)
				{
					out=out.substring(0,(out.length())-1);
					data.add(out);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		inputStream.close();
		return data;


	 }

	/**
	 * This method reads sheet name from an input EXCEL file and stores the same
	 * into a list.
	 * 
	 * @param fileName
	 *            Input filename from where the sheet name is to be read.
	 * @return data This returns the Excel sheet name in a list.
	 */
	public static ArrayList<String> getAllSheetName(String filePath) throws IOException {

		ArrayList<String> sheetNames = new ArrayList<String>();
		FileInputStream inputStream = new FileInputStream(new File(filePath));
		Workbook workbook = new XSSFWorkbook(inputStream);

		for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
			sheetNames.add(workbook.getSheetName(i));
		}
		inputStream.close();
		return sheetNames;
	}

	/**
	 * This method reads sheet name from an input EXCEL file and stores the same
	 * into a list.
	 * 
	 * @param fileName
	 *            Input filename from where the sheet name is to be read.
	 * @return data This returns the Excel sheet name in a list.
	 */
	public Recordset getExcelResultSet(String sheetName, String filePath, String fileName) {
		String testval = null;
		Fillo fillo = new Fillo();
		Connection con = null;
		String result = "";
		Recordset recordSet = null;
		try {
			con = fillo.getConnection(filePath + fileName);
			String query = "Select * from " + sheetName;
			recordSet = con.executeQuery(query);
			recordSet.getCount();
		} catch (FilloException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return recordSet;
	}
	
}