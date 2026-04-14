import React from 'react';
import { BookRow } from './BookRow';

export const BookTable = ({ livros = [] }) => {
if (livros.length === 0) {
    return (
        <div className="p-5 text-center text-muted">
        <i className="bi bi-archive h1 d-block"></i>
        Nenhum livro cadastrado ainda.
        </div>
    );
}

return (
    <div className="table-responsive">
        <table className="table table-hover align-middle mb-0">
            <thead className="table-dark">
                <tr>
                    <th>ID</th>
                    <th>Título</th>
                    <th>Descrição</th>
                    <th>Autor</th>
                    <th>Gênero</th>
                    <th>Páginas</th>
                    <th>Idioma</th>
                    <th>Ano</th>
                    <th>Condição</th>
                    <th>Ações</th>
                </tr>
            </thead>
            <tbody>
                    {livros.map(livro => (
                    <BookRow key={livro.id} livro={livro} />
                ))}
            </tbody>
        </table>
    </div>
);
};