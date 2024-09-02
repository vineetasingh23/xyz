import React, { useState } from 'react';
import { Grid, Box, Button, FormControl, InputLabel, MenuItem, Select, Switch } from "@mui/material";
import StyledSelect from "../path/to/your/StyledSelect"; // Update the import path to your custom StyledSelect
import StyledTextField from "../path/to/your/StyledTextField"; // Update the import path to your custom StyledTextField
import BasicDateTimePicker from "../path/to/your/BasicDateTimePicker"; // Update the import path to your custom BasicDateTimePicker
import processFormConfig from "../path/to/your/processFormConfig.json"; // Update the import path to your processFormConfig

const initialState = processFormConfig.items.reduce((state, item) => {
  state[item.fieldName] = item.fieldType === "dropdown" ? "" : "";
  return state;
}, {});

const ViewProcessForms = ({ acceptRequest, requestId, display }) => {
  const [formState, setFormState] = useState(initialState);

  const handleFieldChange = (fieldName, value) => {
    setFormState(prevState => ({ ...prevState, [fieldName]: value }));
  };

  const renderField = (item) => {
    switch (item.fieldType) {
      case "dropdown":
        return (
          <FormControl fullWidth>
            <StyledSelect
              id={`${item.fieldName}-select`}
              value={formState[item.fieldName]}
              onChange={(e) => handleFieldChange(item.fieldName, e.target.value)}
              sx={{ width: "310px", height: "40px" }}
            >
              <MenuItem value="">
                <em>None</em>
              </MenuItem>
              {item.optionsList && item.optionsList.map((option, index) => (
                <MenuItem key={index} value={option}>
                  {option}
                </MenuItem>
              ))}
            </StyledSelect>
          </FormControl>
        );
      case "textField":
        return (
          <StyledTextField
            value={formState[item.fieldName]}
            onChange={(e) => handleFieldChange(item.fieldName, e.target.value)}
            defaultValue=""
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
            value={formState[item.fieldName]}
            onChange={(date) => handleFieldChange(item.fieldName, date)}
          />
        );
      default:
        return null;
    }
  };

  const chunkArray = (arr, chunkSize) => {
    const results = [];
    for (let i = 0; i < arr.length; i += chunkSize) {
      results.push(arr.slice(i, i + chunkSize));
    }
    return results;
  };

  const gridItems = chunkArray(processFormConfig.items, 3);

  return (
    <Box sx={{ display: display ? "block" : "none" }}>
      {acceptRequest && (
        <Box sx={{ p: 2 }}>
          {gridItems.map((row, index) => (
            <Grid
              container
              spacing={2}
              key={index}
              justifyContent={row.length < 3 ? 'center' : 'flex-start'}
              sx={{ mb: 5 }}
            >
              {row.map(item => (
                <Grid item xs={12} sm={6} md={4} key={item.fieldName}>
                  {item.fieldType === "switch" && <label>{item.fieldName}</label>}
                  {renderField(item)}
                </Grid>
              ))}
            </Grid>
          ))}
          <Box sx={{ display: "flex", justifyContent: "center", mt: 4 }}>
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



    case "dropdown":
      return (
        <FormControl fullWidth>
          <InputLabel
            id={`${item.fieldName}-input-select-label`}
            sx={{
              color:
                (useTheme().palette.mode === "light"
                  ? lightTheme.black
                  : darkTheme.white) + " !important",
            }}
          >
            {item.fieldName}
          </InputLabel>
          <StyledSelect
            labelId={`${item.fieldName}-input-select-label`}
            id={`${item.fieldName}-select`}
            value={formState[item.fieldName]}
            label={item.fieldName}
            onChange={(e) => handleFieldChange(item.fieldName, e.target.value)}
            sx={{ width: "310px", height: "40px" }}
          >
            <MenuItem value="">
              <em>None</em>
            </MenuItem>
            {item.optionsList &&
              item.optionsList.map((option, index) => (
                <MenuItem key={index} value={option}>
                  {option}
                </MenuItem>
              ))}
          </StyledSelect>
        </FormControl>
      );

    case "textField":
      return (
        <FormControl fullWidth>
          <InputLabel
            id={`${item.fieldName}-text-field-label`}
            sx={{
              color:
                (useTheme().palette.mode === "light"
                  ? lightTheme.black
                  : darkTheme.white) + " !important",
            }}
          >
            {item.fieldName}
          </InputLabel>
          <StyledTextField
            id={`${item.fieldName}-text-field`}
            value={formState[item.fieldName]}
            label={item.fieldName}
            onChange={(e) =>
              handleFieldChange(item.fieldName, e.target.value)
            }
            fullWidth
          />
        </FormControl>
      );
  );
};

export default ViewProcessForms;
