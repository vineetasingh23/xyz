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
import { styled } from '@mui/system';

const ScrollbarStyledList = styled(List)(({ theme }) => ({
  overflowY: 'auto',
  overflowX: 'hidden',
  flexGrow: 1,
  '&::-webkit-scrollbar': {
    width: '12px',
  },
  '&::-webkit-scrollbar-thumb': {
    backgroundColor: '#888', // Thumb color
    borderRadius: '10px',
  },
  '&::-webkit-scrollbar-thumb:hover': {
    backgroundColor: '#555', // Thumb hover color
  },
  '&::-webkit-scrollbar-track': {
    backgroundColor: '#f1f1f1', // Track color
    borderRadius: '10px',
  },
  '&::-ms-overflow-style': 'auto', // For Edge
  '&::-ms-scrollbar': {
    width: '12px',
  },
  '&::-ms-scrollbar-thumb': {
    backgroundColor: '#888', // Thumb color
    borderRadius: '10px',
  },
  '&::-ms-scrollbar-thumb:hover': {
    backgroundColor: '#555', // Thumb hover color
  },
  '&::-ms-scrollbar-track': {
    backgroundColor: '#f1f1f1', // Track color
    borderRadius: '10px',
  },
}));

function Sidebar({ open, queues, queuesOpen }) {
  return (
    <Drawer
      variant="permanent"
      open={open}
      id="drawer"
      PaperProps={{
        sx: {
          height: '100%',
          backgroundColor: 'headerandSidebarColor',
          display: 'flex',
          flexDirection: 'column',
        },
      }}
    >
      <Toolbar variant="dense" />
      <List sx={{ flexGrow: 1 }}>
        <ListItem disablePadding sx={{ display: 'block' }}>
          <Tooltip title="Template" placement="right">
            <ListItemButton>...</ListItemButton>
          </Tooltip>
        </ListItem>
        <ListItem disablePadding sx={{ display: 'block' }}>
          <Tooltip title="Outbox" placement="right">
            <ListItemButton>...</ListItemButton>
          </Tooltip>
        </ListItem>
        <ListItem disablePadding sx={{ display: 'block' }}>
          <Tooltip title="Sent Items" placement="right">
            <ListItemButton>...</ListItemButton>
          </Tooltip>
        </ListItem>
        <ListItem disablePadding sx={{ display: 'block' }}>
          <Tooltip title="Common Bin" placement="right">
            <ListItemButton>...</ListItemButton>
          </Tooltip>
        </ListItem>
        <ListItem disablePadding sx={{ display: 'block' }}>
          <Tooltip title="Linked Queue" placement="right">
            <ListItemButton>...</ListItemButton>
          </Tooltip>
        </ListItem>
        <ListItem disablePadding sx={{ display: 'block' }}>
          <Tooltip title={'Queues Count:' + (queues?.length || 0)} placement="right">
            <ListItemButton>...</ListItemButton>
          </Tooltip>
        </ListItem>
        <Collapse in={queuesOpen} timeout="auto" unmountOnExit>
          <Divider />
          <ScrollbarStyledList component={Box} disablePadding>
            {queues &&
              queues.map((value, index) => (
                <ListItemButton
                  key={index}
                  component={Link}
                  to={`/queue/${value.id}`}
                  sx={{
                    minHeight: 48,
                    px: 2.5,
                    ml: open && index !== 0 ? 2.6 : 'auto',
                    backgroundColor: isActive(value.name) ? 'sideBarClickColor' : 'inherit',
                    '&:hover': {
                      backgroundColor: isActive(value.name) ? 'sideBarClickColor' : 'inherit',
                    },
                  }}
                >
                  {value.name}
                </ListItemButton>
              ))}
          </ScrollbarStyledList>
        </Collapse>
      </List>
    </Drawer>
  );
}

export default Sidebar;
