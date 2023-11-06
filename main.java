@Service
public class ElementService {
    @Autowired
    private ElementRepository elementRepository;

    public Element getElementDetails(String objectID) {
        Map<String, Object> result = elementRepository.getElements(objectID);
        Element element = new Element();

        // Mapping the retrieved values to the Element object
        element.setObjectId((Integer) result.get("objectId"));
        element.setName((String) result.get("elementName"));
        element.setElementType((String) result.get("elementType"));
        element.setGuid((String) result.get("guid"));
        element.setAuthor((String) result.get("author"));
        // ... map other attributes similarly

        return element;
    }
}









