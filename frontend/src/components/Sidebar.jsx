import React from "react";

export const Sidebar = () => {
    return (
        <nav className="sidebar bg-dark text-white p-3 min-vh-100" style= {{ width: "250px"}} >
            <h4 className="text-center mb-4 border-bottom pb-3">
                Vulture Books
            </h4>
            <ul className="nav flex-column gap-2">
                <li className="nav-item">
                    <a href="/Livros/cadastrar" className="nav-link text-white">
                        <i className="bi bi-plus-circle me-2"></i>
                        Cadastrar Livro
                    </a>
                </li>
                <li class="nav-item">
                    <a href="#" class="nav-link text-white" data-bs-toggle="modal" data-bs-target="#modalEditar">
                        <i class="bi bi-pencil-square me-2"></i> Editar Livros
                    </a>
                </li>
                <li class="nav-item">
                    <a href="#" class="nav-link text-white text-danger" data-bs-toggle="modal" data-bs-target="#modalExcluir">
                        <i class="bi bi-trash me-2"></i> Excluir Livros
                    </a>
                </li>
            </ul>
            <div className="mt-auto pt-5">
                <a href="/admin/login" className="btn btn-outline-light btn-sm w-100">Sair</a>
            </div>
        </nav>
    )
}