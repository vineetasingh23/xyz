import React from "react";
import { Box, Button, Stack } from "@mui/material";
import ReturnToTableButton from "../ReturnToTableButton";
import Table from "./Table";
import abc from "../abc"; // Import abc component
import { useTheme } from "../../ThemeContext";

type Props = {
  // Define your props if needed
};

const CommonBin = (props: Props) => {
  const [CommonBinData, setCommonBinData] = React.useState(/* initial data */);
  const { currentTheme, toggleTheme } = useTheme(); // Get currentTheme and toggleTheme from context

  const handleMoveToWorkflow = () => {
    // Handle move to workflow action
  };

  return (
    <Box sx={{ height: "100%", width: "100%" }}>
      <Stack direction="row">
        <ReturnToTableButton />
        <Button
          sx={{
            backgroundColor: currentTheme.background, // Use current theme's background color
            color: currentTheme.text, // Use current theme's text color
            position: "absolute",
            right: 10,
            borderRadius: "5px",
            fontSize: "10px",
            "&:hover": {
              backgroundColor: currentTheme.background, // Use current theme's background color on hover
            },
          }}
          onClick={toggleTheme} // Toggle the theme on button click
        >
          <span>{currentTheme.name === "light" ? "Dark Mode" : "Light Mode"}</span>
        </Button>
      </Stack>
      <Box sx={{ height: "68%" }}>
        <Table data={CommonBinData} />
      </Box>
      {/* Render abc component */}
      <abc />
    </Box>
  );
};

export default CommonBin;
