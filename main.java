import React, { useState, useEffect } from "react";
import Box from "@mui/material/Box";
import Tabs from "@mui/material/Tabs";
import Tab from "@mui/material/Tab";
import Snackbar from "@mui/material/Snackbar";
import { AxiosRequestConfig } from "axios";
import ApiConfig from "./path/to/apiConfig";
import ApiEndPoints from "./path/to/apiEndPoints";

interface TabPanelProps {
  children?: React.ReactNode;
  index: number;
  value: number;
}

function CustomTabPanel(props: TabPanelProps) {
  const { children, value, index, ...other } = props;

  return (
    <div
      style={{ height: "90%", width: "100%" }}
      role="tabpanel"
      hidden={value !== index}
      id={`simple-tabpanel-${index}`}
      aria-labelledby={`simple-tab-${index}`}
      {...other}
    >
      {value === index && <Box sx={{ height: "90%", width: "100%", p: 3 }}>{children}</Box>}
    </div>
  );
}

function areaControlProps(index: number) {
  return {
    id: `search-tab-${index}`,
    "aria-controls": `search-tabpanel-${index}`,
  };
}

interface SubjectIdSearchProps {
  searchString: string;
}

export default function SubjectIdSearch({ searchString }: SubjectIdSearchProps) {
  const [correspondenceData, setCorrespondenceData] = useState([]);
  const [auditTrailData, setAuditTrailData] = useState([]);
  const [value, setValue] = useState(0);
  const [isLoading, setIsLoading] = useState(true);
  const [snackbarOpen, setSnackbarOpen] = useState(false);
  const [snackbarMessage, setSnackbarMessage] = useState("");
  const [snackbarSeverity, setSnackbarSeverity] = useState<"success" | "error">("success");

  useEffect(() => {
    if (searchString) {
      fetchCorrespondenceData();
      fetchAuditTrailData();
    }
  }, [searchString]);

  const fetchCorrespondenceData = async () => {
    setIsLoading(true);
    try {
      const url = ApiEndPoints.ROOT + ApiEndPoints.CORRESPONDENCE + searchString;
      const axiosConfig: AxiosRequestConfig = {
        headers: {
          "Access-Control-Allow-Origin": "*",
          "Content-Type": "application/json",
          "ct-remote-user": ApiEndPoints.USER,
        },
      };
      const response = await ApiConfig.getCall(url, axiosConfig);
      setCorrespondenceData(response.data.items);
    } catch (error) {
      setSnackbarMessage("Error fetching Correspondence data");
      setSnackbarSeverity("error");
      setSnackbarOpen(true);
    } finally {
      setIsLoading(false);
    }
  };

  const fetchAuditTrailData = async () => {
    setIsLoading(true);
    try {
      const url = ApiEndPoints.ROOT + ApiEndPoints.AUDIT_TRAIL + searchString;
      const axiosConfig: AxiosRequestConfig = {
        headers: {
          "Access-Control-Allow-Origin": "*",
          "Content-Type": "application/json",
          "ct-remote-user": ApiEndPoints.USER,
        },
      };
      const response = await ApiConfig.getCall(url, axiosConfig);
      setAuditTrailData(response.data.items);
    } catch (error) {
      setSnackbarMessage("Error fetching Audit Trail data");
      setSnackbarSeverity("error");
      setSnackbarOpen(true);
    } finally {
      setIsLoading(false);
    }
  };

  const handleCloseSnackbar = () => {
    setSnackbarOpen(false);
  };

  const handleChange = (event: React.SyntheticEvent, newValue: number) => {
    setValue(newValue);
  };

  return (
    <Box width="100%" height="100%" display="flex" overflow="hidden" margin="5px" sx={{ overflowY: "hidden", flexDirection: "row", justifyContent: "center" }}>
      <Box height="100%" width="100%" display="flex" flexDirection="column" sx={{ backgroundColor: "background.paper" }}>
        <Box sx={{ borderBottom: 1, borderColor: "divider" }}>
          <Tabs value={value} onChange={handleChange} aria-label="basic tabs example" sx={{ color: 'red', "&:hover": { fontWeight: "bold" } }}>
            <Tab label="Correspondence" sx={{ "&:active": { color: "fontColor", fontWeight: "bold" }, "&:hover": { fontWeight: "bold" } }} {...areaControlProps(0)} />
            <Tab label="Audit Trail" sx={{ "&:active": { color: "fontColor", fontWeight: "bold" }, "&:hover": { fontWeight: "bold" } }} {...areaControlProps(1)} />
          </Tabs>
        </Box>
        <CustomTabPanel value={value} index={0}>
          {isLoading ? <Loader /> : <Correspondence correspondence={correspondenceData} />}
        </CustomTabPanel>
        <CustomTabPanel value={value} index={1}>
          {isLoading ? <Loader /> : <AuditTrail audittrail={auditTrailData as unknown as AuditTrailModel} />}
        </CustomTabPanel>
      </Box>
      <Snackbar
        open={snackbarOpen}
        message={snackbarMessage}
        autoHideDuration={6000}
        onClose={handleCloseSnackbar}
        severity={snackbarSeverity}
      />
    </Box>
  );
}
