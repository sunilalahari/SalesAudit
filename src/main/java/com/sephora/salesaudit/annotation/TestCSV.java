package com.sephora.salesaudit.annotation;
import com.aspose.cells.FileFormatType;
import com.aspose.cells.LoadOptions;
import com.aspose.cells.SaveFormat;
import com.aspose.cells.Workbook;

public class TestCSV {
public static void main(String[] args) throws Exception {
	LoadOptions loadOptions = new LoadOptions(FileFormatType.CSV);
	Workbook workbook = new Workbook("./src/test/resources/testdata_output/EXTENT_REPORT/" + "APDATA_20180816.CSV", loadOptions);
	workbook.save("./src/test/resources/testdata_output/EXTENT_REPORT/" + "CSVtoExcel.xlsx" , SaveFormat.XLSX);
	

	
}
}
