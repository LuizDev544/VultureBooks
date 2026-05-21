package BookDonation.demo.Domain.Service;

import BookDonation.demo.Domain.Model.Livro;
import BookDonation.demo.Domain.Model.ValueObjects.*;
import BookDonation.demo.Domain.Repository.AdminRepository;
import BookDonation.demo.Domain.Repository.LivroRepository;
import BookDonation.demo.presentation.DTO.LivroRequestDTO;
import BookDonation.demo.Domain.Event.LivroAlteradoEvent;
import jakarta.transaction.Transactional;
import BookDonation.demo.Domain.Model.Admin;
import BookDonation.demo.Domain.Model.DetalhesCondicao;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroService {

    private final LivroRepository livroRepository;
    private final AdminRepository adminRepository;
    private final ApplicationEventPublisher eventPublisher;

    public LivroService(LivroRepository livroRepository, AdminRepository adminRepository, ApplicationEventPublisher eventPublisher) {
        this.livroRepository = livroRepository;
        this.adminRepository = adminRepository;
        this.eventPublisher = eventPublisher;
    }

    public Livro criarLivro(LivroRequestDTO dto, Long idAdmin) {
        Admin adminResponsavel = adminRepository.findById(idAdmin)
            .orElseThrow(() -> new IllegalArgumentException("Administrador não encontrado no banco de dados."));

        Titulo titulo       = new Titulo(dto.titulo());
        AnoLivro ano        = new AnoLivro(dto.anoLancamento());
        Autor autor         = new Autor(dto.nomeAutor());
        Descricao descricao = new Descricao(dto.textoDescricao());
        Genero genero       = new Genero(dto.nomeGenero());
        Idioma idioma       = new Idioma(dto.nomeIdioma());
        Pagina pagina       = new Pagina(dto.quantidadePaginas());
        StatusLivro status  = new StatusLivro(dto.statusInicial());

        DetalhesCondicao detalhes = new DetalhesCondicao(dto.nivelConservacao(), dto.observacoesExtras());

        Livro novoLivro = new Livro(titulo, ano, autor, descricao, genero, idioma, pagina, status);
        novoLivro.setAdminRegistrador(adminResponsavel); 
        novoLivro.setDetalhesCondicao(detalhes); 

        Livro livroSalvo = livroRepository.save(novoLivro);

        this.eventPublisher.publishEvent(new LivroAlteradoEvent(
            livroSalvo.getId(), 
            dto.titulo(), 
            "CADASTRO", 
            idAdmin
        ));

        return livroSalvo;
    }

    @Transactional
    public Livro atualizarLivro(Long id, LivroRequestDTO dto, Long idAdmin) {
        Livro livroExistente = buscarPorId(id);

        Titulo titulo       = new Titulo(dto.titulo());
        AnoLivro ano        = new AnoLivro(dto.anoLancamento());
        Autor autor         = new Autor(dto.nomeAutor());
        Descricao descricao = new Descricao(dto.textoDescricao());
        Genero genero       = new Genero(dto.nomeGenero());
        Idioma idioma       = new Idioma(dto.nomeIdioma());
        Pagina pagina       = new Pagina(dto.quantidadePaginas());
        StatusLivro status  = new StatusLivro(dto.statusInicial());

        livroExistente.atualizarDados(titulo, ano, autor, descricao, genero, idioma, pagina, status);
        livroExistente.atualizarCondicao(dto.nivelConservacao(), dto.observacoesExtras());

        Livro livroAtualizado = livroRepository.save(livroExistente);

        this.eventPublisher.publishEvent(new LivroAlteradoEvent(
            livroAtualizado.getId(), 
            dto.titulo(), 
            "EDICAO", 
            idAdmin
        ));

        return livroAtualizado;
    }

    public void excluirLivro(Long id, Long idAdmin) {
        Livro livro = buscarPorId(id);
        String nomeTitulo = livro.getTitulo() != null ? livro.getTitulo().toString() : "Desconhecido";
        
        livroRepository.delete(livro);

        this.eventPublisher.publishEvent(new LivroAlteradoEvent(
            id, 
            nomeTitulo, 
            "EXCLUSAO", 
            idAdmin
        ));
    }

    public List<Livro> listarTodosOsLivros() {
        return livroRepository.findAll(); 
    }

    public Livro buscarPorId(Long id) {
        return livroRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Livro não encontrado no banco de dados."));
    }

    @Transactional
    public void alternarDisponibilidade(Long id) {
        Livro livro = livroRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Livro não encontrado no banco de dados."));
        livro.tornarDisponivel();
    }
}