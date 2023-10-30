import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExcelDataService {

    public List<MyElement> readExcelData(MultipartFile file) throws IOException {
        List<MyElement> elements = new ArrayList<>();

        Workbook workbook = WorkbookFactory.create(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

        int rows = sheet.getPhysicalNumberOfRows();

        for (int i = 0; i < rows; i++) {
            Row row = sheet.getRow(i);
            if (row != null) {
                MyElement myElement = new MyElement();

                myElement.setGuid(row.getCell(0).getStringCellValue());
                myElement.setCsvKey(row.getCell(1).getStringCellValue());
                myElement.setCsvParentKey(row.getCell(2).getStringCellValue());
                // Populate other properties...

                elements.add(myElement);
            }
        }

        workbook.close();
        return elements;
    }
}
