import { ColDef, SizeColumnsToFitGridStrategy, SizeColumnsToFitProvidedWidthStrategy, SizeColumnsToContentStrategy } from "ag-grid-community";
import "ag-grid-community/styles/ag-grid.css";
import "ag-grid-community/styles/ag-theme-quartz.css";
import { AgGridReact } from "ag-grid-react";
import React, { useEffect, useMemo, useState } from "react";
import { Box, useTheme } from '@mui/material';
import "./tableStyles.css";
import Loader from './Loader';  // Import the Loader component

export default function ModalTable({ data, tableHeaders, isLoading }) {
  const [gridApi, setGridApi] = useState(null);
  const [gridColumnApi, setGridColumnApi] = useState(null);
  const [visibleColumns, setVisibleColumns] = useState(tableHeaders);
  const [columnDefs, setColumnDefs] = useState<ColDef[]>([]);

  const setColumns = (columns) => {
    const newColumnDefs: ColDef[] = [];
    columns.forEach(header => {
      if (header.field === "comment") {
        newColumnDefs.push({
          field: header.field,
          hide: header.hide,
          width: 400,
        });
      } else {
        newColumnDefs.push({
          field: header.field,
          hide: header.hide,
          autoHeight: true,
        });
      }
    });
    setColumnDefs(newColumnDefs);
  };

  const defaultColDef = useMemo(() => {
    return {
      filter: "agTextColumnFilter",
      floatingFilter: false,
      cellStyle: { overflow: 'auto', wrapText: true, autoHeight: true, height: 'auto' }
    };
  }, [data]);

  const calculateRowHeight = (params) => {
    if (params.data.comment) {
      const cellData = params.data.comment;
      const size = 1 + cellData.length / 30;
      return size > 6 ? 150 : size * 30;
    }
    return null;
  };

  const onGridReady = (params) => {
    setGridApi(params.api);
    setGridColumnApi(params.columnApi);
  };

  const autoSizeStrategy = useMemo(() => ({
    type: "fitGridWidth",
    defaultMinWidth: 100,
  }), []);

  useEffect(() => {
    setColumns(visibleColumns);
  }, [visibleColumns]);

  return (
    <Box
      className={useTheme().palette.mode === 'dark' ? "ag-theme-quartz-dark" : "ag-theme-quartz"}
      sx={{ height: "100%", width: "100%" }}
    >
      {isLoading ? (
        <Loader />
      ) : (
        <AgGridReact
          onGridReady={onGridReady}
          rowData={data}
          columnDefs={columnDefs}
          getRowHeight={calculateRowHeight}
          defaultColDef={defaultColDef}
          autoSizeStrategy={autoSizeStrategy}
          rowSelection="multiple"
          suppressRowClickSelection={true}
          pagination={true}
          paginationPageSize={10}
          paginationPageSizeSelector={[10, 20, 30]}
        />
      )}
    </Box>
  );
}
