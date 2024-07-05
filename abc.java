 const [actionValue, setActionValue] = useState("");
  const [createdBy, setCreatedBy] = useState("vineeta-a.singhadb.com");
  const [mailGroup, setMailGroup] = useState("999996");
  const [rulePriority, setRulePriority] = useState(1);


 Create Actions
        </Box>
        <Box
          display="flex"
          sx={{
            marginBottom: "10px",
            paddingLeft: "15px",
            paddingRight: "15px",
            flexDirection: "row",
          }}
        >
          <FormControl fullWidth sx={{ marginRight: "20px" }}>
            <InputLabel size="small" id="actions">
              Action
            </InputLabel>
            <Select
              size="small"
              labelId="actions"
              id="actions"
              value={action}
              label="Action"
              onChange={handleActionChange}
            >
              {response?.actionParams.map((value) => (
                <MenuItem key={value.actionValue} value={value.actionValue}>
                  {value.actionValue}
                </MenuItem>
              ))}
            </Select>
          </FormControl>
        </Box>
        {action === "Set Deadline as" && (
          <FormControl fullWidth sx={{ marginRight: "20px" }} disabled={addedAction}>
            <DateTimeDeadLinePicker dateTime={true} />
          </FormControl>
        )}
        {action === "Auto Forward to" && (
          <TextField
            sx={{ marginTop: "8px" }}
            size="small"
            id="Email"
            label="Email"
            value={email}
            variant="outlined"
            onChange={handleEmailInput}
            fullWidth
            disabled={addedAction}
          />
        )}
        {action === "Set Offset Deadline as" && (
          <TimerInput disabled={addedAction} />
        )}
        <Box
          display="flex"
          sx={{
            marginBottom: "10px",
            paddingLeft: "15px",
            paddingRight: "15px",
            flexDirection: "row",
            justifyContent: "start",
          }}
        >
          <StyledButton
            variant="contained"
            disabled={addedAction}
            onClick={handleAddAction}
            sx={{ width: "25%", backgroundColor: "darkAction" }}
          >
            Add Action
          </StyledButton>
          <StyledButton
            variant="contained"
            size="small"
            disabled={!addedAction}
            onClick={handleANDAction}
            sx={{ backgroundColor: "darkBlue" }}
          >
            AND
          </StyledButton>
        </Box>
        <Box
          width="100%"
          display="flex"
          sx={{
            marginBottom: "10px",
            paddingLeft: "15px",
            paddingRight: "15px",
            flexDirection: "row",
          }}
        >
          <TextField
            disabled
            value={actionText}
            multiline
            rows={2}
            variant="outlined"
            style={{ width: "100%" }}
          />
        </Box>
        <Box
          display="flex"
          sx={{
            marginTop: "10px",
            flexDirection: "row",
            justifyContent: "end",
          }}
        >
          <StyledButton
            variant="contained"
            sx={{ marginRight: "1%", width: "10%", backgroundColor: "darkBlue" }}
          >
            Save
          </StyledButton>
          <StyledButton
            variant="contained"
            sx={{ marginRight: "1%", width: "10%", backgroundColor: "darkBlue" }}
            onClick={handleReset}
          >
            Reset
          </StyledButton>
        </Box>
      </Box>
    </Paper>
  );
};

export default CreateRules;







  const handleSubmit = async () => {
    const payload = {
      conditionParams: [
        {
          condition: condition,
          operator: operators,
          value: conditionOperand
        }
      ],
      actionParams: [
        {
          parameter: action,
          actionValue: actionValue
        }
      ],
      createdBy: createdBy,
      mailGroup: mailGroup,
      rulePriority: rulePriority
    };

    try {
      const response = await ApiConfig.postCall("/your-api-endpoint", payload);
      console.log("Response:", response.data);
    } catch (error) {
      console.error("Error:", error);
    }
  };
