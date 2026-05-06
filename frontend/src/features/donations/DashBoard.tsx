import React, { useState } from 'react';
import Sidebar from '../../components/Sidebar';
import ActionModal from '../../components/ActionModal';

const Dashboard: React.FC = () => {
    // Estados para controlar os modais (obrigatórios pelo seu componente)
    const [isEditModalOpen, setIsEditModalOpen] = useState(false);
    const [isDeleteModalOpen, setIsDeleteModalOpen] = useState(false);

    const handleEdit = (id: number | string) => {
        console.log("Editando livro:", id);
        setIsEditModalOpen(false);
    };

    const handleDelete = (id: number | string) => {
        console.log("Excluindo livro:", id);
        setIsDeleteModalOpen(false);
    };

    return (
        <div className="d-flex" style={{ backgroundColor: '#121212', minHeight: '100vh', color: 'white' }}>
            <Sidebar />
            <div className="container-fluid p-4">
                <h1 className="mb-4">Painel de Doações</h1>
                
                <p>O sistema de login funcionou! Você está no Dashboard.</p>

                <div className="mt-4">
                    {/* Botões manuais para disparar os modais */}
                    <button className="btn btn-warning me-2" onClick={() => setIsEditModalOpen(true)}>
                        Abrir Edição
                    </button>
                    <button className="btn btn-danger" onClick={() => setIsDeleteModalOpen(true)}>
                        Abrir Exclusão
                    </button>

                    {/* Modal de Edição */}
                    <ActionModal 
                        isOpen={isEditModalOpen}
                        title="Confirmar Edição"
                        message="Deseja realmente editar este livro?"
                        onConfirm={() => handleEdit(1)}
                        onCancel={() => setIsEditModalOpen(false)}
                    />

                    {/* Modal de Exclusão */}
                    <ActionModal 
                        isOpen={isDeleteModalOpen}
                        title="Confirmar Exclusão"
                        message="Tem certeza que deseja apagar este registro? Esta ação é irreversível."
                        onConfirm={() => handleDelete(1)}
                        onCancel={() => setIsDeleteModalOpen(false)}
                    />
                </div>
            </div>
        </div>
    );
};

export default Dashboard;