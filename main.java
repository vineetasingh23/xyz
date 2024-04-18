import { FormControl, InputLabel, MenuItem, Select } from '@mui/material';
import styled from '@emotion/styled';

const StyledSelect = styled(Select)(({ lightTheme }) => ({
  position: "relative",
  height: "35px",
  width: 210,
  backgroundColor: "#ffffff",
  borderStyle: "solid",
  borderColor: lightTheme.fieldblue,
  borderRadius: 10,
  "& .MuiOutlinedInput-root": {
    position: "relative",
    "& .MuiOutlinedInput-notchedOutline": {
      border: "none",
    },
    "&:hover .MuiOutlinedInput-notchedOutline": {
      borderColor: lightTheme.fieldblue,
    },
    "& .Mui-focused .MuiOutlinedInput-notchedOutline": {
      borderColor: lightTheme.primarycolor,
    },
    "& .MuiSelect-root": {
      paddingTop: "10px", // Adjust top padding for the floating label
    },
  },
  "& .MuiInputLabel-root": {
    position: "absolute",
    top: 0,
    left: 10, // Adjust left position for the floating label
    color: lightTheme.darkGreen,
    backgroundColor: "#ffffff", // Background color behind the label
    padding: "0 5px", // Adjust padding for the label
    zIndex: 1, // Ensure label appears above the input
    pointerEvents: "none", // Ensure label doesn't capture events
    transition: "0.3s ease-out", // Smooth transition for label movement
    transform: "translateY(-50%)", // Move label up when focused
  },
  "& .Mui-focused .MuiInputLabel-root": {
    transform: "translateY(-90%)", // Move label further up when focused
    backgroundColor: "transparent", // Transparent background when focused
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
