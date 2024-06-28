 const getCreateRules = async () => {
    try {
      const url = ApiEndPoints.ROOT + ApiEndPoints.STATIC_RULES;
      const axiosConfig: AxiosRequestConfig = {
        headers: {
          "Access-Control-Allow-Origin": "*",
          "Content-Type": "application/json",
        },
      };
      const response = await ApiConfig.getCall(url, axiosConfig);
      setRulesList(response.data);
      setConditionParams(response.data.conditionParams);
      setOperators(response.data.operators);
      console.log(response);
    } catch (error) {
      console.error("Error fetching create rules table:", error);
    }
  };
