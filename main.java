 const getRulesList = async () => {
    try {
      const url = ApiEndPoints.ROOT + ApiEndPoints.VIEW_RULES;
      const axiosConfig: AxiosRequestConfig = {
        headers: {
          "Access-Control-Allow-Origin": "*",
          "Content-Type": "application/json",
        }
      };
      const response = await ApiConfig.getCall(url, axiosConfig);
      setRulesList(response.data.items);  // Assuming the response data structure
      console.log(response);
    } catch (error) {
      console.error("Error fetching rules list:", error);
    }
  };
