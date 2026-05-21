package BookDonation.demo.Domain.Service;

import BookDonation.demo.Domain.Model.Livro;
import BookDonation.demo.Domain.Model.Admin;
import BookDonation.demo.Domain.Model.DetalhesCondicao;
import BookDonation.demo.Domain.Model.ValueObjects.*;
import BookDonation.demo.Domain.Repository.AdminRepository;
import BookDonation.demo.Domain.Repository.LivroRepository;
import BookDonation.demo.presentation.DTO.LivroRequestDTO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class LivroService implements LivroOperations {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private AdminRepository adminRepository;

    public Livro criarLivro(LivroRequestDTO dto, Long idAdmin) {
        Admin adminResponsavel = adminRepository.findById(idAdmin)
            .orElseThrow(() -> new IllegalArgumentException("Administrador não encontrado no banco de dados."));

        Livro novoLivro = new Livro(
            new Titulo(dto.titulo()),
            new AnoLivro(dto.anoLancamento()),
            new Autor(dto.nomeAutor()),
            new Descricao(dto.textoDescricao()),
            new Genero(dto.nomeGenero()),
            new Idioma(dto.nomeIdioma()),
            new Pagina(dto.quantidadePaginas()),
            new StatusLivro(dto.statusInicial())
        );

        novoLivro.setAdminRegistrador(adminResponsavel); 
        novoLivro.setDetalhesCondicao(new DetalhesCondicao(dto.nivelConservacao(), dto.observacoesExtras())); 

        return livroRepository.save(novoLivro);
    }

    @Transactional
    public Livro atualizarLivro(Long id, LivroRequestDTO dto) {
        Livro libroExistente = buscarPorId(id);

        libroExistente.atualizarDados(
            new Titulo(dto.titulo()),
            new AnoLivro(dto.anoLancamento()),
            new Autor(dto.nomeAutor()),
            new Descricao(dto.textoDescricao()),
            new Genero(dto.nomeGenero()),
            new Idioma(dto.nomeIdioma()),
            new Pagina(dto.quantidadePaginas()),
            new StatusLivro(dto.statusInicial())
        );
        
        libroExistente.atualizarCondicao(dto.nivelConservacao(), dto.observacoesExtras());

        return livroRepository.save(libroExistente);
    }

    public void excluirLivro(@NonNull Long id) {
        if (!livroRepository.existsById(id)) {
            throw new IllegalArgumentException("Livro não encontrado no banco de dados.");
        }
        livroRepository.deleteById(id);
    }

    public List<Livro> listarTodosOsLivros() {
        return livroRepository.findAll(); 
    }

    public Livro buscarPorId(@NonNull Long id) {
        return livroRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Livro não encontrado no banco de dados."));
    }

    @Transactional
    public void alternarDisponibilidade(Long id) {
        Livro livro = buscarPorId(id);
        livro.tornarDisponivel();
    }
}