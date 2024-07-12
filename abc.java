  const filteredQueues = useMemo(
    () =>
      queues?.filter((queue) =>
        queue.name.toLowerCase().includes(searchInput.toLowerCase())
      ),
    [queues, searchInput]
  );

  // Focus the search field on render
  React.useEffect(() => {
    if (searchFieldRef.current) {
      searchFieldRef.current.focus();
    }
  }, []);




  sx={{
    height: "55vh",
    marginRight: "15px",
    overflowY: "auto",
    overflowX: "hidden",
    '&::-webkit-scrollbar': {
      width: '8px', /* Width of the scrollbar */
    },
    '&::-webkit-scrollbar-track': {
      background: '#1d1d1d', /* Track background color */
    },
    '&::-webkit-scrollbar-thumb': {
      background: '#888', /* Scrollbar thumb color */
      borderRadius: '10px', /* Thumb border radius */
    },
    '&::-webkit-scrollbar-thumb:hover': {
      background: '#555', /* Scrollbar thumb color on hover */
    },
  }}
>
