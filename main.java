// Loader.js
import React from 'react';
import { CircularProgress, Box } from '@mui/material';

const Loader = () => {
  return (
    <Box
      sx={{
        display: 'flex',
        justifyContent: 'center',
        alignItems: 'center',
        height: '100%',
      }}
    >
      <CircularProgress />
    </Box>
  );
};

export default Loader;


 {isLoading ? (
            <Loader marginTop="50px" /> // You can adjust the marginTop value here
          ) : viewRules.length === 0 ? (
            <Typography>No data to display</Typography>
          ) : (
            <RulesetList rulesList={viewRules} />
          )}
