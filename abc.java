const [disableAutoSave, setDisableAutoSave] = useState(false);

const handleButtonClick = () => {
  setDisableAutoSave(true);
  // Perform button-specific actions
  setTimeout(() => setDisableAutoSave(false), 5000); // Resume autosave after 5 seconds
};

useEffect(() => {
  if (!disableAutoSave) {
    const interval = setInterval(() => {
      saveDraft();
    }, 5000);
    return () => clearInterval(interval);
  }
}, [disableAutoSave]);
