package BookDonation.demo.Domain.Model;

import BookDonation.demo.Domain.Model.ValueObjects.*;
import jakarta.persistence.*;

@Entity
@Table(name = "livros")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private AnoLivro ano;

    @Embedded
    private Autor autor;

    @Embedded
    private Descricao descricao;

    @Embedded
    private Genero genero;

    @Embedded
    private Idioma idioma;

    @Embedded
    private Pagina pagina;

    @Embedded 
    private StatusLivro status;

    protected Livro() {}

    public Livro(AnoLivro ano, Autor autor, Descricao descricao, Genero genero, Idioma idioma, Pagina pagina, StatusLivro status) {
        this.ano = ano;
        this.autor = autor;
        this.descricao = descricao;
        this.genero = genero;
        this.idioma = idioma;
        this.pagina = pagina;
        this.status = status;
    }
}