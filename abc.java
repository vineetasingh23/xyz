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



Download the Ingestion Controller Endpoints Collection:
Obtain the Ingestion Controller Endpoints Collection file and save it to your local system.
Import the Collection into Postman:
Open Postman and utilize the Import functionality to upload the downloaded collection file.
Download CA 13 Certificates:
Follow the detailed instructions provided in the TEST CA 13 Certificate Installation document on Confluence to download the CA 13 certificates.
Configure CA Certificates in Postman:
Navigate to the Settings section in Postman.
Enable the CA Certificates option.
Add the Group Root TEST CA13 certificate.
Test the APIs:
You are now ready to test the APIs using the configured settings.





