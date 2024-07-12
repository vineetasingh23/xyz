const [searchInput, setSearchInput] = useState('');
  const [showSearchResults, setShowSearchResults] = useState(false);

  const searchFieldRef = useRef(null);

  // Filtered queues based on search input
  const filteredQueues = useMemo(() => {
    if (!showSearchResults) return queues;  // Show all queues if search results are hidden
    return queues?.filter((queue) =>
      queue.name.toLowerCase().includes(searchInput.toLowerCase())
    );
  }, [queues, searchInput, showSearchResults]);

  // Debounced function for handling search input change
  const debouncedSearch = useCallback(
    debounce((input) => {
      setSearchInput(input);
      setShowSearchResults(true);  // Show search results when input changes
    }, 2000), // 2 seconds debounce delay
    []
  );

  const handleSearchChange = (event) => {
    debouncedSearch(event.target.value); // Use debounced function
  };

  useEffect(() => {
    if (searchFieldRef.current) {
      searchFieldRef.current.focus();
    }
  }, []);
