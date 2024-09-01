import { createRoot } from "react-dom/client";

import { ThemeProvider } from "@mui/material";
import React from "react";
import { Provider } from "react-redux";
import App from "./App.jsx";
import { NotificationProvider } from "./common/context/NotificationContext.tsx";
import store from "./common/redux/store.js";
import "./styles/global.css";
import { Theme } from "./styles/themes/theme.tsx";

import { createBrowserRouter, RouterProvider } from "react-router-dom";
import { CoursePage } from "./common/pages/course/CoursePage.tsx";
import { CurriculumPage } from "./common/pages/curriculum/CurriculumPage.tsx";
import ErrorPage from "./common/pages/error/ErrorPage.jsx";
import HomePage from "./common/pages/home/HomePage.jsx";
import { RegistrationPage } from "./common/pages/registration/Registration.tsx";
import { StudentPage } from "./common/pages/student/StudentPage.tsx";
import { SubjectPage } from "./common/pages/subjects/Subject.tsx";
import { TeacherPage } from "./common/pages/teacher/TeacherPage.tsx";
import LoginPage from "./common/pages/login/LoginPage.tsx";
import Request from "./common/pages/forgotPassword/request/Request.tsx";
import CheckToken from "./common/pages/forgotPassword/token/CheckToken.tsx";
import ForgotPassword from "./common/pages/forgotPassword/ForgotPassword.tsx";
import ChangePassword from "./common/pages/forgotPassword/change/ChangePassword.tsx";

const router = createBrowserRouter([
  {
    path: "/",
    element: <App />,
    errorElement: <ErrorPage />,
    children: [
      {
        path: "/home",
        element: <HomePage />,
      },
      {
        path: "/registration",
        element: <RegistrationPage />,
      },
      {
        path: "/subjects",
        element: <SubjectPage />,
      },
      {
        path: "/course",
        element: <CoursePage />,
      },
      {
        path: "/curriculum",
        element: <CurriculumPage />,
      },
      {
        path: "/student",
        element: <StudentPage />,
      },
      {
        path: "/teacher",
        element: <TeacherPage />,
      },

    ],
  },
  {
    path: '/login',
    element: <LoginPage />
  },
  {
    path: '/forgotpassword',
    element: <ForgotPassword />,
    children: [
      {
        index: true,
        element: <Request />
      },
      {
        path: 'token',
        element: <CheckToken />
      },
      {
        path: 'changepassword',
        element: <ChangePassword />
      }
    ]
  }
]);
createRoot(document.getElementById("root")).render(
  <React.StrictMode>
    <ThemeProvider theme={Theme}>
      <NotificationProvider>
        <Provider store={store}>
          <RouterProvider router={router} />
        </Provider>
      </NotificationProvider>
    </ThemeProvider>
  </React.StrictMode>
);
