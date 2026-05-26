package BookDonation.demo.Domain.Service;

import BookDonation.demo.Domain.Model.Livro;
import BookDonation.demo.presentation.DTO.LivroRequestDTO;
import java.util.List;

public interface LivroOperations {
    Livro criarLivro(LivroRequestDTO dto, Long idAdmin);
    Livro atualizarLivro(Long id, LivroRequestDTO dto, Long idAdmin);
    
    void excluirLivro(Long id, Long idAdmin); 
    
    List<Livro> listarTodosOsLivros();
    Livro buscarPorId(Long id);
    void alternarDisponibilidade(Long id);
}