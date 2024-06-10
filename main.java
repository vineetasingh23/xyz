import React from "react";
import AutorenewIcon from "@mui/icons-material/Autorenew";
import Box from "@mui/material/Box";
import Button from "@mui/material/Button";
import { styled } from "@mui/system";
import { lightTheme, darkTheme } from "../Constants/theme";
import clsx from "clsx";

const RefreshIcon = styled(AutorenewIcon)(({ theme }) => ({
  '&.spin': {
    animation: 'spin 1s linear infinite',
  },
  '@keyframes spin': {
    '0%': {
      transform: 'rotate(0deg)',
    },
    '100%': {
      transform: 'rotate(360deg)',
    },
  },
}));

const ColorButton = styled(Button)(({ theme }) => ({
  backgroundColor: theme.palette.mode === 'light' ? lightTheme.darkBlue : darkTheme.darkBlue,
  position: 'absolute',
  right: '1.4%',
  borderRadius: '5px',
  color: 'white',
  '&:hover': {
    backgroundColor: theme.palette.mode === 'light' ? lightTheme.darkBlue : darkTheme.darkBlue,
    color: 'white',
    fontWeight: 'bold',
  },
}));

const RefreshButton = () => {
  const [spin, setSpin] = React.useState(false);

  const refresh = () => {
    setSpin(true);
    setTimeout(() => {
      setSpin(false);
      window.location.reload();
    }, 1000);
  };

  return (
    <Box onClick={refresh}>
      <ColorButton variant="contained" size="small">
        <RefreshIcon className={clsx({ spin })} sx={{ mr: 0.5 }} />
        Refresh
      </ColorButton>
    </Box>
  );
};

export default RefreshButton;
