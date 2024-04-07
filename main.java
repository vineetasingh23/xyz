import React, { useState, useEffect } from 'react';
import ReactQuill from 'react-quill';
import 'react-quill/dist/quill.snow.css';
import 'quill-better-table/dist/quill-better-table.css'; // Import Quill Better Table styles
import { Quill } from 'react-quill'; // Import Quill instance
import QuillBetterTable from 'quill-better-table'; // Import Quill Better Table module

// Register Quill Better Table module
Quill.register('modules/better-table', QuillBetterTable);

function Editor() {
  const [value, setValue] = useState('');
  let quillRef;

  useEffect(() => {
    if (quillRef) {
      // Initialize Quill with Better Table module
      quillRef.getEditor().enable(false); // Disable the editor temporarily
      quillRef.getEditor().addModule('better-table', true); // Add the Better Table module
      quillRef.getEditor().enable(true); // Re-enable the editor
    }
  }, [quillRef]);

  const modules = {
    toolbar: [
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
    ],
    better-table: true, // Enable Better Table module
  };

  return (
    <div className='Editor'>
      <ReactQuill
        ref={(el) => { quillRef = el; }}
        modules={modules}
        theme='snow'
        value={value}
        onChange={setValue}
      />
    </div>
  );
}

export default Editor;
