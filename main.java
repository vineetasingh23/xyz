const VisuallyHiddenInput = styled("input")({
  clip: "rect(0 0 0 0)",
  clipPath: "inset(50%)",
  height: 1,
  overflow: "hidden",
  position: "absolute",
  bottom: 0,
  left: 0,
  whiteSpace: "nowrap",
  width: 1,
});

const FileUploadComponent = () => {
  const [fileList, setFileList] = useState<File[]>([]);

  const handleFileChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    if (e.target.files) {
      setFileList(Array.from(e.target.files));
    }
  };

  const handleRemoveAllFiles = () => {
    setFileList([]);
  };

  const handleUploadClick = () => {
    if (fileList.length === 0) {
      return;
    }
    const data = new FormData();
    fileList.forEach((file, i) => {
      data.append(`file-${i}`, file, file.name);
    });

    fetch("https://httpbin.org/post", {
      method: "POST",
      body: data,
    })
      .then((res) => res.json())
      .then((data) => console.log(data))
      .catch((err) => console.error(err));
  };

  const handleRemoveFile = (index: number) => {
    setFileList(fileList.filter((_, i) => i !== index));
  };
