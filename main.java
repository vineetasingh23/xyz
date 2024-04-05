.email-form {
  margin: 20px;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 5px;
  display: flex;
  flex-direction: column;
}

.form-group {
  margin-bottom: 10px;
}

.label {
  display: inline-block;
  width: 120px; /* Adjust width as needed */
  margin-bottom: 5px;
}

.input-wrapper {
  display: flex;
  align-items: center;
}

.input-wrapper input,
.select-wrapper select {
  flex: 1;
  padding: 8px;
  border: 1px solid #ccc;
  border-radius: 5px;
}

.editor-wrapper {
  flex: 1;
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
      <div className="form-group input-wrapper">
        <label className="label">From:</label>
        <input type="text" value={from} onChange={handleFromChange} />
      </div>
      <div className="form-group input-wrapper">
        <label className="label">Subject:</label>
        <input type="text" value={subject} onChange={handleSubjectChange} />
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
      <div className="form-group editor-wrapper">
        <label className="label">Message:</label>
        <Editor value={editorHtml} onChange={handleEditorChange} />
      </div>
    </div>
  );
};

export default CreateRequest;
