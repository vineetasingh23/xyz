import React from "react";
import RefreshIcon from "@mui/icons-material/Refresh";
import Box from "@mui/material/Box";
import Button from "@mui/material/Button";
import { styled } from "@mui/material/styles";
import { makeStyles } from "@mui/styles";
import clsx from "clsx";

const useStyles = makeStyles((theme) => ({
    refresh: {
        marginTop: "20px",
        cursor: "pointer",
        margin: "auto",
        "&.spin": {
            animation: "$spin 1s 1",
            // pointerEvents:'none'
        }
    },
    "@keyframes spin": {
        "0%": {
            transform: "rotate(0deg)"
        },
        "100%": {
            transform: "rotate(360deg)"
        }
    }
}));

const ColorButton = styled(Button)(({ theme }) => ({
    position: "absolute",
    right: "1.4%",
    borderRadius: "5px",
    color: "white",
    backgroundColor: theme.palette.mode === 'light' ? lightTheme.darkBlue : darkTheme.darkBlue,
    "&:hover": {
        color: "white",
        fontWeight: "bold",
    }
}));

type Props = {};

const RefreshButton: React.FC<Props> = () => {
    const classes = useStyles();
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
            <ColorButton
                variant="contained"
                size="small"
                className={clsx({
                    [classes.refresh]: true,
                    spin: spin
                })}
            >
                <RefreshIcon sx={{ mr: 0.5 }} />
                Refresh
            </ColorButton>
        </Box>
    );
};

export default RefreshButton;
