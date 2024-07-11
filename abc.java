import React from 'react';
import TextField from '@mui/material/TextField';
import { styled } from '@mui/system';

const CustomTextField = styled(TextField)(({ theme }) => ({
  '& .MuiInputBase-input': {
    color: 'red', // Change text color
    fontFamily: 'Arial', // Change font family
    fontSize: '16px', // Change font size
  },
  width: '100%', // Set the width to 100%
}));

function App() {
  return (
    <CustomTextField
      variant="outlined"
      disabled
      value="Your text here"
      multiline
      rows={2}
    />
  );
}

export default App;
