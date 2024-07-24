import React, { useCallback } from "react";
import { IconButton, Tab, Tabs, Typography } from "@mui/material";
import CancelIcon from "@mui/icons-material/Cancel";
import Box from "@mui/material/Box";
import ViewRequestMain from "/screens/ViewRequestMain";
import ReturnToTableButton from "/ReturnToTableButton";
import CreateRequest from "/screens/CreateRequest";
import NewMemo from "/screens/NewMemo";
import CreateTemplate from "/screens/CreateTemplate";
import { useDispatch, useSelector } from "react-redux";
import { AppDispatch, RootState } from "/reduxState/store/store";
import { getChangeTab, getRemoveTab, TabState } from "/reduxState/reducers/StateSlices";
import { ActionCreatorWithPayload } from "@reduxjs/toolkit";
import SubjectIdSearch from "./screens/SubjectIdSearchResults";

interface TabPanelProps {
  children?: React.ReactNode;
  index: number;
  value: number;
}

function CustomTabPanel(props: TabPanelProps) {
  const { children, value, index, ...other } = props;
  return (
    <div
      role="tabpanel"
      hidden={value !== index}
      id={`simple-tabpanel-${index}`}
      aria-labelledby={`simple-tab-${index}`}
      {...other}
    >
      {value === index && (
        <Box>
          <Typography>{children}</Typography>
        </Box>
      )}
    </div>
  );
}

function allyProps(index: number) {
  return {
    id: `simple-tab-${index}`,
    "aria-controls": `simple-tabpanel-${index}`,
  };
}

interface TabPanelForTableProps {
  page: string;
}

const TabPanelForTable: React.FC<TabPanelForTableProps> = ({ page }) => {
  const dispatch = useDispatch<AppDispatch>();
  const showCorrespondence = true;
  const showComment = true;
  const showCycleTime = true;
  const showHistory = true;
  const showLinkedRequest = true;
  const showProcessForm = true;

  const getState = (state: RootState): TabState => {
    switch (page) {
      case "queue":
        return state.QueueReducer;
      case "linkedqueue":
        return state.LinkedQueueReducer;
      case "outbox":
        return state.OutboxReducer;
      case "sentitems":
        return state.SentItemReducer;
      case "template":
        return state.TemplateReducer;
      case "commonbin":
        return state.CommonbinReducer;
      case "search":
        return state.SearchReducer;
      default:
        return state.QueueReducer;
    }
  };

  const ReqList = useSelector((state: RootState) => getState(state).tabs);
  const selectedTab = useSelector((state: RootState) => getState(state).selectedTab);

  const handleTabChange = (event: React.SyntheticEvent, newValue: number) => {
    const action = getChangeTab(page) as ActionCreatorWithPayload<any, any>;
    dispatch(action({ id: newValue }));
  };

  const handleClose = useCallback(
    (event: React.MouseEvent, tabToDelete: number) => {
      event.stopPropagation();
      event.preventDefault();
      const action = getRemoveTab(page) as ActionCreatorWithPayload<any, any>;
      dispatch(action({ id: tabToDelete }));
    },
    [dispatch, page]
  );

  const handleRequestClick = (requestId: string) => {
    const existingTab = ReqList.indexOf(requestId);
    if (existingTab !== -1) {
      // If the tab is already open, switch to it
      handleTabChange(null, existingTab);
    } else {
      // Otherwise, open a new tab
      const action = getChangeTab(page) as ActionCreatorWithPayload<any, any>;
      dispatch(action({ id: ReqList.length }));
    }
  };

  return (
    <Box sx={{ width: "100%" }}>
      <Box display="flex">
        <ReturnToTableButton />
        <Tabs
          value={selectedTab}
          onChange={handleTabChange}
          variant="scrollable"
          scrollButtons="auto"
        >
          {ReqList.map((id, key) => (
            <Tab
              key={key}
              {...allyProps(key)}
              sx={{
                mt: 1.5,
                backgroundColor: "fieldblue",
                borderTopLeftRadius: 8,
                borderTopRightRadius: 8,
                border: "1px solid rgb(0 0 0 / 0.03)",
                color: "fontColor",
                "&:hover": {
                  backgroundColor: "fieldblueHover",
                  opacity: 0.9,
                },
              }}
              label={
                <span>
                  {id}
                  <IconButton
                    sx={{
                      height: "0.5px",
                      width: "0.5px",
                      ml: 1,
                      mt: -1,
                      color: "red",
                    }}
                    component="span"
                    onClick={(event) => handleClose(event, key)}
                  >
                    <CancelIcon />
                  </IconButton>
                </span>
              }
              onClick={() => handleRequestClick(id)}
            />
          ))}
        </Tabs>
      </Box>
      {selectedTab !== -1 && (
        <CustomTabPanel value={selectedTab} index={selectedTab}>
          {ReqList[selectedTab] === "New Request" && <CreateRequest />}
          {ReqList[selectedTab] === "New Memo" && <NewMemo />}
          {ReqList[selectedTab] === "New Template" && <CreateTemplate />}
          {ReqList[selectedTab].startsWith("EMS") && (
            <SubjectIdSearch searchString={ReqList[selectedTab]} />
          )}
          {!["New Request", "New Memo", "New Template"].includes(ReqList[selectedTab]) &&
            !ReqList[selectedTab].startsWith("EMS") && (
              <ViewRequestMain
                requestId={ReqList[selectedTab]}
                showCorrespondence={showCorrespondence}
                showComment={showComment}
                showCycleTime={showCycleTime}
                showHistory={showHistory}
                showLinkedRequest={showLinkedRequest}
                showProcessForm={showProcessForm}
              />
            )}
        </CustomTabPanel>
      )}
    </Box>
  );
};

export default TabPanelForTable;
