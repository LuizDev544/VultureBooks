package BookDonation.demo.Domain.Service;

import BookDonation.demo.Domain.Model.Livro;
import BookDonation.demo.Domain.Model.Admin;
import BookDonation.demo.Domain.Model.DetalhesCondicao;
import BookDonation.demo.Domain.Model.ValueObjects.*;
import BookDonation.demo.Domain.Repository.AdminRepository;
import BookDonation.demo.Domain.Repository.LivroRepository;
import BookDonation.demo.Domain.Factories.LivroFactory;
import BookDonation.demo.presentation.DTO.LivroRequestDTO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private LivroFactory livroFactory; 

    public Livro criarLivro(LivroRequestDTO dto, Long idAdmin) {
        Admin adminResponsavel = adminRepository.findById(idAdmin)
            .orElseThrow(() -> new IllegalArgumentException("Administrador não encontrado no banco de dados."));

        Object[] vos = livroFactory.criarValueObjectsDoLivro(dto);
        DetalhesCondicao detalhes = livroFactory.criarDetalhesCondicao(dto);

        Livro novoLivro = new Livro(
            (Titulo) vos[0], (AnoLivro) vos[1], (Autor) vos[2], (Descricao) vos[3],
            (Genero) vos[4], (Idioma) vos[5], (Pagina) vos[6], (StatusLivro) vos[7]
        );

        novoLivro.setAdminRegistrador(adminResponsavel); 
        novoLivro.setDetalhesCondicao(detalhes); 

        return livroRepository.save(novoLivro);
    }

    @Transactional
    public Livro atualizarLivro(Long id, LivroRequestDTO dto) {
        Livro libroExistente = buscarPorId(id);

        Object[] vos = livroFactory.criarValueObjectsDoLivro(dto);

        libroExistente.atualizarDados(
            (Titulo) vos[0], (AnoLivro) vos[1], (Autor) vos[2], (Descricao) vos[3],
            (Genero) vos[4], (Idioma) vos[5], (Pagina) vos[6], (StatusLivro) vos[7]
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

    public Livro buscarPorId(Long id) {
        return livroRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Livro não encontrado no banco de dados."));
    }

    @Transactional
    public void alternarDisponibilidade(Long id) {
        Livro livro = buscarPorId(id);
        livro.tornarDisponivel();
    }
}