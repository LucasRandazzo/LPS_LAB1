import { useRef, useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';

import { Login, VisibilityOff } from '@mui/icons-material';
import Visibility from '@mui/icons-material/Visibility';
import { Box, Button, IconButton, InputAdornment, Link, TextField } from '@mui/material';
import React from 'react';
import * as ButtonStyle from '../../../styles/types/ButtonsStyles';
import * as Input from '../../../styles/types/InputStyles';
import Validade from '../../utils/Validate.tsx';
import * as S from './LoginPage.styles.ts';
import { useDispatch } from 'react-redux'
import { login, logout } from '../../redux/user/slice.js';
import { User, UserLogin } from '../../helpers/types.ts';
import userService from '../../services/userService.ts';

const LoginPage = () => {
    const navigate = useNavigate();

    const refEmail = useRef<HTMLInputElement>(null);
    const refPassword = useRef<HTMLInputElement>(null);

    const [emailError, setEmailError] = useState(false);
    const [emailHelperText, setEmailHelperText] = useState('');
    const [showPassword, setShowPassword] = useState(false);

    const dispatch = useDispatch()

    const validade = new Validade();
    
    useEffect(() => {
        const localUser = localStorage.getItem("user");

        if (localUser === null) {
            return
        }
        
        const user = JSON.parse(localUser);
        dispatch(login(user))

        navigate('/home')
    }, []);

    const onSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
        event.preventDefault();

        const email = refEmail.current?.value || '';
        const password = refPassword.current?.value || '';
        const isEmailValid = validade.validateEmail(email);

        const userLogin : UserLogin = {
            email: email,
            password: password,
        }

        setEmailError(!isEmailValid);

        if (!isEmailValid) {
            setEmailHelperText('Please enter a valid email.');
        } else {
            setEmailHelperText('');
        }

        if (!isEmailValid) return;

        const user : User = await userService.login(userLogin)
        localStorage.setItem("user", JSON.stringify(user));
        dispatch(login(user))
        navigate("/home")
    };

    return (
        <S.Container>
            {/* <S.Logo src={Logo} alt="logo" /> */}
            <S.LoginWrapper>
                <S.LoginTitle>Login</S.LoginTitle>
                <S.LoginForm onSubmit={onSubmit}>
                    <TextField
                        id="email"
                        label="Email"
                        variant="outlined"
                        placeholder="Email"
                        error={emailError}
                        helperText={emailHelperText}
                        fullWidth
                        margin="normal"
                        sx={Input.input}
                        inputRef={refEmail}
                    />
                    <TextField
                        id="password"
                        label="Password"
                        variant="outlined"
                        type={showPassword ? 'text' : 'password'}
                        placeholder="Password"
                        fullWidth
                        margin="normal"
                        sx={Input.input}
                        inputRef={refPassword}
                        InputProps={{
                            endAdornment: (
                                <InputAdornment position="end">
                                    <IconButton
                                        aria-label="toggle password visibility"
                                        onClick={() => setShowPassword(!showPassword)}
                                        edge="end"
                                    >
                                        {showPassword ? <VisibilityOff /> : <Visibility />}
                                    </IconButton>
                                </InputAdornment>
                            ),
                        }}
                    />
                    <Box
                        sx={{
                            display: 'flex',
                            justifyContent: 'right',
                            width: '100%',
                            mt: '-15px',
                            maxWidth: 720,
                        }}
                    >
                        <Link
                            underline="hover"
                            component="label"
                            variant="body2"
                            sx={{
                                color: 'black',
                            }}
                            onClick={() => {
                                navigate('/forgotPassword/email');
                            }}
                        >
                            Esqueceu sua senha?
                        </Link>
                    </Box>

                    <Button type="submit" sx={ButtonStyle.greenButton}>
                        Entrar
                    </Button>
                </S.LoginForm>
            </S.LoginWrapper>
        </S.Container>
    );
};

export default LoginPage;
