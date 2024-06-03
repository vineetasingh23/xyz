import React, { useState } from "react";
import ContentCopyIcon from '@mui/icons-material/ContentCopy';
import { Box } from "@mui/material";
import { makeStyles } from "@mui/styles";
import clsx from "clsx";
import "../tableStyles.css";

const useStyles = makeStyles((theme) => ({
    iconContainer: {
        position: "relative",
        display: "inline-block",
    },
    copyIcon: {
        cursor: "pointer",
        transition: "transform 0.3s, opacity 0.3s",
        opacity: 0,
    },
    visible: {
        opacity: 1,
    },
    animate: {
        animation: "$bounce 0.5s",
    },
    "@keyframes bounce": {
        "0%, 100%": {
            transform: "translateY(0)",
        },
        "50%": {
            transform: "translateY(-10px)",
        }
    }
}));

export default (props: CustomCellRendererProps) => {
    const [animate, setAnimate] = useState(false);
    const classes = useStyles();

    const buttonClicked = async () => {
        await navigator.clipboard.writeText(props.value);
        alert("Text copied");
        setAnimate(true);
        setTimeout(() => {
            setAnimate(false);
        }, 500);
    };

    return (
        <Box
            className={classes.iconContainer}
            onMouseEnter={() => setHovered(true)}
            onMouseLeave={() => setHovered(false)}
            style={{ position: "relative", display: "inline-block" }}
        >
            <ContentCopyIcon
                className={clsx(classes.copyIcon, { [classes.visible]: hovered, [classes.animate]: animate })}
                onClick={buttonClicked}
                style={{
                    color: "iconblue",
                    height: "24px"
                }}
            />
        </Box>
    );
};
