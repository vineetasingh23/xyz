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
      background: "none", // Remove background color behind label value
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
