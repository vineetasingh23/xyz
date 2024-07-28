  useEffect(() => {
    // Restore the scroll position after the component updates
    if (scrollContainerRef.current) {
      scrollContainerRef.current.scrollTop = scrollPositionRef.current;
    }
  });

  const handleScroll = () => {
    if (scrollContainerRef.current) {
      scrollPositionRef.current = scrollContainerRef.current.scrollTop;
    }
  };


  const handleClick = (event: React.MouseEvent<HTMLDivElement, MouseEvent>, queueName: string) => {
    event.preventDefault();
    // Save the scroll position before the state update
    if (scrollContainerRef.current) {
      scrollPositionRef.current = scrollContainerRef.current.scrollTop;
    }
    console.log(queueName);
  };


 const scrollContainerRef = useRef<HTMLDivElement>(null);
  const scrollPositionRef = useRef<number>(0);
