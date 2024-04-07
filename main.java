import React, { useState } from 'react';
import ReactQuill from 'react-quill';
import 'react-quill/dist/quill.snow.css';
import './Editor.css';

function Editor() {
  const [value, setValue] = useState('');

  console.log(value);

  const handlePaste = (e) => {
    e.preventDefault();
    const clipboardData = e.clipboardData || window.clipboardData;
    const pastedData = clipboardData.getData('text/plain');

    // Example: parse pasted data to identify and format tables
    const formattedData = formatPastedData(pastedData);

    const newValue = value + formattedData;
    setValue(newValue);
  };

  const formatPastedData = (data) => {
    // Example: Parse and format data as tables (specific to your needs)
    // This is a simplified example, you may need to adjust this based on your data format
    const lines = data.split('\n');
    const rows = lines.map((line) => `<tr><td>${line.split('\t').join('</td><td>')}</td></tr>`);
    const table = `<table>${rows.join('')}</table>`;
    return table;
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
