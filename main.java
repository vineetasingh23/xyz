import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import dev.mj.excelupload.util.UploadUtil;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UploadService {

    private final UploadUtil uploadUtil;

    public UploadService(UploadUtil uploadUtil) {
        this.uploadUtil = uploadUtil;
    }

    public List<Map<String, String>> upload(MultipartFile file) throws Exception {
        Path tempDir = Files.createTempDirectory("");
        File tempFile = tempDir.resolve(file.getOriginalFilename()).toFile();
        file.transferTo(tempFile);

        Workbook workbook = WorkbookFactory.create(tempFile);
        Sheet sheet = workbook.getSheetAt(0);

        Supplier<Stream<Row>> rowStreamSupplier = uploadUtil.getRowStreamSupplier(sheet);
        Row headerRow = rowStreamSupplier.get().findFirst().get();
        List<String> headerCells = uploadUtil.getStream(headerRow)
                .map(Cell::getStringCellValue) // Read header cells as strings
                .collect(Collectors.toList());

        List<Map<String, String>> result = new ArrayList<>();
        int colCount = headerCells.size();

        rowStreamSupplier.get().skip(1).forEach(row -> {
            List<String> cellList = uploadUtil.getStream(row)
                    .map(cell -> {
                        if (cell.getCellType() == CellType.NUMERIC) {
                            // Handle numeric value
                            return String.valueOf(cell.getNumericCellValue());
                        } else {
                            // Handle other data types as strings
                            return cell.getStringCellValue();
                        }
                    })
                    .collect(Collectors.toList());

            if (cellList.size() == colCount) {
                Map<String, String> rowMap = uploadUtil.cellIteratorSupplier(colCount)
                        .get()
                        .collect(Collectors.toMap(headerCells::get, cellList::get));
                result.add(rowMap);
            }
        });

        return result;
    }
}
