import React, { useState, useEffect } from 'react';
import ReactQuill from 'react-quill';
import 'react-quill/dist/quill.snow.css'; // or another Quill theme
import 'quill-table-ui/dist/quill.table.css'; // Quill Table UI styles
import { Table } from 'quill-table-ui';
import Quill from 'quill';

const EditorWithTable = () => {
  const [editorHtml, setEditorHtml] = useState('');

  useEffect(() => {
    // Register Quill modules
    const table = Quill.import('modules/table');
    Quill.register('modules/table', table, true);
  }, []);

  const modules = {
    toolbar: [
      [{ 'header': [1, 2, 3, false] }],
      ['bold', 'italic', 'underline', 'strike'],
      [{ 'list': 'ordered' }, { 'list': 'bullet' }],
      [{ 'align': [] }],
      ['link', 'image'],
      ['table'],
      ['clean']
    ],
    table: true // Enable table module
  };

  const formats = [
    'header',
    'bold', 'italic', 'underline', 'strike',
    'list', 'bullet',
    'align',
    'link', 'image',
    'table'
  ];

  const handleEditorChange = (content) => {
    setEditorHtml(content);
  };

  return (
    <div>
      <h1>React Quill Editor with Table</h1>
      <ReactQuill
        theme="snow" // or use another Quill theme
        modules={modules}
        formats={formats}
        value={editorHtml}
        onChange={handleEditorChange}
      />
      <Table />
    </div>
  );
};

export default EditorWithTable;
import React, { useState } from 'react';
import ReactQuill, { Quill } from 'react-quill';
import 'react-quill/dist/quill.snow.css'; // or another Quill theme
import 'quill-table-ui/dist/index.css'; // Quill Table UI styles
import { Table } from 'quill-table-ui';

const EditorWithTable = () => {
  const [editorHtml, setEditorHtml] = useState('');

  const modules = {
    toolbar: [
      [{ 'header': [1, 2, 3, false] }],
      ['bold', 'italic', 'underline', 'strike'],
      [{ 'list': 'ordered' }, { 'list': 'bullet' }],
      [{ 'align': [] }],
      ['link', 'image'],
      ['table'],
      ['clean']
    ],
    table: true // Enable table module
  };

  const formats = [
    'header',
    'bold', 'italic', 'underline', 'strike',
    'list', 'bullet',
    'align',
    'link', 'image',
    'table'
  ];

  const handleEditorChange = (content) => {
    setEditorHtml(content);
  };

  return (
    <div>
      <h1>React Quill Editor with Table</h1>
      <ReactQuill
        theme="snow" // or use another Quill theme
        modules={modules}
        formats={formats}
        value={editorHtml}
        onChange={handleEditorChange}
      />
      <Table />
    </div>
  );
};

export default EditorWithTable;
import React, { useEffect, useRef } from 'react';
import Quill from 'quill';
import 'quill/dist/quill.snow.css';
import './editor.css'; // Import custom CSS file

const CustomClipboard = Quill.import('modules/clipboard');
const Delta = Quill.import('delta');

class PlainClipboard extends CustomClipboard {
  convert(html = null) {
    if (typeof html === 'string') {
      this.container.innerHTML = html;
    }
    let text = this.container.innerText;
    this.container.innerHTML = '';
    return new Delta().insert(text);
  }

  convertTable(html) {
    const tableRegex = /<table[^>]*>((.|[\n\r])*?)<\/table>/g;
    const matches = html.match(tableRegex);
    if (matches) {
      const delta = new Delta();
      matches.forEach((match) => {
        const div = document.createElement('div');
        div.innerHTML = match;
        const table = div.firstChild;
        const rows = table.rows;
        for (let i = 0; i < rows.length; i++) {
          const cells = rows[i].cells;
          for (let j = 0; j < cells.length; j++) {
            const cellText = cells[j].innerText;
            delta.insert(cellText);
            if (j < cells.length - 1) {
              delta.insert('\t');
            }
          }
          delta.insert('\n');
        }
      });
      return delta;
    }
    return null;
  }

  paste() {
    const range = this.quill.getSelection(true);
    const text = this.quill.clipboard.convert().ops[0].insert;
    const delta = new Delta().retain(range.index).delete(range.length).insert(text);
    this.quill.updateContents(delta, 'user');
    this.quill.setSelection(delta.length(), 'silent');
  }
}

Quill.register('modules/clipboard', PlainClipboard, true);

function Editor() {
  const editorRef = useRef(null);
  let quill = null;

  useEffect(() => {
    if (editorRef.current) {
      quill = new Quill(editorRef.current, {
        theme: 'snow',
        modules: {
          toolbar: [
            [{ 'header': [1, 2, 3, 4, 5, 6, false] }],
            ['bold', 'italic', 'underline', 'strike'],
            ['blockquote', 'code-block'],
            [{ 'list': 'ordered' }, { 'list': 'bullet' }],
            [{ 'script': 'sub' }, { 'script': 'super' }],
            [{ 'indent': '-1' }, { 'indent': '+1' }],
            [{ 'direction': 'rtl' }],
            [{ 'size': ['small', false, 'large', 'huge'] }],
            [{ 'color': [] }, { 'background': [] }],
            ['link', 'image', 'video'],
            ['clean'],
          ],
          clipboard: {
            matchers: [['table', (node, delta) => this.convertTable(node.innerHTML)]],
          },
        },
      });
    }
    return () => {
      if (quill) {
        quill.deleteAt(0, quill.getLength());
        quill = null;
      }
    };
  }, []);

  return <div ref={editorRef} />;
}

export default Editor;
