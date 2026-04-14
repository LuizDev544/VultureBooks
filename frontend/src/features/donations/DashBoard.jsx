import React from 'react';
import { Sidebar } from '../../components/Sidebar';
import { BookTable } from './components/BookTable';
import { ActionModal } from '../../components/ActionModal';

const Dashboard = () => {
    const livrosMock = [
        { id: 1, titulo: 'O Senhor dos Anéis', autor: 'J.R.R. Tolkien', status: 'DISPONIVEL', genero: 'Fantasia', paginas: 1200, idioma: 'Português', ano: 1954, descricao: 'Um clássico da fantasia.' },
        { id: 2, titulo: 'Dom Casmurro', autor: 'Machado de Assis', status: 'DOADO', genero: 'Clássico', paginas: 256, idioma: 'Português', ano: 1899, descricao: 'A dúvida de Bentinho.' }
    ];

    const handleEdit = (id) => console.log("Editando livro:", id);
    const handleDelete = (id) => console.log("Excluindo livro:", id);

    return (
        <div className="d-flex">
        <Sidebar />

            <main className="flex-grow-1 p-4 bg-light">
                <div className="d-flex justify-content-between align-items-center mb-4">
                    < h2>Gerenciamento de Doação</h2>
                    <button className="btn btn-primary">
                        <i className="bi bi-person-circle me-2"></i> Admin
                    </button>
                </div>
                <div className="card shadow-sm border-0">
                    <div className="card-body p-0">
                        <BookTable livros={livrosMock} />
                    </div>
                </div>
            </main>

            <ActionModal 
                id="modalEditar" 
                title="Editar Livro" 
                label="Digite o ID para editar:" 
                colorClass="bg-warning" 
                buttonText="Buscar para Editar"
                onConfirm={handleEdit}
            />

            <ActionModal 
                id="modalExcluir" 
                title="Excluir Livro" 
                label="Digite o ID para excluir permanentemente:" 
                colorClass="bg-danger text-white" 
                buttonText="Excluir Agora"
                onConfirm={handleDelete}
            />
        </div>
    );
};

export default Dashboard;