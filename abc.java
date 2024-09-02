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
            value={formState[item.fieldName] || ""}
            label={item.fieldName}
            onChange={(e) => handleFieldChange(item.fieldName, e.target.value)}
            displayEmpty
            sx={{ width: "310px", height: "40px" }}
          >
            <MenuItem value="" disabled>
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
        shrink={true}  // Ensures the label stays above the text field
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
