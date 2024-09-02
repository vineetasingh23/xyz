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
            value={formState[item.fieldName] || "select"}  // Default to "select"
            label={item.fieldName}
            onChange={(e) => handleFieldChange(item.fieldName, e.target.value)}
            sx={{ width: "310px", height: "40px" }}
          >
            <MenuItem value="select" disabled>
              <em>select</em>
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
