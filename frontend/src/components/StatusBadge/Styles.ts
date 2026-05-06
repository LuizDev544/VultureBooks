import styled from 'styled-components';

interface BadgeProps {
    $status: 'DISPONIVEL' | 'INDISPONIVEL' | 'DOADO';
}

export const BadgeContainer = styled.span<BadgeProps>`
    padding: 4px 12px;
    border-radius: 20px;
    font-size: 12px;
    font-weight: 700;
    text-transform: uppercase;
    color: ${props => props.theme.colors.textLight};

    background-color: ${({ theme, $status }) => {
        switch ($status) {
        case 'DISPONIVEL':
            return theme.colors.success;
        case 'INDISPONIVEL':
            return theme.colors.danger;
        case 'DOADO':
            return theme.colors.warning;
        default:
            return '#6b7280';
        }
    }};
`;