// SnackbarComponent.jsx
import React from 'react';
import Snackbar from '@mui/material/Snackbar';
import MuiAlert from '@mui/material/Alert';
import Slide from '@mui/material/Slide';

const Alert = React.forwardRef(function Alert(props, ref) {
  return <MuiAlert elevation={6} ref={ref} variant="filled" {...props} />;
});

function SlideTransition(props) {
  return <Slide {...props} direction="up" />;
}

const SnackbarComponent = ({
  snackbarOpen,
  snackbarMessage,
  snackbarSeverity = 'info',
  autoHideDuration = 3000,
  handleCloseSnackbar,
  anchorOrigin = { vertical: 'top', horizontal: 'center' }
}) => {
  return (
    <Snackbar
      open={snackbarOpen}
      autoHideDuration={autoHideDuration}
      onClose={handleCloseSnackbar}
      TransitionComponent={SlideTransition}
      anchorOrigin={anchorOrigin}
    >
      <Alert onClose={handleCloseSnackbar} severity={snackbarSeverity} sx={{ width: '100%' }}>
        {snackbarMessage}
      </Alert>
    </Snackbar>
  );
};

export default SnackbarComponent;
