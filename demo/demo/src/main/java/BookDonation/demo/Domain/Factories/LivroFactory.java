package BookDonation.demo.Domain.Factories;

import BookDonation.demo.Domain.Model.ValueObjects.*;
import BookDonation.demo.Domain.Model.DetalhesCondicao;
import BookDonation.demo.presentation.DTO.LivroRequestDTO;
import org.springframework.stereotype.Component;

@Component
public class LivroFactory {

    public Object[] criarValueObjectsDoLivro(LivroRequestDTO dto) {
        return new Object[] {
            new Titulo(dto.titulo()),
            new AnoLivro(dto.anoLancamento()),
            new Autor(dto.nomeAutor()),
            new Descricao(dto.textoDescricao()),
            new Genero(dto.nomeGenero()),
            new Idioma(dto.nomeIdioma()),
            new Pagina(dto.quantidadePaginas()),
            new StatusLivro(dto.statusInicial())
        };
    }

    public DetalhesCondicao criarDetalhesCondicao(LivroRequestDTO dto) {
        return new DetalhesCondicao(dto.nivelConservacao(), dto.observacoesExtras());
    }
}