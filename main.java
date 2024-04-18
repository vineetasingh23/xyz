const StyledSelect = styled(Select)(({ theme }) => ({
  height: "35px",
  width: 210,
  alignItems: "center",
  backgroundColor: "#ffffff",
  borderStyle: "solid",
  borderColor: theme.palette.mode === 'light' ? theme.fieldblue : theme.darkFieldblue,
  borderRadius: 10,
  "& .MuiInputLabel-root": {
    background: "#ffffff",
    padding: "0 8px",
    zIndex: 1, // Ensure label appears above the input border
  },
  "& .MuiOutlinedInput-root": {
    "& .MuiSelect-root": {
      paddingTop: "8px", // Adjust top padding for the text visibility
    },
    "& .MuiOutlinedInput-notchedOutline": {
      border: "none",
    },
    "&:hover .MuiOutlinedInput-notchedOutline": {
      borderColor: theme.palette.mode === 'light' ? theme.fieldblue : theme.darkFieldblue,
    },
    "& .Mui-focused .MuiOutlinedInput-notchedOutline": {
      borderColor: theme.palette.mode === 'light' ? theme.primarycolor : theme.darkPrimarycolor,
    },
  },
}));

<FormControl>
  <InputLabel id="mailgroup-input-select-label">MailGroup</InputLabel>
  <StyledSelect
    labelId="mailgroup-select-label"
    id="mailgroup-select"
    value={selectedMailGroup}
    onChange={handleMailGroupChange}
    sx={{ mr: "60px" }}
  >
    <MenuItem value="A11">{"All"}</MenuItem>
    {mailgroups !== null &&
      mailgroups.map((value, index) => (
        <MenuItem value={String(index)}>{value.mailGroup}</MenuItem>
      ))}
  </StyledSelect>
</FormControl>
