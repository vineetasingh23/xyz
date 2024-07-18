import React, { useRef, useEffect } from 'react';
import { TextField, InputAdornment } from '@mui/material';
import SearchIcon from '@mui/icons-material/Search';

interface SearchInputProps {
  searchInput: string;
  handleSearchChange: (event: React.ChangeEvent<HTMLInputElement>) => void;
}

const SearchInput: React.FC<SearchInputProps> = ({ searchInput, handleSearchChange }) => {
  const inputRef = useRef<HTMLInputElement>(null);

  useEffect(() => {
    if (inputRef.current) {
      inputRef.current.focus();
    }
  }, [searchInput]);

  return (
    <TextField
      inputRef={inputRef}
      variant="outlined"
      placeholder="Search Queues"
      value={searchInput}
      onChange={handleSearchChange}
      fullWidth
      InputProps={{
        startAdornment: (
          <InputAdornment position="start">
            <SearchIcon style={{ color: 'white', height: '20px' }} />
          </InputAdornment>
        ),
        sx: {
          color: 'white',
          '& .MuiOutlinedInput-input': {
            color: 'white',
          },
          '& .MuiInputAdornment-root svg': {
            color: 'white',
          },
          '& .MuiOutlinedInput-notchedOutline': {
            borderColor: 'white',
          },
          '&:hover .MuiOutlinedInput-notchedOutline': {
            borderColor: 'white',
          },
          '&.Mui-focused .MuiOutlinedInput-notchedOutline': {
            borderColor: 'white',
          },
        }
      }}
      sx={{
        marginBottom: '10px',
      }}
    />
  );
};

export default SearchInput;
