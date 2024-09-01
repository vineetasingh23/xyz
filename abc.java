import dayjs from "dayjs";
import React, { useState } from "react";
import { Box, Button, MenuItem, Select, Switch, TextField, Grid } from "@mui/material";
import BasicDateTimePicker from "../BasicDateTimePicker";
import processFormConfig from "../assets/mockdata/processFormConfig.json";

function ViewProcessForms({ acceptRequest, display }: { acceptRequest?: boolean; display: boolean; }) {
  const [formState, setFormState] = useState(() => {
    const initialState: any = {};
    processFormConfig.items.forEach(item => {
      initialState[item.fieldName] = item.fieldType === "dropdown" ? "" : false;
    });
    return initialState;
  });

  const handleFieldChange = (fieldName: string, value: any) => {
    setFormState(prevState => ({ ...prevState, [fieldName]: value }));
  };

  const renderField = (item: any) => {
    switch (item.fieldType) {
      case "dropdown":
        return (
          <Select
            value={formState[item.fieldName]}
            onChange={(e) => handleFieldChange(item.fieldName, e.target.value)}
            fullWidth
          >
            {item.optionsList.map((option: string) => (
              <MenuItem key={option} value={option}>
                {option}
              </MenuItem>
            ))}
          </Select>
        );
      case "textField":
        return (
          <TextField
            value={formState[item.fieldName]}
            onChange={(e) => handleFieldChange(item.fieldName, e.target.value)}
            fullWidth
          />
        );
      case "switch":
        return (
          <Switch
            checked={formState[item.fieldName]}
            onChange={(e) => handleFieldChange(item.fieldName, e.target.checked)}
          />
        );
      case "dateField":
        return (
          <BasicDateTimePicker
            value={formState[item.fieldName] || dayjs()}
            onChange={(date) => handleFieldChange(item.fieldName, date)}
          />
        );
      default:
        return null;
    }
  };

  return (
    <Box sx={{ display: display ? "block" : "none" }}>
      {acceptRequest && (
        <Box sx={{ p: 2 }}>
          <Grid container spacing={2}>
            {processFormConfig.items.map(item => (
              <Grid item xs={12} sm={6} md={4} key={item.fieldName}>
                <label>{item.fieldName}</label>
                {renderField(item)}
              </Grid>
            ))}
          </Grid>

          <Box sx={{ display: 'flex', justifyContent: 'center', mt: 4 }}>
            <Button
              variant="contained"
              sx={{
                backgroundColor: "darkBlue",
                color: "white",
                "&:hover": {
                  backgroundColor: "darkBlue",
                  color: "white",
                  fontWeight: "bold",
                },
              }}
            >
              <span>SAVE</span>
            </Button>
          </Box>
        </Box>
      )}
    </Box>
  );
}

export default ViewProcessForms;
