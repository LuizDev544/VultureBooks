package BookDonation.demo.presentation.DTO;

// utilizar o record pois ele é imutável
public record LivroRequestDTO(
    String titulo,
    String nomeAutor,
    int anoLancamento,
    String textoDescricao,
    String nomeGenero,
    String nomeIdioma,
    int quantidadePaginas,
    String statusInicial
) {}