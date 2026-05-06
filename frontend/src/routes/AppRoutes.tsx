import React from 'react';
import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom';
import LoginPage from '@/features/auth/LoginPage';

const AppRoutes: React.FC = () => {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/login" element={<LoginPage />} />
                <Route path="/" element={<Navigate to="/login" replace />} />
                <Route path="*" element={<div style={{color: 'white'}}>Página não encontrada</div>} />
            </Routes>
        </BrowserRouter>
    );
};

export default AppRoutes; 