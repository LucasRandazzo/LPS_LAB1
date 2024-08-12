import { useSelector } from 'react-redux'
import { useDispatch } from 'react-redux';

import { Button } from '@mui/material'

import { selectApresentacao } from '../../redux/user/user.selectors';
import { login, logout } from '../../redux/user/slice';

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
    <div>
      Home
      <Button onClick={handleClick}>
        botao
      </Button>
    </div>

  )
}

export default HomePage