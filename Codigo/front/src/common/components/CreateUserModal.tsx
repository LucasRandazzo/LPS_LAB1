import CloseIcon from "@mui/icons-material/Close";
import {
    Box,
    Button,
    CircularProgress,
    Dialog,
    DialogContent,
    DialogTitle,
    IconButton,
    Input,
} from "@mui/material";
import React, { Dispatch, SetStateAction, useEffect, useState } from "react";
import * as ButtonStyle from "../../styles/types/ButtonsStyles";
import { User } from "../helpers/types";
import { useNotification } from "../hooks/useNotification";
import axiosInstance from "../services/api";
interface CreateUserModalProps {
  typeUser: string;
  openModal: boolean;
  setOpenModal: Dispatch<SetStateAction<boolean>>;
  setReload: Dispatch<SetStateAction<boolean>>;
}

const CreateUserModal: React.FC<CreateUserModalProps> = ({
  typeUser,
  openModal,
  setOpenModal,
  setReload,
}) => {
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [loading, setLoading] = useState(false);
  const handleNameChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setName(event.target.value);
  };

  const handleEmailChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setEmail(event.target.value);
  };
  const { showNotification } = useNotification();
  useEffect(() => {
    setName("");
    setEmail("");
  }, [openModal]);

  const handleSubmit = (event: React.FormEvent) => {
    event.preventDefault();
    const user: User = {
      name: name,
      email: email,
      id: null,
      password: null,
      userType: typeUser,
    };
    setLoading(true);
    axiosInstance
      .post("/user/register", user)
      .then((data) => {
        setReload(true);
        showNotification({
          message: "User created successfully",
          type: "success",
          title: "User created",
        });
        setLoading(false);
        handleClose();
      })
      .catch((e) => {
        console.log(e);
        showNotification({
          message: e.response.data.message,
          type: "error",
          title: e.response.data.title,
        });
      });
  };

  const handleClose = () => {
    setOpenModal(!open);
  };

  return (
    <Dialog
      fullWidth
      maxWidth="md"
      open={openModal}
      onClose={handleClose}
      sx={{
        pointerEvents: loading ? "none" : "auto",
      }}
    >
      <DialogTitle>Create User </DialogTitle>
      <DialogContent>
        <CircularProgress
          sx={{
            visibility: loading ? "visible" : "hidden",
            position: "absolute",
            top: "40%",
            left: "45%",
          }}
        />
   <IconButton
          edge="start"
          color="inherit"
          onClick={handleClose}
          aria-label="close"
          sx={ButtonStyle.closeButton}
        >
          <CloseIcon />
        </IconButton>
        <form onSubmit={handleSubmit}>
          <Box display={"grid"} className="my-5">
            <label>Informe o nome</label>
            <Input type="text" value={name} onChange={handleNameChange} />
          </Box>
          <Box display={"grid"} className="my-5">
            <label>Informe o email</label>
            <Input type="email" value={email} onChange={handleEmailChange} />
          </Box>
          <Button type="submit" color="primary">
            Register
          </Button>
        </form>
      </DialogContent>
    </Dialog>
  );
};

export default CreateUserModal;
