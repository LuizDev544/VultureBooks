import styled from 'styled-components';

export const SidebarContainer = styled.aside`
    width: 250px;
    background-color: ${props => props.theme.colors.navbarBg};
    height: 100%;
    min-height: calc(100vh - 70px); /* Descontando a altura do Header */
    display: flex;
    flex-direction: column;
    padding: 20px 0;
    transition: all 0.3s ease;
    border-right: 1px solid rgba(255, 255, 255, 0.1);

    @media (max-width: 768px) {
        width: 70px;
        
        span {
        display: none; /* Esconde o texto no mobile para virar mini-sidebar */
        }
    }
`;

export const NavItem = styled.div<{ $active?: boolean }>`
    padding: 15px 25px;
    display: flex;
    align-items: center;
    gap: 15px;
    cursor: pointer;
    color: ${props => props.$active ? props.theme.colors.action : props.theme.colors.textLight};
    background-color: ${props => props.$active ? 'rgba(255, 255, 255, 0.05)' : 'transparent'};
    transition: 0.2s;
    border-left: 4px solid ${props => props.$active ? props.theme.colors.action : 'transparent'};

    &:hover {
        background-color: rgba(255, 255, 255, 0.1);
        color: ${props => props.theme.colors.action};
    }

    svg {
        font-size: 20px;
    }
`;