import React, { useState } from 'react';
import ReactQuill from 'react-quill';
import 'react-quill/dist/quill.snow.css';

const EmailComposer = () => {
  const [from, setFrom] = useState('');
  const [subject, setSubject] = useState('');
  const [classification, setClassification] = useState('internal');
  const [editorHtml, setEditorHtml] = useState('');

  const handleFromChange = (e) => {
    setFrom(e.target.value);
  };

  const handleSubjectChange = (e) => {
    setSubject(e.target.value);
  };

  const handleClassificationChange = (e) => {
    setClassification(e.target.value);
  };

  const handleEditorChange = (html) => {
    setEditorHtml(html);
  };

  return (
    <div>
      <div>
        <label>From:</label>
        <input type="text" value={from} onChange={handleFromChange} />
      </div>
      <div>
        <label>Subject:</label>
        <input type="text" value={subject} onChange={handleSubjectChange} />
        <select value={classification} onChange={handleClassificationChange}>
          <option value="internal">Internal</option>
          <option value="public">Public Use</option>
          <option value="confidential">Confidential</option>
          <option value="urgent">Urgent</option>
        </select>
      </div>
      <div>
        <label>Message:</label>
        <ReactQuill theme="snow" value={editorHtml} onChange={handleEditorChange} />
      </div>
    </div>
  );
};

export default EmailComposer;
