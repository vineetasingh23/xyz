import ContentCopyIcon from '@mui/icons-material/ContentCopy';
import { Box, Tooltip } from "@mui/material";
import React, { useEffect, useState } from "react";
import { styled } from "@mui/system";
import clsx from "clsx";

const IconContainer = styled(Box)(({ theme }) => ({
  position: 'relative',
  display: 'inline-flex',
  alignItems: 'center',
  cursor: 'pointer',
  '&:hover .copyIcon': {
    opacity: 1,
    transition: 'opacity 0.35s',
  },
}));

const CopyIcon = styled(ContentCopyIcon)(({ theme }) => ({
  transition: 'opacity 0.35s',
  opacity: 0,
  '&.animate': {
    opacity: 1,
    transform: 'translateY(-5px)',
    animation: 'bounce 0.55s',
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

const CustomCellRenderer = (props) => {
  const [hovered, setHovered] = useState(false);
  const [animate, setAnimate] = useState(false);
  const [showTooltip, setShowTooltip] = useState(false);

  const buttonClicked = async () => {
    await navigator.clipboard.writeText(props.value);
    setAnimate(true);
    setShowTooltip(true);
    setTimeout(() => {
      setAnimate(false);
    }, 500);
  };

  const handleContainerClick = () => {
    buttonClicked();
  };

  useEffect(() => {
    let timer;
    if (showTooltip) {
      timer = setTimeout(() => {
        setShowTooltip(false);
      }, 1000);
    }
    return () => clearTimeout(timer);
  }, [showTooltip]);

  return (
    <IconContainer
      onClick={handleContainerClick}
      onMouseEnter={() => setAnimate(true)}
      onMouseLeave={() => setAnimate(false)}
    >
      {props.value}
      <Tooltip
        title={showTooltip ? "Copied Successfully!" : ""}
        leaveDelay={500}
        open={showTooltip}
      >
        <CopyIcon
          className={clsx('copyIcon', { 'animate': animate })}
          style={{ color: "iconblue", height: 15 }}
        />
      </Tooltip>
    </IconContainer>
  );
};

export default CustomCellRenderer;
