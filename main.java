const [snackbarOpen, setSnackbarOpen] = useState(false);
  const [snackbarMessage, setSnackbarMessage] = useState('');
  const [snackbarSeverity, setSnackbarSeverity] = useState('success');

  const handleDelete = (ruleid) => {
    // Replace this with your delete logic
    deleteRule(ruleid)
      .then(response => {
        setSnackbarMessage('Deleted successfully');
        setSnackbarSeverity('success');
        setSnackbarOpen(true);
      })
      .catch(error => {
        setSnackbarMessage('Failed to delete');
        setSnackbarSeverity('error');
        setSnackbarOpen(true);
      });
  };

  const handleCloseSnackbar = () => {
    setSnackbarOpen(false);
  };










   </TableBody>
      </Table>
      <Snackbar
        open={snackbarOpen}
        autoHideDuration={6000}
        onClose={handleCloseSnackbar}
      >
        <Alert onClose={handleCloseSnackbar} severity={snackbarSeverity} sx={{ width: '100%' }}>
          {snackbarMessage}
        </Alert>
      </Snackbar>
    </>
  );
};

export default YourComponent;
