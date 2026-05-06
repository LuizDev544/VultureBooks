import React from 'react';
import * as S from './styles';
// Se estiver usando react-icons: import { FaBook, FaHandsHelping, FaChartLine } from 'react-icons/fa';

const SideBar: React.FC = () => {
    return (
        <S.SidebarContainer>
        <S.NavItem $active={true}>
            <span>Dashboard</span>
        </S.NavItem>
        <S.NavItem>
            <span>Livros Disponíveis</span>
        </S.NavItem>
        <S.NavItem>
            <span>Minhas Doações</span>
        </S.NavItem>
        <S.NavItem>
            <span>Perfil Adm</span>
        </S.NavItem>
        </S.SidebarContainer>
    );
};

export default SideBar;