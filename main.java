.email-form {
  margin: 20px;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 5px;
}

.form-group {
  margin-bottom: 10px;
}

.label {
  display: block;
  margin-bottom: 5px;
}

.select-wrapper {
  position: relative;
}

.select-wrapper::after {
  content: '\25BE';
  position: absolute;
  top: 50%;
  right: 10px;
  transform: translateY(-50%);
}

.select {
  width: 100%;
  padding: 8px;
  border: 1px solid #ccc;
  border-radius: 5px;
}


import React, { useState } from 'react';
import Editor from '../Editor';
import './CreateRequest.css'; // Import the CSS file

const CreateRequest = () => {
  const [from, setFrom] = useState('');
  const [subject, setSubject] = useState('');
  const [classification, setClassification] = useState('classify'); // Default value set to "Classify Message"
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
    <div className="email-form">
      <div className="form-group">
        <label className="label">From:</label>
        <input type="text" value={from} onChange={handleFromChange} />
      </div>
      <div className="form-group">
        <label className="label">Subject:</label>
        <input type="text" value={subject} onChange={handleSubjectChange} />
      </div>
      <div className="form-group">
        <label className="label">Classify Message:</label>
        <div className="select-wrapper">
          <select className="select" value={classification} onChange={handleClassificationChange}>
            <option value="classify">Classify Message</option>
            <option value="internal">Internal</option>
            <option value="external">External</option>
            <option value="confidential">Strictly Confidential</option>
          </select>
        </div>
      </div>
      <div className="form-group">
        <label className="label">Message:</label>
        <Editor value={editorHtml} onChange={handleEditorChange} />
      </div>
    </div>
  );
};

export default CreateRequest;

