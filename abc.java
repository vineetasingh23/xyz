import { styled, TextField } from '@mui/material';
import React from 'react';
import { lightTheme, darkTheme } from '/theme';

export const StyledTextField = styled(TextField)(({ theme }) => ({
  '& .MuiOutlinedInput-root': {
    width: '310px',
    borderRadius: '10px',
    justifyContent: 'center',
    color: theme.palette.mode === 'light' ? lightTheme.fontColor : darkTheme.fontColor, // Ensuring text color is visible
    '& fieldset': {
      border: `1px solid`,
      borderColor: theme.palette.mode === 'light' ? lightTheme.primaryColor : darkTheme.primaryColor,
      backgroundColor: theme.palette.mode === 'light' ? lightTheme.white : 'transparent',
    },
  },
  '& .MuiInputLabel-root': {
    color: theme.palette.mode === 'light' ? lightTheme.fontColor : darkTheme.white, // Ensuring label color is visible
  },
  '& .MuiOutlinedInput-input': {
    color: theme.palette.mode === 'light' ? 'black' : 'white', // Text color based on theme mode
  },
}));
