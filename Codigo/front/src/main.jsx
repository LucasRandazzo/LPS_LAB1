import { createRoot } from "react-dom/client";

import { ThemeProvider } from "@mui/material";
import React from "react";
import { Provider } from "react-redux";
import { NotificationProvider } from "./common/context/NotificationContext.tsx";
import store from "./common/redux/store.js";
import "./styles/global.css";
import { Theme } from "./styles/themes/theme.tsx";

import { RouterProvider } from "react-router-dom";
import SystemRoutes from "./routes/SystemRoutes.tsx";
createRoot(document.getElementById("root")).render(
  <React.StrictMode>
    <ThemeProvider theme={Theme}>
      <NotificationProvider>
        <Provider store={store}>
          <RouterProvider router={SystemRoutes} />
        </Provider>
      </NotificationProvider>
    </ThemeProvider>
  </React.StrictMode>
);
