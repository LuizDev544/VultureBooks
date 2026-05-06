import React from 'react';
import * as S from './styles';

interface StatusBadgeProps {
    status: 'DISPONIVEL' | 'INDISPONIVEL' | 'DOADO';
}

const StatusBadge: React.FC<StatusBadgeProps> = ({ status }) => {
    return (
        <S.BadgeContainer $status={status}>
        {status.replace('_', ' ')}
        </S.BadgeContainer>
    );
};

export default StatusBadge;