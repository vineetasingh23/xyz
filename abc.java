const renderField = (item: any) => {
  const isDisabled = [8, 5, 9].includes(statusCode);

  switch (item.fieldType) {
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
            IconComponent={isDisabled ? NotInterestedIcon : undefined}
          >
            {item.options && item.options.map((option: string, index: number) => (
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
          label={item.fieldName}
          defaultValue={""}
          size="small"
          sx={{
            width: "100%",
            "& label": {
              color: useTheme().palette.mode === 'light' ? 'black' : 'white',
            },
            "& .MuiInputBase-input": {
              color: useTheme().palette.mode === 'light' ? 'black' : 'white',
            },
          }}
          disabled={isDisabled}
          InputProps={{
            endAdornment: isDisabled ? <NotInterestedIcon color="error" /> : null,
          }}
        />
      );

    case "switch":
      return (
        <Box sx={{ display: 'flex', alignItems: 'center' }}>
          <Switch
            checked={formState[item.fieldName]}
            onChange={(e) => handleFieldChange(item.fieldName, e.target.checked)}
            disabled={isDisabled}
          />
          {isDisabled && <NotInterestedIcon color="error" sx={{ ml: 1 }} />}
        </Box>
      );

    case "dateField":
      return (
        <BasicDateTimePicker
          value={formState[item.fieldName]}
          onChange={(date) => handleFieldChange(item.fieldName, date)}
          dateLabel={item.fieldName}
          minDate={startValue} // Adjust these values based on your logic
          maxDate={endValue}   // Adjust these values based on your logic
          disable={isDisabled}
          InputProps={{
            endAdornment: isDisabled ? <NotInterestedIcon color="error" /> : null,
          }}
        />
      );

    default:
      return null;
  }
};
