export type BookStatus = 'DISPONIVEL' | 'INDISPONIVEL' | 'DOADO';

export interface Book {
    id?: number;
    titulo: string;
    nomeAutor: string;
    nomeGenero: string;
    anoLancamento: number;
    quantidadePaginas: number;
    nomeIdioma: string;
    nivelConservacao: string;
    status: BookStatus;
    textoDescricao: string;
    observacoesExtras?: string;
    dataCadastro?: string;
}

export interface DonationState {
    books: Book[];
    loading: boolean;
    error: string | null;
    selectedBook: Book | null;
}