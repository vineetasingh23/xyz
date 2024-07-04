const CreateRules = () => {
  const [rulesList, setRulesList] = useState([]);

  const getCreateRules = async () => {
    // Fetch logic here
  };

  useEffect(() => {
    getCreateRules();
  }, []);

  const handleDelete = async (id) => {
    await deleteItem(id);
    // Optionally refetch the list or update the state to remove the deleted item
    setRulesList(rulesList.filter(item => item.id !== id));
  };

  return (
    <div>
      {/* Other components and logic */}
      <ItemList items={rulesList} onDelete={handleDelete} />
    </div>
  );
};





import axios from 'axios';
import { ApiEndPoints } from '../Constants/ApiEndPoints';

const deleteItem = async (id) => {
  try {
    const url = `${ApiEndPoints.ROOT}${ApiEndPoints.DELETE_RULE}/${id}`;
    const axiosConfig = {
      headers: {
        "Access-Control-Allow-Origin": "*",
        "Content-Type": "application/json",
      },
    };

    const response = await axios.delete(url, axiosConfig);
    console.log('Delete successful:', response);
    // Optionally update your state here
  } catch (error) {
    console.error('Error deleting item:', error);
  }
};
import DeleteIcon from '@mui/icons-material/Delete';

// Assuming you have a list of items to display
const ItemList = ({ items, onDelete }) => {
  return (
    <ul>
      {items.map((item) => (
        <li key={item.id}>
          {item.name}
          <DeleteIcon onClick={() => onDelete(item.id)} />
        </li>
      ))}
    </ul>
  );
};
