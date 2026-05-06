export interface LoginCredentials {
    email: string;
    senha?: string;
    password?: string; 
}

export interface User {
    id: string;
    nome: string;
    email: string;
    role: 'ADMIN' | 'USER';
}

export interface AuthResponse {
    token: string;
    user: User;
}