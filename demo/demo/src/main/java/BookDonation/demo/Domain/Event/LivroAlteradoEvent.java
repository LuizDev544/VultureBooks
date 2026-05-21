package BookDonation.demo.Domain.Event;

public class LivroAlteradoEvent {
    private final Long livroId;
    private final String titulo;
    private final String acao;
    private final Long adminId;

    public LivroAlteradoEvent(Long livroId, String titulo, String acao, Long adminId) {
        this.livroId = livroId;
        this.titulo = titulo;
        this.acao = acao;
        this.adminId = adminId;
    }

    public Long getLivroId() {
        return livroId;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAcao() {
        return acao;
    }

    public Long getAdminId() {
        return adminId;
    }
}
