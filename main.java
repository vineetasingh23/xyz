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


   {viewRules.length === 0 ? (
            <Loader />
          ) : (
            <RulesetList rulesList={viewRules} />
          )}
