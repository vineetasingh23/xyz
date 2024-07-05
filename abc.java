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









import DeleteIcon from "@mui/icons-material/Delete";
import { IconButton, Paper, Table, TableCell, TableContainer, TableHead, TableRow, useTheme } from "@mui/material";
import axios from "axios";
import React, { useState } from "react";
import { ApiEndPoints } from "../Constants/ApiEndPoints";
import { Rules } from "../models/rules";

type RulesetListProps = {
  rulesList: Rules[];
};

const RulesetList = ({ rulesList }: RulesetListProps) => {
  const [rules, setRules] = useState(rulesList);

  const handleDelete = async (ruleId: string) => {
    try {
      const url = `${ApiEndPoints.ROOT}${ApiEndPoints.DELETE_RULE}/${ruleId}`;
      const axiosConfig = {
        headers: {
          "Access-Control-Allow-Origin": "*",
          "Content-Type": "application/json",
        },
      };

      const response = await axios.delete(url, axiosConfig);
      console.log('Delete successful:', response);

      // Remove the deleted rule from the state
      setRules(rules.filter(rule => rule.ruleId !== ruleId));
    } catch (error) {
      console.error('Error deleting rule:', error);
    }
  };

  const columns = ["Conditions", "Action", "Rule Priority"];

  return (
    <TableContainer component={Paper} sx={{ height: "90%", pl: "30px", pr: "30px", backgroundColor: "fieldblue", backgroundImage: 'none' }}>
      <Table stickyHeader size="small" aria-label="simple table">
        <TableHead sx={{ borderBottom: 2, borderColor: "gray", backgroundColor: "fieldblue" }}>
          <TableRow>
            {columns.map(value => (
              <TableCell key={value} sx={{ fontWeight: "bold", backgroundColor: "fieldblue" }} align="center">
                {value}
              </TableCell>
            ))}
          </TableRow>
        </TableHead>
        <tbody>
          {rules.map((item, index) => (
            <TableRow key={item.ruleId}>
              <TableCell align="center">{item.conditions}</TableCell>
              <TableCell align="center">{item.actions}</TableCell>
              <TableCell align="center">{item.rulePriority}</TableCell>
              <TableCell align="center">
                <IconButton
                  size="small"
                  sx={{ p: "3px", backgroundColor: "darkBlue", color: "white", '&:hover': { backgroundColor: "darkBlue", color: "white", opacity: 0.9 } }}
                  onClick={() => handleDelete(item.ruleId)}
                >
                  <DeleteIcon fontSize="small" />
                </IconButton>
              </TableCell>
            </TableRow>
          ))}
        </tbody>
      </Table>
    </TableContainer>
  );
};

export default RulesetList;
