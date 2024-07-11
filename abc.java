import React from 'react';
import Drawer from '@mui/material/Drawer';
import Toolbar from '@mui/material/Toolbar';
import List from '@mui/material/List';
import ListItem from '@mui/material/ListItem';
import ListItemButton from '@mui/material/ListItemButton';
import Tooltip from '@mui/material/Tooltip';
import Collapse from '@mui/material/Collapse';
import Divider from '@mui/material/Divider';
import { Box } from '@mui/material';
import { Link } from 'react-router-dom';

function Sidebar({ open, queues, queuesOpen }) {
  return (
    <Drawer
      variant="permanent"
      open={open}
      id="drawer"
      PaperProps={{
        sx: {
          height: "100vh",
          backgroundColor: "headerandSidebarColor",
          display: "flex",
          overflow: "hidden",
        },
      }}
    >
      <Toolbar variant="dense" />
      <List>
        <ListItem disablePadding sx={{ display: "block" }}>
          <Tooltip title="Template" placement="right">
            <ListItemButton>...</ListItemButton>
          </Tooltip>
        </ListItem>
        <ListItem disablePadding sx={{ display: "block" }}>
          <Tooltip title="Outbox" placement="right">
            <ListItemButton>...</ListItemButton>
          </Tooltip>
        </ListItem>
        <ListItem disablePadding sx={{ display: "block" }}>
          <Tooltip title="Sent Items" placement="right">
            <ListItemButton>...</ListItemButton>
          </Tooltip>
        </ListItem>
        <ListItem disablePadding sx={{ display: "block" }}>
          <Tooltip title="Common Bin" placement="right">
            <ListItemButton>...</ListItemButton>
          </Tooltip>
        </ListItem>
        <ListItem disablePadding sx={{ display: "block" }}>
          <Tooltip title="Linked Queue" placement="right">
            <ListItemButton>...</ListItemButton>
          </Tooltip>
        </ListItem>
        <ListItem disablePadding sx={{ display: "block" }}>
          <Tooltip title={"Queues Count:" + (queues?.length || 0)} placement="right">
            <ListItemButton>...</ListItemButton>
          </Tooltip>
        </ListItem>
        <Collapse in={queuesOpen} timeout="auto" unmountOnExit>
          <Divider />
          <List
            sx={{
              mr: "15px",
              overflowY: "auto",
              overflowX: "hidden",
              flexGrow: 1,
            }}
            component={Box}
            disablePadding
          >
            {queues && queues.map((value, index) => (
              <ListItemButton
                key={index}
                component={Link}
                to={`/queue/${value.id}`}
                sx={{
                  minHeight: 48,
                  px: 2.5,
                  ml: open && index !== 0 ? 2.6 : 'auto',
                  backgroundColor: isActive(value.name) ? "sideBarClickColor" : "inherit",
                  "&:hover": {
                    backgroundColor: isActive(value.name) ? "sideBarClickColor" : "inherit",
                  },
                }}
              >
                {value.name}
              </ListItemButton>
            ))}
          </List>
        </Collapse>
      </List>
    </Drawer>
  );
}

export default Sidebar;
