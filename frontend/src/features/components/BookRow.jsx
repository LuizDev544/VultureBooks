import React from 'react';
import { StatusBadge } from '../../components/StatusBadge';

export const BookRow = ({ livro }) => {
    if (!livro) return null;

    return (
        <tr>
            <td>{livro.id}</td>
            <td>{livro.titulo}</td>
            <td>{livro.descricao}</td>
            <td>{livro.autor}</td>
            <td>{livro.genero}</td>
            <td>{livro.paginas}</td>
            <td>{livro.idioma}</td>
            <td>{livro.ano}</td>
            <td>
                <StatusBadge status={livro.status} />
            </td>
            <td>
                {livro.status !== 'DISPONIVEL' && (
                    <button className="btn btn-sm btn-outline-success">
                        <i className="bi bi-check-circle"></i>
                        Liberar
                    </button>
                )}
            </td>
        </tr>
    )
}