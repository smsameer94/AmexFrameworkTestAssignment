package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.DateUtil;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExcelReader {
	private String filePath;

	public ExcelReader(String filePath) {
		this.filePath = filePath;
	}

	public Object[][] getTestData(String sheetName) throws IOException {
		FileInputStream fis = new FileInputStream(filePath);
		Workbook workbook = WorkbookFactory.create(fis);
		Sheet sheet = workbook.getSheet(sheetName);
		int rowCount = sheet.getPhysicalNumberOfRows();
		int colCount = sheet.getRow(0).getPhysicalNumberOfCells();

		Object[][] data = new Object[rowCount - 1][colCount];

		for (int i = 1; i < rowCount; i++) {
			Row row = sheet.getRow(i);
			for (int j = 0; j < colCount; j++) {
				Cell cell = row.getCell(j);

				if (cell.getCellType() == CellType.NUMERIC && DateUtil.isCellDateFormatted(cell)) {
					SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
					Date dateValue = cell.getDateCellValue();
					data[i - 1][j] = dateFormat.format(dateValue);
				}

				else if (cell.getCellType() == CellType.NUMERIC) {
					data[i - 1][j] = String.valueOf((long) cell.getNumericCellValue());
				}

				else {
					data[i - 1][j] = cell.toString();
				}
			}
		}
		workbook.close();
		fis.close();
		return data;
	}
}
