import { CircularProgress } from '@mui/material';
import React, { Suspense } from 'react';
import { Route, BrowserRouter as Router, Routes } from 'react-router-dom';
import { DashboardLayout } from '../layouts/DashboardLayout';
import LoginPage from '../pages/login/LoginPage';
import { RegistrationPage } from '../pages/registration/Registration';
import { SubjectPage } from '../pages/subjects/Subject';
import { CoursePage } from '../pages/course/CoursePage';

export const RouterRoutes = () => {
    return (
        <Router>
            {/* TODO: Add a loader component */}
            <Suspense
                fallback={
                    <DashboardLayout>
                        <CircularProgress color="inherit" />
                    </DashboardLayout>
                }
            >
                <Routes>
                    <Route path="/" element={
                    <DashboardLayout>
                    <RegistrationPage />
                </DashboardLayout>} />
                <Route path="/course" element={
                    <DashboardLayout>
                    <CoursePage />
                </DashboardLayout>} />
                    <Route path="/registration" element={ 
                    <DashboardLayout>
                        <RegistrationPage />
                    </DashboardLayout>} />
                    <Route path="/subjects" element={ 
                    <DashboardLayout>
                        <SubjectPage />
                    </DashboardLayout>} />
                    <Route path="/login" element={<LoginPage/>} />
                </Routes>
            </Suspense>
        </Router>
    );
};

