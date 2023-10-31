@Service
public class ExcelDataService {

    public MyElement createMyElement(Row row) {
        MyElement myElement = new MyElement();

        myElement.setGuid(getStringValue(row.getCell(0)));
        myElement.setCsvKey(getStringValue(row.getCell(1)));
        myElement.setCsvParentKey(getStringValue(row.getCell(2)));

        // Skipping rows where CSV_PARENT_KEY is empty
        if (myElement.getCsvParentKey().length() < 2) {
            return null; // Skipping to next iteration
        }

        myElement.setDelete(getStringValue(row.getCell(3)));
        myElement.setAlias(getStringValue(row.getCell(4)).replace("*", ""));
        myElement.setName(getStringValue(row.getCell(5)).replace("''", "**"));
        myElement.setNotes(getStringValue(row.getCell(6)).replaceAll("''''''", "\""));

        // Assuming the rest of the attributes are strings
        myElement.setElementType(getStringValue(row.getCell(7)));
        myElement.setStereotype(getStringValue(row.getCell(8)));
        myElement.setAuthor("Administrator");
        myElement.setStatus(getStringValue(row.getCell(10)));
        myElement.setVersion(getStringValue(row.getCell(11)));
        myElement.setPhase(getStringValue(row.getCell(12)));
        myElement.setUrl(getStringValue(row.getCell(13)));

        return myElement;
    }

    // Helper method to safely retrieve cell values as strings
    private String getStringValue(Cell cell) {
        if (cell != null) {
            cell.setCellType(CellType.STRING);
            return cell.getStringCellValue();
        }
        return ""; // or null as needed
    }
}


  try {
                if ("Yes".equals(element.getDelete()) && element.getGuid() != null && !element.getGuid().isEmpty()) {
                    deleteElement(element);
                }

                setParentElement(element);

                addOrUpdateElement(element);
            } catch (Exception e) {
                // Handle exceptions
            }
