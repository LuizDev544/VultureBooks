import React, { useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom';

const EditBookpage: React.FC = () => {
    const { id } = useParams();
    const navigate = useNavigate();
    
    const [book, setBook] = useState({
        nomeLivro: 'O Hobbit',
        anoLancamento: '1937', // Mudei para string para evitar erro no input
        quantidadePaginas: '310'
    });

    const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const { name, value } = e.target;
        setBook(prev => ({ ...prev, [name]: value }));
    };

    const handleSubmit = (e: React.FormEvent) => {
        e.preventDefault();
        console.log("Atualizando id:", id, book);
        navigate('/livros/painel');
    };

    return (
        <div className="container mt-5" style={{ color: 'white' }}>
            <h2>Editar Livro #{id}</h2>
            <form onSubmit={handleSubmit}>
                <input 
                    name="nomeLivro" 
                    value={book.nomeLivro} 
                    className="form-control mb-3" 
                    onChange={handleChange} 
                />
                <button type="submit" className="btn btn-success">Atualizar</button>
            </form>
        </div>
    );
};

export default EditBookpage;