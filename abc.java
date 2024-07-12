 {queues?.length > 7 && (
          <ListItem disablePadding sx={{ display: "flex", justifyContent: "center" }}>
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
                style: { color: 'white', height: '30px' }, // Adjust the height here
              }}
              sx={{
                width: '70%',
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
                  '& input': {
                    padding: '8px 14px', // Adjust the padding to reduce height
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

        <Collapse in={queuesOpen} timeout="auto" unmountOnExit sx={{ margin: "15px" }}>
          <Box sx={{ height: "55vh", marginRight: "15px", overflowY: "auto", overflowX: "hidden" }}>
            <List disablePadding>
              {filteredQueues?.map((value, index) => (
                <ListItemButton
                  key={index}
                  component={Link}
                  to={`/queue/${value.id}`}
                  sx={{
                    minHeight: 48,
                    paddingX: 2.5,
                    marginLeft: open && index !== 0 ? 2.6 : 'auto',
                    backgroundColor: isActive(value.name) ? "sideBarClickColor" : "inherit",
                    "&:hover": {
                      backgroundColor: isActive(value.name) ? "sideBarClickColor" : "inherit",
                    },
                    "&:focus": {
                      backgroundColor: "sideBarClickColor",
                    },
                  }}
                >
                  <ListItemText
                    primary={value.name}
                    sx={{ opacity: open ? 1 : 0, color: "EMSWhite" }}
                  />
                </ListItemButton>
              ))}
            </List>
          </Box>
        </Collapse>
      </List>
    </Drawer>
  );
}




                    const [searchInput, setSearchInput] = useState('');

  const handleSearchChange = (event) => {
    setSearchInput(event.target.value);
  };

  const filteredQueues = useMemo(
    () =>
      queues?.filter((queue) =>
        queue.name.toLowerCase().includes(searchInput.toLowerCase())
      ),
    [queues, searchInput]
  );
