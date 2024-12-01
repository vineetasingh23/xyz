import React, { useState, useEffect } from "react";

const EmailAttachmentHandler = ({ reqId, mailData }) => {
  const [includeAttachments, setIncludeAttachments] = useState(false);
  const [updatedEmailData, setUpdatedEmailData] = useState({ ...mailData });

  useEffect(() => {
    // Sync the updated email data to sessionStorage whenever it changes
    sessionStorage.setItem(
      `ModifiedEmailDetails/${reqId}`,
      JSON.stringify(updatedEmailData)
    );
  }, [updatedEmailData, reqId]);

  const handleCheckboxChange = (event) => {
    const checked = event.target.checked;
    setIncludeAttachments(checked);

    // Clone the current email data and update attachments/subject
    const newEmailData = { ...mailData };
    if (checked) {
      newEmailData.attachments = mailData.attachments || [];
      newEmailData.subject = "Attachments included"; // Example subject
    } else {
      newEmailData.attachments = []; // Exclude attachments
      newEmailData.subject = "Attachments excluded"; // Example subject
    }

    setUpdatedEmailData(newEmailData); // Update state to trigger re-render
  };

  return (
    <div>
      <label>
        <input
          type="checkbox"
          checked={includeAttachments}
          onChange={handleCheckboxChange}
        />
        Include Attachments
      </label>
      <div>
        <strong>Subject:</strong> {updatedEmailData.subject}
      </div>
      <div>
        <strong>Attachments:</strong>{" "}
        {updatedEmailData.attachments?.length > 0
          ? updatedEmailData.attachments.join(", ")
          : "None"}
      </div>
    </div>
  );
};

export default EmailAttachmentHandler;
