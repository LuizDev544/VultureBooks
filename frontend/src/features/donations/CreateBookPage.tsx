import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';

const CreateBookPage: React.FC = () => {
    const navigate = useNavigate();
    const [book, setBook] = useState({
        nomeLivro: '',
        nomeAutor: '',
        textoDescricao: ''
    });

    const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
        const { name, value } = e.target;
        setBook(prev => ({ ...prev, [name]: value }));
    };

    const handleSubmit = (e: React.FormEvent) => {
        e.preventDefault();
        console.log("Salvando:", book);
        navigate('/livros/painel');
    };

    return (
        <div className="container mt-5" style={{ color: 'white' }}>
            <h2>Cadastrar Novo Livro</h2>
            <form onSubmit={handleSubmit}>
                <div className="mb-3">
                    <label className="form-label">Nome do Livro</label>
                    <input name="nomeLivro" className="form-control" onChange={handleChange} />
                </div>
                <div className="mb-3">
                    <label className="form-label">Descrição</label>
                    <textarea 
                        name="textoDescricao" 
                        className="form-control" 
                        rows={3} // Corrigido: Agora é número
                        onChange={handleChange}
                    />
                </div>
                <button type="submit" className="btn btn-primary">Salvar</button>
            </form>
        </div>
    );
};

export default CreateBookPage;