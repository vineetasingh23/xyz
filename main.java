import React, { useState } from 'react';
import styled from 'styled-components';
import Button from '@mui/material/Button';
import CloudUploadIcon from '@mui/icons-material/CloudUpload';
import ClearIcon from '@mui/icons-material/Clear';
import DeleteIcon from '@mui/icons-material/Delete';
import IconButton from '@mui/material/IconButton';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';

// Styled component to hide the input element visually
const VisuallyHiddenInput = styled.input`
  clip: rect(0 0 0 0);
  clip-path: inset(50%);
  height: 1px;
  overflow: hidden;
  position: absolute;
  bottom: 0;
  left: 0;
  white-space: nowrap;
  width: 1px;
`;

const FileUploadComponent: React.FC = () => {
  const [fileList, setFileList] = useState<File[]>([]);

  const handleFileChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    if (e.target.files) {
      setFileList((prevFiles) => [...prevFiles, ...Array.from(e.target.files)]);
    }
  };

  const handleRemoveAllFiles = () => {
    setFileList([]);
  };

  const handleUploadClick = () => {
    if (fileList.length === 0) {
      return;
    }
    const data = new FormData();
    fileList.forEach((file, i) => {
      data.append(`file-${i}`, file, file.name);
    });

    fetch("https://httpbin.org/post", {
      method: "POST",
      body: data,
    })
      .then((res) => res.json())
      .then((data) => console.log(data))
      .catch((err) => console.error(err));
  };

const handleRemoveFiles = (index: number) => {
  if (fileList) {
    const updatedFileList = Array.from(fileList).filter((_, i) => i !== index);
    const dataTransfer = new DataTransfer();
    updatedFileList.forEach(file => dataTransfer.items.add(file));
    setFileList(dataTransfer.files);
  }
};

  return (
    <div>
      <Button
        component="label"
        role="undefined"
        variant="contained"
        tabIndex={-1}
        startIcon={<CloudUploadIcon />}
        sx={{
          borderRadius: "10px",
          textTransform: "none",
          marginBottom: 1,
          marginTop: 1,
          backgroundColor: "darkBlue",
          color: "white",
          height: "60%",
          fontSize: "12px",
          fontFamily: "inherit",
          marginRight: 1,
          boxShadow: 30,
          '&:hover': {
            boxShadow: 30,
            backgroundColor: "rgba(12, 35, 64, 0.88)",
            opacity: "0.9",
          },
        }}
        onClick={handleUploadClick}
      >
        Upload
        <VisuallyHiddenInput type="file" onChange={handleFileChange} multiple />
      </Button>

      {/* Remove All */}
      <Button
        variant="contained"
        sx={{
          borderRadius: "10px",
          textTransform: "none",
          marginBottom: 1,
          marginTop: 1,
          backgroundColor: "darkBlue",
          color: "white",
          height: "60%",
          fontSize: "12px",
          '&:hover': {
            boxShadow: 30,
            backgroundColor: "rgba(12, 35, 64, 0.88)",
            opacity: "0.9",
          },
        }}
        onClick={handleRemoveAllFiles}
      >
        <ClearIcon sx={{ height: 20, width: 20, alignSelf: "center", marginRight: 1 }} />
        Remove All
      </Button>

      {fileList.length > 0 && (
        <TableContainer component={Paper} sx={{ height: "90%", pl: "30px", pr: "30px", mb: '10px' }}>
          <Table stickyHeader size="small" aria-label="simple table">
            <TableHead sx={{ borderBottom: 2, borderColor: "gray" }}>
              <TableRow>
                <TableCell align="center" sx={{ fontWeight: "bold" }}>Name</TableCell>
                <TableCell align="center" sx={{ fontWeight: "bold" }}>Size</TableCell>
                <TableCell align="center" sx={{ fontWeight: "bold" }}>Type</TableCell>
                <TableCell align="center" sx={{ fontWeight: "bold" }}>Actions</TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {fileList.map((file, index) => (
                <TableRow key={index}>
                  <TableCell align="center">{file.name}</TableCell>
                  <TableCell align="center">{(file.size / 1024).toFixed(2)} KB</TableCell>
                  <TableCell align="center">{file.type}</TableCell>
                  <TableCell align="center">
                    <IconButton
                      size="small"
                      sx={{
                        p: "3px",
                        backgroundColor: "darkBlue",
                        color: "white",
                        '&:hover': {
                          p: "3px",
                          backgroundColor: "darkBlue",
                          color: "white",
                          opacity: '0.9',
                        },
                      }}
                      onClick={() => handleRemoveFile(index)}
                    >
                      <DeleteIcon fontSize="small" />
                    </IconButton>
                  </TableCell>
                </TableRow>
              ))}
            </TableBody>
          </Table>
        </TableContainer>
      )}
    </div>
  );
};

export default FileUploadComponent;
