import React, { useState } from "react";
import ContentCopyIcon from '@mui/icons-material/ContentCopy';
import { Box } from "@mui/material";
import "../tableStyles.css";
import { makeStyles } from "@mui/styles";
import clsx from "clsx";

const useStyles = makeStyles((theme) => ({
    copyIcon: {
        cursor: "pointer",
        transition: "transform 0.3s",
        "&.animate": {
            animation: "$bounce 0.5s",
        }
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
    const [hovered, setHovered] = useState(false);
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
        <ContentCopyIcon
            className={clsx(classes.copyIcon, { animate })}
            onClick={buttonClicked}
            style={{
                color: "iconblue",
                height: "24px",
                ...hovered && { color: "hoverColor" } // Optionally, change color on hover
            }}
            onMouseEnter={() => setHovered(true)}
            onMouseLeave={() => setHovered(false)}
        />
    );
};
