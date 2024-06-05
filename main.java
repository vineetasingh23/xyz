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
import React, { useRef } from 'react';
import ContentCopyIcon from '@mui/icons-material/ContentCopy';
import { Button } from '@mui/material';
import './ContentCopyAnimation.css';

const ContentCopyAnimation = () => {
  const iconRef = useRef(null);

  const handleAnimation = () => {
    if (iconRef.current) {
      iconRef.current.classList.remove('animate');
      void iconRef.current.offsetWidth;  // Trigger a reflow to restart the animation
      iconRef.current.classList.add('animate');
    }
  };

  return (
    <div>
      <ContentCopyIcon ref={iconRef} className="content-copy-icon" />
      <Button variant="contained" color="primary" onClick={handleAnimation}>
        Animate Icon
      </Button>
    </div>
  );
};

export default ContentCopyAnimation;
.content-copy-icon {
  font-size: 40px; /* Adjust size as needed */
  transition: transform 0.3s ease-in-out;
}

.content-copy-icon.animate {
  transform: scale(1.5) rotate(20deg);
}
import React from 'react';
import ContentCopyAnimation from './ContentCopyAnimation';

function App() {
  return (
    <div className="App">
      <ContentCopyAnimation />
    </div>
  );
}

export default App;


    import { makeStyles } from '@material-ui/core/styles';

const useStyles = makeStyles((theme) => ({
  iconContainer: {
    position: 'relative',
    display: 'inline-flex',
    alignItems: 'center',
    cursor: 'pointer',
  },
  copyIcon: {
    transition: 'transform 0.3s, opacity 0.3s',
    opacity: 0,
    '&:hover': {
      opacity: 1,
      transition: 'transform 0.3s, opacity 0.3s',
    },
    '&:active': {
      opacity: 1,
    },
  },
  animate: {
    animation: '$bounce 0.5s',
  },
  '@keyframes bounce': {
    '0%, 100%': {
      transform: 'translateY(0)',
    },
    '50%': {
      transform: 'translateY(-5px)',
    },
  },
}));




import React, { useState, useEffect } from 'react';
import Tooltip from '@material-ui/core/Tooltip';
import ContentCopyIcon from '@material-ui/icons/ContentCopy';
import Box from '@material-ui/core/Box';
import clsx from 'clsx';
import useStyles from './useStyles'; // Assuming you have a useStyles hook

const CopyButton = (props) => {
  const classes = useStyles();
  const [animate, setAnimate] = useState(false);
  const [showTooltip, setShowTooltip] = useState(false);

  const buttonClicked = () => {
    setAnimate(true);
    setShowTooltip(true);
    setTimeout(() => {
      setAnimate(false);
    }, 1000); // Duration of the animation
  };

  useEffect(() => {
    let timer;
    if (showTooltip) {
      timer = setTimeout(() => {
        setShowTooltip(false);
      }, 2000); // Tooltip will be visible for 2 seconds
    }
    return () => clearTimeout(timer);
  }, [showTooltip]);

  const handleContainerClick = () => {
    // Implement your copy logic here
    buttonClicked();
  };

  return (
    <Box
      className={classes.iconContainer}
      onClick={handleContainerClick}
      onMouseEnter={() => setAnimate(true)}
      onMouseLeave={() => setAnimate(f
    >
      {props.value}
      <Tooltip
        title={showTooltip ? "Copied!" : ""}
        leaveDelay={500}
        open={showTooltip}
      >
        <ContentCopyIcon
          className={clsx(classes.copyIcon, { [classes.animate]: animate })}
          style={{ color: "iconblue", height: 15 }}
        />
      </Tooltip>
    </Box>
  );
};



export default CopyButton;









import React, { useState, useEffect } from 'react';
import Tooltip from '@material-ui/core/Tooltip';
import ContentCopyIcon from '@material-ui/icons/ContentCopy';
import Box from '@material-ui/core/Box';
import clsx from 'clsx';
import { makeStyles } from '@material-ui/core/styles';

const useStyles = makeStyles((theme) => ({
  iconContainer: {
    position: 'relative',
    display: 'inline-flex',
    alignItems: 'center',
    cursor: 'pointer',
    '&:hover $copyIcon': {
      opacity: 1,
      transition: 'opacity 0.3s',
    },
  },
  copyIcon: {
    transition: 'transform 0.3s, opacity 0.3s',
    opacity: 0,
    '&:active': {
      opacity: 1,
    },
  },
  animate: {
    animation: '$bounce 0.5s',
  },
  '@keyframes bounce': {
    '0%, 100%': {
      transform: 'translateY(0)',
    },
    '50%': {
      transform: 'translateY(-5px)',
    },
  },
}));

const CopyButton = (props) => {
  const classes = useStyles();
  const [animate, setAnimate] = useState(false);
  const [showTooltip, setShowTooltip] = useState(false);

  const buttonClicked = () => {
    setAnimate(true);
    setShowTooltip(true);
    setTimeout(() => {
      setAnimate(false);
    }, 1000); // Duration of the animation
  };

  useEffect(() => {
    let timer;
    if (showTooltip) {
      timer = setTimeout(() => {
        setShowTooltip(false);
      }, 2000); // Tooltip will be visible for 2 seconds
    }
    return () => clearTimeout(timer);
  }, [showTooltip]);

  const handleContainerClick = () => {
    // Implement your copy logic here
    buttonClicked();
  };

  return (
    <Box
      className={classes.iconContainer}
      onClick={handleContainerClick}
      onMouseEnter={() => setAnimate(true)}
      onMouseLeave={() => setAnimate(false)}
    >
      {props.value}
      <Tooltip
        title={showTooltip ? "Copied!" : ""}
        leaveDelay={500}
        open={showTooltip}
      >
        <ContentCopyIcon
          className={clsx(classes.copyIcon, { [classes.animate]: animate })}
          style={{ color: "iconblue", height: 15 }}
        />
      </Tooltip>
    </Box>
  );
};

export default CopyButton;



import { makeStyles } from '@material-ui/core/styles';

const useStyles = makeStyles((theme) => ({
  iconContainer: {
    position: 'relative',
    display: 'inline-flex',
    alignItems: 'center',
    cursor: 'pointer',
    '&:hover $copyIcon': {
      opacity: 1,
      transition: 'opacity 0.3s',
    },
  },
  copyIcon: {
    transition: 'transform 0.3s, opacity 0.3s',
    opacity: 0,
    '&:active': {
      opacity: 1,
    },
  },
  animate: {
    animation: '$bounce 0.5s',
  },
  '@keyframes bounce': {
    '0%, 100%': {
      transform: 'translateY(0)',
    },
    '50%': {
      transform: 'translateY(-5px)',
    },
  },
}));


