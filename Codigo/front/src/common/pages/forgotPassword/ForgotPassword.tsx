import { Container } from '@mui/material';
import React, { ReactNode } from 'react';
import { Outlet } from 'react-router-dom';

export const ForgotPassword = (): ReactNode => {

    return (
        <Container>
            <div>Esqueceu a Senha</div>
            <Outlet />
        </Container>
    );
};

export default ForgotPassword;
