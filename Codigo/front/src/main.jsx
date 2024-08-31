import { createRoot } from "react-dom/client";

import { ThemeProvider } from "@mui/material";
import React from "react";
import { RouterRoutes } from "./common/routes/Routes.tsx";
import "./styles/global.css";
import { Theme } from "./styles/themes/theme.tsx";
import { NotificationProvider } from "./common/context/NotificationContext.tsx";
import { Provider } from "react-redux";
import store from './common/redux/store.js'

createRoot(document.getElementById("root")).render(
  <React.StrictMode>
    <ThemeProvider theme={Theme}>
      <NotificationProvider>
        <Provider store={store}>
          <RouterRoutes />
        </Provider>
      </NotificationProvider>
    </ThemeProvider>
  </React.StrictMode>
);
