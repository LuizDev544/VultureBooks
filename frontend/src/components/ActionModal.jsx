import React from "react";

export const ActionModal = ({ id, title, label, colorClass, buttonText, onConfirm}) => {
    return (
        <div className="modal fade" id={id} tabIndex="-1" aria-hidden="true">
            <div className="modal-dialog modal-dialog-centered">
                <div className="modal-content">
                <div className={`modal-header ${colorClass}`}>
                    <h5 className="modal-title fw-bold">{title}</h5>
                    <button type="button" className="btn-close" data-bs-dismiss="modal"></button>
                </div>
                <div className="modal-body">
                    <label className="form-label">{label}</label>
                    <input type="number" className="form-control" placeholder="Ex: 1" id={`input-${id}`} />
                </div>
                <div className="modal-footer">
                    <button type="button" className="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                    <button 
                    type="button" 
                    className={`btn ${colorClass.includes('warning') ? 'btn-warning' : 'btn-danger'} fw-bold`}
                    onClick={() => onConfirm(document.getElementById(`input-${id}`).value)}
                    >
                    {buttonText}
                    </button>
                </div>
                </div>
            </div>
        </div>
    )
}