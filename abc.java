import { useEffect, useRef } from "react";

function YourComponent() {
  const latestValuesRef = useRef({
    to: [],
    cc: [],
    bcc: [],
    subject: "",
    classification: null,
    editorHtml: "",
    attaches: [],
    requestId: null,
  });

  // Update the ref whenever values change
  useEffect(() => {
    latestValuesRef.current = {
      to,
      cc,
      bcc,
      subject,
      classification,
      editorHtml,
      attaches,
      requestId,
    };
  }, [to, cc, bcc, subject, classification, editorHtml, attaches, requestId]);

  useEffect(() => {
    const interval = setInterval(async () => {
      const {
        to,
        cc,
        bcc,
        subject,
        classification,
        editorHtml,
        attaches,
        requestId,
      } = latestValuesRef.current;

      if (!requestId) {
        // Save draft for the first time
        const id = await handleAutoSaveDraftFirstTime();
        setRequestId(id);
      } else {
        // Save subsequent drafts
        await handleAutoSaveDraft();
      }
    }, 10000); // Call every 10 seconds

    return () => clearInterval(interval); // Cleanup the interval on component unmount
  }, []); // Run only once when the component mounts

  return (
    <div>
      {/* Your component JSX */}
    </div>
  );
}
