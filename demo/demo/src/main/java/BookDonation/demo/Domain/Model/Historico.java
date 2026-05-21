package BookDonation.demo.Domain.Model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "historicos")
public class Historico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String acao;
    private Long idLivro;
    private Long idAdmin;
    private LocalDateTime dataHora;

    protected Historico() {}

    public Historico(String acao, Long idLivro, Long idAdmin) {
        this.acao = acao;
        this.idLivro = idLivro;
        this.idAdmin = idAdmin;
        this.dataHora = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public String getAcao() { return acao; }
    public Long getIdLivro() { return idLivro; }
    public Long getIdAdmin() { return idAdmin; }
    public LocalDateTime getDataHora() { return dataHora; }
}