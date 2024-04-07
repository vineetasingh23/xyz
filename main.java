import React, { useRef, useEffect } from 'react';
import Quill from 'quill';
import 'quill/dist/quill.snow.css';

const Editor = () => {
  const quillRef = useRef(null);

  useEffect(() => {
    if (!quillRef.current) return;

    const quill = new Quill(quillRef.current, {
      theme: 'snow',
      modules: {
        toolbar: [
          [{ header: [1, 2, false] }],
          ['bold', 'italic', 'underline'],
          [{ list: 'ordered' }, { list: 'bullet' }],
          ['link', 'image'],
        ],
      },
    });

    quill.clipboard.addMatcher(Node.ELEMENT_NODE, (node, delta) => {
      // Check if the node is an HTML table
      if (node.tagName && node.tagName.toLowerCase() === 'table') {
        const html = node.outerHTML;
        delta.ops = [
          {
            insert: { table: html },
          },
        ];
      }

      return delta;
    });

    return () => {
      quill.off('text-change');
    };
  }, []);

  return <div ref={quillRef} style={{ height: '400px' }} />;
};

export default Editor;
