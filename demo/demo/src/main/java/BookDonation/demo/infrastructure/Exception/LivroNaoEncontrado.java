package BookDonation.demo.infrastructure.Exception;

public class LivroNaoEncontrado extends RuntimeException {

    public LivroNaoEncontrado(Long id) {
        super("Livro não encontrado com id: " + id);
    }
}