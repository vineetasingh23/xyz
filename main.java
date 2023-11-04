@Service
public class EAService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void setParentElement(List<MyElement> elements, String defaultParentPackage) {
        for (MyElement element : elements) {
            String parentKey = element.getCsvParentKey();

            if (parentKey != null && !parentKey.isEmpty()) {
                if (parentKey.startsWith("{")) {
                    try {
                        MyElement parentElement = getElementByGUID(parentKey);
                        
                        if (parentElement != null && "Package".equals(parentElement.getType())) {
                            setParentPackage(parentElement);
                            element.setParentElementId(parentElement.getElementID());
                        }
                    } catch (Exception e) {
                        // Handle errors when getting the element by GUID
                    }
                } else {
                    MyElement csvParent = getCsvParentByKey(parentKey);
                    if (csvParent == null) {
                        setDefaultParentPackage(defaultParentPackage);
                    } else {
                        if ("Package".equals(csvParent.getType())) {
                            element.setParentElementId(csvParent.getElementID());
                            setParentPackage(csvParent);
                        }
                    }
                }
            }
        }
    }

    private MyElement getElementByGUID(String guid) {
        // Your logic to fetch an element from the database by GUID
        return null;
    }

    private MyElement getCsvParentByKey(String key) {
        // Your logic to fetch the CSV parent element by key
        return null;
    }

    private void setParentPackage(MyElement parentPackage) {
        // Logic to handle setting the parent package
    }

    private void setDefaultParentPackage(String defaultParentPackage) {
        // Logic to set a default parent package based on the user input
    }
}


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ElementDAO {

    private final JdbcTemplate jdbcTemplate;

    public ElementDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Element getElementByGUID(String elementGUID) {
        String sql = "SELECT * FROM elements WHERE element_guid = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{elementGUID}, new ElementMapper());
    }

    // ElementMapper is a RowMapper class to map query results to Element object
    private static class ElementMapper implements RowMapper<Element> {
        @Override
        public Element mapRow(ResultSet rs, int rowNum) throws SQLException {
            Element element = new Element();
            element.setId(rs.getLong("id"));
            element.setElementGUID(rs.getString("element_guid"));
            // Set other properties accordingly
            return element;
        }
    }
}



String originalNotes = row.getCell(7).getStringCellValue(); // Replace with your data source
String updatedNotes = originalNotes.replace("'", " ").replace("\"", " ");
myElement.setNotes(updatedNotes);


public void updateObjectProperties(String parentGUID, String starttime) {
        String sql = "UPDATE t_objectproperties SET value = ? WHERE property = 'lastUpdated' " +
                "AND object_id = (SELECT object_id FROM t_object WHERE ea_guid = ?)";
        
        jdbcTemplate.update(sql, starttime, parentGUID);


    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); // Adjust the format as needed
LocalDateTime now = LocalDateTime.now();
String startTime = now.format(formatter);




    @Service
public class DataExtractorDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Elements getElementsForPackage(String packageID) {
        String sql = "SELECT * FROM t_object WHERE package_id = ?";
        
        return jdbcTemplate.queryForObject(sql, new Object[]{packageID}, new ElementsRowMapper());
    }
}

class ElementsRowMapper implements RowMapper<Elements> {
    @Override
    public Elements mapRow(ResultSet rs, int rowNum) throws SQLException {
        Elements elements = new Elements();
        elements.setId(rs.getInt("id"));
        elements.setName(rs.getString("name"));
        // Map other fields
        
        return elements;
    }
}


