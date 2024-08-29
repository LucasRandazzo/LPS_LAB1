import { useSelector } from 'react-redux'
import { useDispatch } from 'react-redux';

import { Button } from '@mui/material'

import { selectApresentacao } from '../../redux/user/user.selectors';
import { login, logout } from '../../redux/user/slice';
import { Container, Title } from './style';

const HomePage = () => {
  const { currentUser } = useSelector(state => state.userReducer)
  const apresentacao = useSelector(selectApresentacao)
  const dispatch = useDispatch();

  console.log(currentUser);
  console.log(apresentacao)

  const handleClick = () => {
    if (currentUser) {
      dispatch(logout())
    }
    else {
      dispatch(login({ name: 'Pedro', age: 20 }))
    }
  }

  return (
    <Container>
      <Title>Home</Title>
      <Button variant="contained" onClick={handleClick}>
        botao
      </Button>
    </Container>

  )
}

export default HomePage