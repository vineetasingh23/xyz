<div className="form-group" style={{ display: 'flex', justifyContent: 'center' }}>
  {/* Upload Button */}
  <Button
    variant="contained"
    sx={{
      borderRadius: '10px',
      textTransform: 'none',
      marginBottom: 1,
      marginTop: 1,
      backgroundColor: 'rgba(183, 216, 255, 0.15)',
      height: 25,
      fontSize: '11px',
      color: '#000000',
      marginLeft: 'auto',
      marginRight: 1,
      boxShadow: 30,
      ':hover': {
        boxShadow: 30,
        backgroundColor: 'rgba(183, 216, 255, 0.9)',
      },
    }}
    onClick={handleUploadFiles}
  >
    <AttachmentOutlinedIcon sx={{ height: 20, width: 20, alignSelf: 'center' }} />
    <span> Upload</span>
  </Button>
  <Button
    variant="contained"
    sx={{
      borderRadius: '10px',
      textTransform: 'none',
      marginBottom: 1,
      marginTop: 1,
      backgroundColor: 'rgba(183, 216, 255, 0.15)',
      height: 25,
      fontSize: '11px',
      color: '#000000',
      ':hover': {
        boxShadow: 30,
        backgroundColor: 'rgba(183, 216, 255, 0.9)',
      },
    }}
    onClick={handleRemoveFiles}
  >
    <ClearIcon sx={{ height: 20, width: 20, alignSelf: 'center', marginRight: 1 }} />
    <span> Remove All</span>
  </Button>
</div>
