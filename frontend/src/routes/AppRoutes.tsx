import React from 'react';
import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom';
import LoginPage from '../features/auth/LoginPage';
import DashBoard from '../features/donations/DashBoard'; 
import CreateBookPage from '../features/donations/CreateBookPage';
import EditBookpage from '../features/donations/EditBookpage';

const AppRoutes: React.FC = () => {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/login" element={<LoginPage />} />
                <Route path="/livros/painel" element={<DashBoard />} />
                <Route path="/livros/novo" element={<CreateBookPage />} />
                <Route path="/livros/editar/:id" element={<EditBookpage />} />
                <Route path="/" element={<Navigate to="/login" replace />} />
                <Route path="*" element={<div style={{color: 'white'}}>Página não encontrada</div>} />
            </Routes>
        </BrowserRouter>
    );
};

export default AppRoutes;