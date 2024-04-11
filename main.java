.select {
  border: 1px solid #ccc;
  border-radius: 5px;
  padding: 8px;
  width: 200px; /* Adjust width as needed */
  font-size: 14px;
}

.select option {
  background-color: #fff; /* Background color of options */
  color: #000; /* Text color of options */
}

/* Style for the selected option */
.select option:checked {
  background-color: #007bff; /* Background color when option is selected */
  color: #fff; /* Text color when option is selected */
}




<div className="select-wrapper" style={{ marginLeft: 7 }}>
  <select className="select" value={from} onChange={handleFromChange}>
    <option value="" hidden>Select</option>
    <option value="Demo 1">Demo 1</option>
    <option value="Demo 2">Demo 2</option>
    <option value="Demo 3">Demo 3</option>
    <option value="Demo 4">Demo 4</option>
  </select>
</div>
<div className="form-group input-wrapper">
  <label className="label" style={{ fontSize: '15px', fontWeight: 'bold', marginRight: 7 }}>Subject:</label>
  <input type="text" value={subject} onChange={handleSubjectChange} />
</div>
<div className="select-wrapper">
  <select className="select" value={classification} onChange={handleClassificationChange}>
    <option value="" hidden>Select classification</option>
    <option value="internal">Internal</option>
    <option value="public">Public</option>
    <option value="confidential">Confidential</option>
    <option value="external_communication">External Communication</option>
    <option value="strictly_confidential">Strictly Confidential</option>
  </select>
</div>
