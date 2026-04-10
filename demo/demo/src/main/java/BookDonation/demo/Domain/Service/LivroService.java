package BookDonation.demo.Domain.Service;

import BookDonation.demo.Domain.Model.Livro;
import BookDonation.demo.Domain.Model.ValueObjects.*;
import BookDonation.demo.Domain.Repository.LivroRepository;
import BookDonation.demo.presentation.DTO.LivroRequestDTO;
import jakarta.transaction.Transactional;

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

    @Transactional
    public Livro atualizarLivro(Long id, LivroRequestDTO dto) {
        Livro livroExistente = buscarPorId(id);

        AnoLivro ano        = new AnoLivro(dto.anoLancamento());
        Autor autor         = new Autor(dto.nomeAutor());
        Descricao descricao = new Descricao(dto.textoDescricao());
        Genero genero       = new Genero(dto.nomeGenero());
        Idioma idioma       = new Idioma(dto.nomeIdioma());
        Pagina pagina       = new Pagina(dto.quantidadePaginas());
        StatusLivro status  = new StatusLivro(dto.statusInicial());

        livroExistente.atualizarDados(dto.titulo(), ano, autor, descricao, genero, idioma, pagina, status);
        return livroRepository.save(livroExistente);
    }

    public void excluirLivro(Long id) {
        Livro livro = buscarPorId(id);
        
        livroRepository.delete(livro);
    }

    public List<Livro> listarTodosOsLivros() {
        return livroRepository.findAll(); 
    }

    public Livro buscarPorId(Long id) {
        return livroRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Livro não encontrado no banco de dados."));
    }
}