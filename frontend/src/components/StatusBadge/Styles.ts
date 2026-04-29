import styled from 'styled-components';

interface ContainerProps {
    $status: 'DISPONIVEL' | 'INDISPONIVEL' | 'DOADO';
}

export const BadgeContainer = styled.span<ContainerProps>`
    padding: 5px 12px;
    border-radius: 20px;
    font-size: 0.85rem;
    font-weight: bold;
    color: white;

    background-color: ${({ $status }) => {
        if ($status === 'DISPONIVEL') return '#2ecc71';
        if ($status === 'INDISPONIVEL') return '#e74c3c'; 
        return '#f1c40f'; 
    }};
`;