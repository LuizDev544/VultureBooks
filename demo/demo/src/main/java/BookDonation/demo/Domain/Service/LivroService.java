package BookDonation.demo.Domain.Service;

import org.springframework.stereotype.Service;

import java.util.List;

import BookDonation.demo.Domain.Model.Livro;
import BookDonation.demo.Domain.Repository.LivroRepository;
import BookDonation.demo.infrastructure.Exception.*;

@Service
public class LivroService{

    private final LivroRepository repository;

    public LivroService(LivroRepository repository){
        this.repository = repository;
    }

    public List<Livro> listar() {
        return repository.findAll();
    }

    public Livro salvar(Livro livro) {
        return repository.save(livro);
    }

    public Livro buscarPoID(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new LivroNaoEncontrado(id));
    }

    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new LivroNaoEncontrado(id);

            repository.deleteAllById(id);
        }
    }

    public Livro atualizar(Long id, Livro novoLivro) {
        Livro livroExistente = buscarPoID(id);

        livroExistente.atualizarDados(novoLivro);

        return repository.save(livroExistente);
    }

}