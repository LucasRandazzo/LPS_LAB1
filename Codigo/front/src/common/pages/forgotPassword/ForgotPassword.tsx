import { Container, TextField, Button } from "@mui/material"
import React, { ReactNode } from "react"
import userService from "../../services/userService"
import { useSelector, UseSelector } from "react-redux"
import { User } from "../../helpers/types"


export const ForgotPassword = () : ReactNode => {
    const user = useSelector((state: User) => state.user);

    const sendEmail = () => {
        userService.recoverpassword(user.email)
    }

    return (
        <Container>
            <div>Esqueceu a Senha</div>
            <TextField label={'Email:'} />
            <Button onSubmit={sendEmail} >
                Enviar
            </Button>
        </Container>
    )
}

export default ForgotPassword