const selectedCondition = response.conditionParams.find(c => c.value === condition);
    const selectedOperator = response.operatorParams.find(o => o.value === operators);
    
    // Map the condition and operator values to the new condition object
    setConditions([
      ...conditions, 
      { 
        condition: selectedCondition,  // Here you can store the full object or just its value
        operator: selectedOperator,    // Here you can store the full object or just its value
        value: conditionOperand 
      }
    ]);


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
