  const handleRemoveFiles = (index: number) => {
    if (fileList) {
      const filesArray = Array.from(fileList);
      const updatedFileList = filesArray.filter((_, i) => i !== index);
      setFileList(new DataTransfer().files);
      updatedFileList.forEach(file => new DataTransfer().items.add(file));
    }
  };
