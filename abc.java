

          {queues?.length > 7 && (
          <ListItem disablePadding sx={{ display: "block" }}>
            <TextField
              variant="outlined"
              placeholder="Search Queues"
              value={searchInput}
              onChange={handleSearchChange}
              fullWidth
              InputProps={{
                startAdornment: (
                  <InputAdornment position="start">
                    <SearchIcon style={{ color: 'white' }} />
                  </InputAdornment>
                ),
                style: { color: 'white' },
              }}
              sx={{
                '& .MuiOutlinedInput-root': {
                  '& fieldset': {
                    borderColor: 'white',
                  },
                  '&:hover fieldset': {
                    borderColor: 'white',
                  },
                  '&.Mui-focused fieldset': {
                    borderColor: 'white',
                  },
                },
                '& .MuiInputLabel-root': {
                  color: 'white',
                },
                '& .MuiInputLabel-root.Mui-focused': {
                  color: 'white',
                },
              }}
            />
          </ListItem>
        )}




              

        <Collapse in={queuesOpen} timeout="auto" unmountOnExit>
          <Divider />
          <List
            sx={{
              overflowY: "auto",
              overflowX: "hidden",
              flexGrow: 1,
            }}
            component={Box}
            disablePadding
          >
            {filteredQueues && filteredQueues.map((value, index) => (
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
