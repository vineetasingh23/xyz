const handleGenerate = async () => {
  setIsLoading(true);

  try {
    const selectedMail = mailGroup?.find(m => m.mailGroup === selectedMailGroup);
    const mailGroupID = selectedMail ? selectedMail.regionId : "1";

    const selectedQueue = queue?.find(q => q.name === selectedQueue);
    const queueId = selectedQueue ? selectedQueue.queueId : null;

    const requestsPayload = {
      mailGroup: mailGroupID,
      receivedAfter: startDate.format("DD-MMM-YYYY 00:00:00"),
      receivedBefore: endDate.format("DD-MMM-YYYY 00:00:00"),
      selectedQueue: queueId,
    };

    const axiosConfig = {
      headers: {
        "Access-Control-Allow-Origin": "*",
        "Content-Type": "application/json;charset=UTF-8",
        "ct-remote-user": userDetails.emailid,
      },
      responseType: 'blob',
    };

    const url = ApiEndPoints.ROOT + ApiEndPoints.GENERATE_REPORT;
    const apiResponse = await ApiConfig.postCall(url, requestsPayload, axiosConfig);

    const blob = new Blob([apiResponse.data], { type: 'application/pdf' });
    const link = document.createElement('a');
    link.href = window.URL.createObjectURL(blob);
    link.download = 'report.pdf';
    document.body.appendChild(link);
    link.click();
    document.body.removeChild(link);
    
  } catch (error) {
    setSnackbarMessage("Error fetching the report");
    setSnackbarSeverity("error");
    setSnackbarOpen(true);
  } finally {
    setIsLoading(false);
  }
};
