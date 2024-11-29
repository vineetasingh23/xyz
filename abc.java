import React from "react";
import { Checkbox } from "@mui/material";
import { styled, alpha } from "@mui/material/styles";
import { darkTheme, lightTheme } from "../Constants/theme";

export const CustomCheckbox = styled(Checkbox)(({ theme }) => ({
  color: theme.palette.mode === "light" ? lightTheme.darkBlue : darkTheme.darkBlue,
  "&.Mui-checked": {
    color: theme.palette.mode === "light" ? lightTheme.darkBlue : darkTheme.darkBlue,
  },
  "&:hover": {
    backgroundColor: alpha(
      theme.palette.mode === "light" ? lightTheme.iconBlue : darkTheme.iconBlue,
      theme.palette.action.hoverOpacity
    ),
  },
  "&.Mui-disabled": {
    color: theme.palette.mode === "light" ? theme.palette.grey[400] : theme.palette.grey[600],
  },
  "&.Mui-disabled + .MuiCheckbox-track": {
    opacity: theme.palette.mode === "light" ? 0.6 : 0.3,
  },
}));
