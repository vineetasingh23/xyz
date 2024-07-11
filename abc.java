const CustomTextField = styled(TextField)(({ theme }) => ({
  '& .MuiOutlinedInput-root': {
    width: '100%',
    '& fieldset': {
      borderColor: theme.palette.primary.main,
    },
    '&:hover fieldset': {
      borderColor: theme.palette.secondary.main,
    },
    '&.Mui-focused fieldset': {
      borderColor: theme.palette.secondary.main,
    },
    '& .MuiInputBase-input': {
      color: 'red', // Change text color
      fontFamily: 'Arial', // Change font family
      fontSize: '16px', // Change font size
    },
  },
}));
