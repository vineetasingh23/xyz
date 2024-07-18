{open && queuesOpen && queues?.length > 10 && (
              <ListItem disablePadding sx={{ display: 'flex', justifyContent: 'center' }}>
                <Box sx={{ width: '80%', maxWidth: 400 }}>
                  <SearchInput searchInput={searchInput} handleSearchChange={handleSearchChange} />
                </Box>
              </ListItem>
            )}
          </ListItem>
        </List>
