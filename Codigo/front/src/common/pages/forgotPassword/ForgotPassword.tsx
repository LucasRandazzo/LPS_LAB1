import { Container, TextField, Button } from "@mui/material"
import React, { ReactNode, useRef } from "react"
import userService from "../../services/userService"
import { useSelector } from "react-redux"
import { User } from "../../helpers/types"


export const ForgotPassword = () : ReactNode => {
    //const user = useSelector((state: User) => state.user);
    const refEmail = useRef<HTMLInputElement>(null);

    const sendEmail = async () => {
        const email = refEmail.current?.value ?? '';
        userService.recoverpassword(email);
    }

    return (
        <Container>
            <div>Esqueceu a Senha</div>
            <TextField label={'Email:'} inputRef={refEmail}/>
            <Button onSubmit={sendEmail} >
                Enviar
            </Button>
        </Container>
    )
}

export default ForgotPassword