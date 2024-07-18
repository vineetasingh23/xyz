import React, { useState, useMemo } from 'react';
import { Box, ListItem, Tooltip, Collapse, Toolbar, List } from '@mui/material';
import { useLocation } from 'react-router-dom';
import { styled } from '@mui/material/styles';
import MuiDrawer from '@mui/material/Drawer';
import SearchInput from './SearchInput';  // Import the new SearchInput component

interface DrawerProps {
  open: boolean;
}

const SideBar: React.FC<DrawerProps> = ({ open }) => {
  const [drawerWidth, setDrawerWidth] = useState<number>(260);
  const minDrawerWidth = 260;
  const maxDrawerWidth = 500;
  const [queuesOpen, setQueuesOpen] = useState(true);
  const temp = useLocation().pathname.split('/');
  const currentPath = decodeURI(temp[temp.length - 1]);
  const [queues, setQueues] = useState([]);
  const [searchInput, setSearchInput] = useState('');

  const handleSearchChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setSearchInput(event.target.value);
  };

  const filteredQueues = useMemo(() => {
    return queues?.filter(queue => queue.name.toLowerCase().includes(searchInput.toLowerCase()));
  }, [queues, searchInput]);

  return (
    <Box sx={{ display: 'flex' }}>
      <Drawer
        variant="permanent"
        open={open}
        PaperProps={{
          sx: {
            width: open ? drawerWidth : minDrawerWidth,
            transition: 'width 0.3s',
          }
        }}
      >
        <Toolbar variant="dense" />
        <List>
          <ListItem disablePadding sx={{ display: 'block' }}>
            <Tooltip title="Template" placement="right">
              {/* Tooltip Content */}
            </Tooltip>
            <Tooltip title="Outbox" placement="right">
              {/* Tooltip Content */}
            </Tooltip>
            <Tooltip title="Sent Items" placement="right">
              {/* Tooltip Content */}
            </Tooltip>
            <Tooltip title="Common Bin" placement="right">
              {/* Tooltip Content */}
            </Tooltip>
            <Tooltip title="Linked Queue" placement="right">
              {/* Tooltip Content */}
            </Tooltip>
            {open && queuesOpen && queues?.length > 10 && (
              <ListItem disablePadding sx={{ display: 'flex', justifyContent: 'center' }}>
                <SearchInput searchInput={searchInput} handleSearchChange={handleSearchChange} />
              </ListItem>
            )}
          </ListItem>
        </List>
        <Collapse in={queuesOpen}>
          <Box
            sx={{
              height: '55vh',
              marginRight: '0px',
              overflowY: open ? 'auto' : 'hidden',
              overflowX: 'hidden',
              '&::-webkit-scrollbar': {
                width: '14px',
              },
              '&::-webkit-scrollbar-track': {
                background: '#f1f1f1',
              },
              '&::-webkit-scrollbar-thumb': {
                background: '#888',
              },
              '&::-webkit-scrollbar-thumb:hover': {
                background: '#555',
              },
            }}
          >
            {/* Render filteredQueues */}
          </Box>
        </Collapse>
      </Drawer>
    </Box>
  );
};

export default SideBar;
