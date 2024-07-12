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




// App.jsx or any other parent component
import React, { useState } from 'react';
import SnackbarComponent from './SnackbarComponent';

function App() {
  const [snackbarOpen, setSnackbarOpen] = useState(false);
  const [snackbarMessage, setSnackbarMessage] = useState('');
  const [snackbarSeverity, setSnackbarSeverity] = useState('info');

  const handleOpenSnackbar = (message, severity) => {
    setSnackbarMessage(message);
    setSnackbarSeverity(severity);
    setSnackbarOpen(true);
  };

  const handleCloseSnackbar = (event, reason) => {
    if (reason === 'clickaway') {
      return;
    }
    setSnackbarOpen(false);
  };

  return (
    <div>
      <button onClick={() => handleOpenSnackbar('This is a success message!', 'success')}>
        Show Success Snackbar
      </button>
      <button onClick={() => handleOpenSnackbar('This is an error message!', 'error')}>
        Show Error Snackbar
      </button>
      <SnackbarComponent
        snackbarOpen={snackbarOpen}
        snackbarMessage={snackbarMessage}
        snackbarSeverity={snackbarSeverity}
        handleCloseSnackbar={handleCloseSnackbar}
      />
    </div>
  );
}

export default App;

