package BookDonation.demo.Domain.Service;

import BookDonation.demo.Domain.Model.Livro;
import BookDonation.demo.Domain.Model.Historico;
import BookDonation.demo.Domain.Repository.HistoricoRepository;
import BookDonation.demo.presentation.DTO.LivroRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service("livroHistoricoDecorator")
public class LivroHistoricoDecorator implements LivroOperations {

    private final LivroOperations livroServiceOriginal;
    
    @Autowired
    private HistoricoRepository historicoRepository;

    @Autowired
    public LivroHistoricoDecorator(LivroOperations livroServiceOriginal) {
        this.livroServiceOriginal = livroServiceOriginal;
    }

    @Override
    public Livro criarLivro(LivroRequestDTO dto, Long idAdmin) {
        Livro livroSalvo = livroServiceOriginal.criarLivro(dto, idAdmin);
        historicoRepository.save(new Historico("CRIAR", livroSalvo.getId(), idAdmin));
        return livroSalvo;
    }

    @Override
    public Livro atualizarLivro(Long id, LivroRequestDTO dto) {
        Livro livroAtualizado = livroServiceOriginal.atualizarLivro(id, dto);
        historicoRepository.save(new Historico("ATUALIZAR", id, null));
        return livroAtualizado;
    }

    // Métodos novos: Apenas repassam o comando direto para o service original sem gerar log
    @Override
    public void excluirLivro(Long id) {
        livroServiceOriginal.excluirLivro(id);
    }

    @Override
    public List<Livro> listarTodosOsLivros() {
        return livroServiceOriginal.listarTodosOsLivros();
    }

    @Override
    public Livro buscarPorId(Long id) {
        return livroServiceOriginal.buscarPorId(id);
    }

    @Override
    public void alternarDisponibilidade(Long id) {
        livroServiceOriginal.alternarDisponibilidade(id);
    }
}