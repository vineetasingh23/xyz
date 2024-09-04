case "dropdown":
  return (
    <FormControl fullWidth>
      <InputLabel
        id={`${item.fieldName}-input-select-label`}
        sx={{
          color:
            useTheme().palette.mode === "light"
              ? lightTheme.black
              : darkTheme.white + " !important",
        }}
      >
        {item.fieldName}
      </InputLabel>
      <StyledSelect
        labelId={`${item.fieldName}-input-select-label`}
        id={`${item.fieldName}-select`}
        value={formState[item.fieldName] || "select"}
        label={item.fieldName}
        onChange={(e) => handleFieldChange(item.fieldName, e.target.value)}
        sx={{ height: "40px" }}
        disabled={isDisabled}
        MenuProps={{
          PaperProps: {
            sx: {
              "& .MuiMenuItem-root.Mui-disabled": {
                color: "red", // Color for the disabled item
              },
            },
          },
        }}
        IconComponent={isDisabled ? NotInterestedIcon : undefined} 
      >
        <MenuItem value="select" disabled>
          <em>Select</em>
        </MenuItem>
        {item.options &&
          item.options.map((option: string, index: number) => (
            <MenuItem key={index} value={option}>
              {option}
            </MenuItem>
          ))}
      </StyledSelect>
    </FormControl>
  );
