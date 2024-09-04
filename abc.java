const handleSave = async () => {
  const payload = processForm.items.reduce((acc, item) => {
    acc[item.emailMgmtMapping] = formState[item.fieldName];
    return acc;
  }, {});

  try {
    const url = `${ApiEndPoints.ROOT}${ApiEndPoints.REQUEST}/${ApiEndPoints.PROCESS_FORM}`;
    const axiosConfig: AxiosRequestConfig = {
      headers: {
        "Access-Control-Allow-Origin": "*",
        "Content-Type": "application/json",
        "ct-remote-user": userDetails.emailId,
      },
    };

    const response: AxiosResponse = await ApiConfig.put(url, payload, axiosConfig);
    setSnackbarMessage("Process form saved successfully!");
    setSnackbarSeverity("success");
    setSnackbarOpen(true);
  } catch (error) {
    setSnackbarMessage("Error saving Process Forms");
    setSnackbarSeverity("error");
    setSnackbarOpen(true);
  }
};
