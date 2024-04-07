import React, { useState } from 'react';
import ReactQuill from 'react-quill';
import 'react-quill/dist/quill.snow.css';
import './Editor.css';

function Editor() {
  const [value, setValue] = useState('');

  console.log(value);

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

  const modules = {
    toolbar: toolbarOptions,
    clipboard: {
      matchVisual: false, // Disables Quill's default HTML paste handler
      matchers: [
        [Node.ELEMENT_NODE, (node, delta) => {
          if (node.tagName && node.tagName.toLowerCase() === 'table') {
            const html = node.outerHTML;
            return { ops: [{ insert: html }] };
          }
          return delta;
        }],
      ],
    },
  };

  return (
    <div className='Editor'>
      <ReactQuill modules={modules} theme='snow' value={value} onChange={setValue} />
    </div>
  );
}

export default Editor;
