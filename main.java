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
