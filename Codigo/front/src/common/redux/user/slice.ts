import { createSlice, Slice } from '@reduxjs/toolkit';
import { User } from '../../helpers/types';

const initialState : User = {
  id: null,
  email: null,
  name: null,
  password: null
};

const userSlice : Slice<User> = createSlice({
  name: 'user',
  initialState,
  reducers: {
    login: (state, action) => {
      state = action.payload;
    },

    logout: (state) => {
      state = initialState;
    },
  },
});

export const { login, logout } = userSlice.actions;

export default userSlice.reducer