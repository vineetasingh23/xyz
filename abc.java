<StyledTextField
            value={formState[item.fieldName] || ""}
            onChange={(e) => handleFieldChange(item.fieldName, e.target.value)}
            label={item.fieldName}
            defaultValue=""
            size="small"
            sx={{
              '& label': {
                color: theme.palette.mode === 'light' ? 'black' : 'white',
              },
              '& .MuiInputBase-input': {
                color: theme.palette.mode === 'light' ? 'black' : 'white',
              },
            }}
          />
