// Import necessary modules and styles
import React, { useEffect, useRef } from 'react';
import Quill from 'quill';
import 'quill/dist/quill.snow.css';
import './editor.css'; // Import custom CSS file

// Custom clipboard handler
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

// Quill Editor component
function Editor() {
  const editorRef = useRef(null);

  useEffect(() => {
    if (editorRef.current) {
      new Quill(editorRef.current, {
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
  }, []);

  return <div ref={editorRef} />;
}

export default Editor;
