import React from 'react';
import { TextField, InputAdornment } from '@mui/material';
import SearchIcon from '@mui/icons-material/Search';

interface SearchInputProps {
  searchInput: string;
  handleSearchChange: (event: React.ChangeEvent<HTMLInputElement>) => void;
}

const SearchInput: React.FC<SearchInputProps> = ({ searchInput, handleSearchChange }) => {
  return (
    <TextField
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
      }}
      sx={{
        color: 'white',
        height: '40px',
        marginBottom: '10px',
      }}
    />
  );
};

export default SearchInput;
