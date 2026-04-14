export const StatusBadge = ({ status }) => {
    
    const config = {
        DISPONIVEL: { class: 'bg-success', label: 'Disponível' },
    INDISPONIVEL: { class: 'bg-secondary', label: 'Indisponível' },
    DOADO: { class: 'bg-primary', label: 'Doado' }
    }

    const { class: className, label } = config[status] || config.INDISPONIVEL;

    return <span className={`badge ${className}`}>{label}</span>;
}