import { createRoot } from "react-dom/client";

import { ThemeProvider } from "@mui/material";
import React from "react";
import { RouterRoutes } from "./common/routes/Routes.tsx";
import "./styles/global.css";
import { Theme } from "./styles/themes/theme.tsx";
import { NotificationProvider } from "./common/context/NotificationContext.tsx";

createRoot(document.getElementById("root")).render(
  <React.StrictMode>
    <ThemeProvider theme={Theme}>
      <NotificationProvider>
        <RouterRoutes />
      </NotificationProvider>
    </ThemeProvider>
  </React.StrictMode>
);
