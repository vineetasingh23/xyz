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
