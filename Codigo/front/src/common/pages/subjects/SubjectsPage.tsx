import AddIcon from '@mui/icons-material/Add';
import { Button, Typography } from '@mui/material';
import { Box } from '@mui/system';
import { MaterialReactTable, useMaterialReactTable } from 'material-react-table';
import React, { useEffect, useMemo, useState } from 'react';
import axiosInstance from '../../services/api';


export const SubjectPage = () => {
    const [data, setData] = useState([]);
    const [subjectData, setSubjectData] = useState([]);
    const [registrationId, setRegistrationId] = useState();
    const [open, setOpen] = useState(false);
    const [openRegisterModel, setOpenRegisterModel] = useState(false);
    const [reload, setReload] = useState(true);


    useEffect(() => {
        getRegistration();
    }, [reload]);


    const getRegistration = () => {
        axiosInstance
        .get('/subject')
        .then((data) => {
            setData(data.data);
            setReload(false);
        })
        .catch((e) => {
            console.log(e);
        });
    };

    const columns = useMemo(
        () => [
          {
            accessorKey: "id",
            header: "ID",
            grow: true,
          },
          {
            accessorKey: "name",
            header: "Disciplina",
          },

          {
            accessorKey: "price",
            header: "Valor",
            Cell: ({ renderedCellValue }) => {
              return `R$ ${renderedCellValue}`;
            },
          },
          {
            accessorKey: "situationEnum",
            header: "Situação",
          },
          {
            accessorKey: "credits",
            header: "Creditos",
          },
          {
            accessorKey: "CurriculumName",
            header: "Cursos Relacionados",
          },
        ],
        []
      );
      const table = useMaterialReactTable({
        columns,
        enableDensityToggle: false,
        data: data,
        //passing the static object variant if no dynamic logic is needed
        muiSelectCheckboxProps: {
          color: "secondary", //makes all checkboxes use the secondary color
        },
        enableRowActions: true,
        columnResizeMode: "onChange",
        positionActionsColumn: "last",
        displayColumnDefOptions: {
          "mrt-row-select": {
            size: 50, //adjust the size of the row select column
            grow: false, //new in v2.8 (default is false for this column)
          },
          "mrt-row-numbers": {
            size: 40,
            grow: true, //new in v2.8 (allow this column to grow to fill in remaining space)
          },
        },
        renderRowActions: ({ row }) => (
          <Box sx={{ display: "flex", flexWrap: "nowrap", gap: "8px" }}>
           
          </Box>
        ),
    
        muiTableContainerProps: {
          sx: { maxWidth: "100%" },
        },
        muiTopToolbarProps: {
          sx: {
            fontWeight: "bold",
            fontSize: "16px",
          },
        },
        muiBottomToolbarProps: {
          sx: {
            fontWeight: "bold",
            fontSize: "16px",
          },
        },
        muiTableHeadCellProps: {
          sx: {
            fontWeight: "bold",
            fontSize: "16px",
          },
        },
        muiTableBodyCellProps: {
          sx: {
            fontWeight: "normal",
            fontSize: "16px",
          },
        },
      });
    return (
        <>
            <header className="flex justify-between">
                <Typography variant="h4">Materias Cadastradas</Typography>
                <Button variant="contained" onClick={() => {
                    setOpenRegisterModel(!openRegisterModel)
                }} endIcon={<AddIcon />}>
                    Adicionar Materia
                </Button>
            </header>

            <Box display={'grid'} className="my-5">
                <MaterialReactTable table={table} />
            </Box>
        </>
    );
};

