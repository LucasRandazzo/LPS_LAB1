import { createSlice, Slice } from '@reduxjs/toolkit';
import { User } from '../../helpers/types';

type UserSlice = {
  user: User
}

const initialState : UserSlice = {
  user: {
    id: null,
    email: null,
    name: null,
    password: null,
  }
}

const userSlice : Slice<UserSlice> = createSlice({
  name: 'user',
  initialState,
  reducers: {
    login: (state, action) => {
      state.user = action.payload;
    },

    logout: (state) => {
      state.user = initialState.user;
    },
  },
});

export const { login, logout } = userSlice.actions;

export default userSlice.reducer