import React, { useState } from 'react';
import Editor from '../Editor'; // Import the Editor component
import './CreateRequest.css'; // Import your custom CSS file

const CreateRequest: React.FC = () => {
  const [from, setFrom] = useState('');
  const [subject, setSubject] = useState('');
  const [classification, setClassification] = useState('');
  const [editorHtml, setEditorHtml] = useState('');
  const [files, setFiles] = useState<File[]>([]); // State for file attachments

  const handleFromChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setFrom(e.target.value);
  };

  const handleSubjectChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setSubject(e.target.value);
  };

  const handleClassificationChange = (e: React.ChangeEvent<HTMLSelectElement>) => {
    setClassification(e.target.value);
  };

  const handleEditorChange = (html: string) => {
    setEditorHtml(html); // Update editorHtml state when editor content changes
  };

  const handleUploadFiles = (files: FileList | null) => {
    if (files) {
      const fileList = Array.from(files);
      setFiles(fileList);
    }
  };

  const handleRemoveFiles = () => {
    setFiles([]); // Clear the file attachments
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
        <select className="select" value={classification} onChange={handleClassificationChange}>
          <option value="" disabled hidden>Select Classification</option>
          <option value="internal">Internal</option>
          <option value="external">External</option>
          <option value="confidential">Strictly Confidential</option>
        </select>
      </div>
      <div className="form-group">
        {/* Upload Button */}
        <button style={{ marginTop: '10px', position: 'relative' }} className="btn btn-default btn-sm pull-right" onClick={() => handleUploadFiles()} multiple>
          <span className="glyphicon glyphicon-upload"></span> Upload
        </button>
        {/* Remove Button */}
        <button style={{ marginTop: '10px', marginRight: '10px', position: 'relative' }} className="btn btn-default btn-sm pull-right" onClick={() => handleRemoveFiles()}>
          <span className="glyphicon glyphicon-remove"></span> Remove All
        </button>
      </div>
      <div className="form-group">
        <label className="message-heading">Message:</label>
        <Editor initialValue={editorHtml} onChange={handleEditorChange} /> {/* Use initialValue prop */}
      </div>
      {/* Attachments Section */}
      {files.length > 0 && (
        <div className="attach-cls">
          <span className="attach-heading">Attachments</span>
          <div className="attachmentdiv" style={{ width: '120%', marginLeft: '65px' }}>
            <table className="table">
              <thead>
                <tr>
                  <th>File name</th>
                  <th>File Size (bytes)</th>
                </tr>
              </thead>
              <tbody>
                {files.map((file, index) => (
                  <tr key={index}>
                    <td>
                      <button type="button" className="btn btn-secondary outline" onClick={() => downloadLink(file)}>
                        <span className="fa fa-file"></span> {file.name}
                      </button>
                    </td>
                    <td>{file.size}</td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        </div>
      )}
    </div>
  );
};

export default CreateRequest;
