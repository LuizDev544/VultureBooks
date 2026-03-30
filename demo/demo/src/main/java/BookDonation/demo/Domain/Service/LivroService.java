package BookDonation.demo.Domain.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import BookDonation.demo.Domain.Model.*;
import BookDonation.demo.Repository.LivroRepository;

@Service
public class LivroService {
    private final LivroRepository livroRepository;

    public LivroService(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    public List<livros> listarMuseus() {
        return livroRepository.findAll();
    }

    public Livro salvarLivro(Livro livro) {
        // ⚠ Falta validação de regras de negócio (ex: campos obrigatórios)
        return livroRepository.save(livro);
    }

    public Livro buscarPorId(int id) {
        
        return livroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado")); // ⚠ Ideal: criar LivroNaoEncontradoException
    }

    public void deletarLivro(int id) {
        
        if (!livroRepository.existsById(id)) {
            
            throw new RuntimeException("Livro não encontrado"); // ⚠ Criar exceção específica
        }

        livroRepository.deleteById(id);
    }

    public Livro atualizarLivro(int id, Livro livroAtualizado) {

        return livroRepository.findById(id).map(livro -> {

            livro.setTitulo(livroAtualizado.getTitulo());
            livro.setAutor(livroAtualizado.getAutor());
            livro.setAno(livroAtualizado.getAno());
            livro.setEditora(livroAtualizado.getEditora());
            livro.setIsbn(livroAtualizado.getIsbn());
            livro.setGenero(livroAtualizado.getGenero());
            livro.setSinopse(livroAtualizado.getSinopse());
            livro.setPreco(livroAtualizado.getPreco());
            museu.setTema(museuAtualizado.getTema());
            museu.setCapacidade(museuAtualizado.getCapacidade());
            museu.setFundacao(museuAtualizado.getFundacao());
            museu.setEndereco(museuAtualizado.getEndereco());
            museu.setPreco(museuAtualizado.getPreco());

            return museuRepository.save(museu);

        }).orElseThrow(() ->
            new RuntimeException("Museu não encontrado") 
        );
}

