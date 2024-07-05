      setConditions([...conditions, { condition, operator: operators, value: conditionOperand }]);
      setActions([...actions, { parameter: response.actionParams.findIndex((a) => a.actionValue === action), actionValue: action }]);
  const handleSave = async () => {
    const payload = {
      actionParams: actions,
      conditionParams: conditions,
      createdBy,
      mailGroup,
      rulePriority,
      toxicActions
    };

    try {
      const url = `${ApiEndPoints.ROOT}${ApiEndPoints.SAVE_RULE}`;
      const axiosConfig = {
        headers: {
          "Access-Control-Allow-Origin": "*",
          "Content-Type": "application/json",
        },
      };
      await ApiConfig.post(url, payload, axiosConfig);
      alert("Rule saved successfully!");
    } catch (error) {
      console.error("Error saving rule:", error);
    }
  };
