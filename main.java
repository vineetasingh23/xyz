import React, { useState } from 'react';
import ReactQuill from 'react-quill';
import 'react-quill/dist/quill.snow.css';
import './Editor.css';

function Editor() {
  const [value, setValue] = useState('');

  const handlePaste = (event) => {
    event.preventDefault();
    const clipboardData = event.clipboardData || window.clipboardData;
    const pastedData = clipboardData.getData('text/plain');

    // Check if the pasted data contains tabular structure
    if (isTabularData(pastedData)) {
      const formattedData = `<table>${pastedData}</table>`;
      document.execCommand('insertHTML', false, formattedData);
    } else {
      document.execCommand('insertText', false, pastedData);
    }
  };

  const isTabularData = (data) => {
    // Example check for tabular data based on tab separation
    return data.includes('\t');
  };

  const toolbarOptions = [
    [{ 'header': [1, 2, 3, 4, 5, 6, false] }],
    ['bold', 'italic', 'underline', 'strike'],
    ['blockquote', 'code-block'],
    [{ 'list': 'ordered' }, { 'list': 'bullet' }],
    [{ 'script': 'sub' }, { 'script': 'super' }],
    [{ 'direction': 'rtl' }],
    [{ 'size': ['small', false, 'large', 'huge'] }],
    [{ 'color': [] }, { 'background': [] }],
    [{ 'align': [] }],
    ['link', 'image', 'video'],
    ['clean'],
  ];

  return (
    <div className='Editor'>
      <ReactQuill
        modules={{ toolbar: toolbarOptions }}
        theme='snow'
        value={value}
        onChange={setValue}
        onPaste={handlePaste}
      />
    </div>
  );
}

export default Editor;
