package BookDonation.demo.Domain.Service;

import BookDonation.demo.Domain.Model.Livro;
import BookDonation.demo.Domain.Model.ValueObjects.*;
import BookDonation.demo.Domain.Repository.LivroRepository;
import BookDonation.demo.presentation.DTO.LivroRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    public Livro criarLivro(LivroRequestDTO dto) {
        AnoLivro ano        = new AnoLivro(dto.anoLancamento());
        Autor autor         = new Autor(dto.nomeAutor());
        Descricao descricao = new Descricao(dto.textoDescricao());
        Genero genero       = new Genero(dto.nomeGenero());
        Idioma idioma       = new Idioma(dto.nomeIdioma());
        Pagina pagina       = new Pagina(dto.quantidadePaginas());
        StatusLivro status  = new StatusLivro(dto.statusInicial());

        Livro novoLivro     = new Livro(dto.titulo(), ano, autor, descricao, genero, idioma, pagina, status);

        return livroRepository.save(novoLivro);
    }

    public List<Livro> listarTodosOsLivros() {
        return livroRepository.findAll(); 
    }
}