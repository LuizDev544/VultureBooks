package BookDonation.demo.Domain.Model;

import BookDonation.demo.Domain.Model.ValueObjects.*;
import jakarta.persistence.*;

@Entity
@Table(name = "livros")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo; 

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

    public Livro(String titulo, AnoLivro ano, Autor autor, Descricao descricao, Genero genero, Idioma idioma, Pagina pagina, StatusLivro status) {
        this.titulo = titulo;
        this.ano = ano;
        this.autor = autor;
        this.descricao = descricao;
        this.genero = genero;
        this.idioma = idioma;
        this.pagina = pagina;
        this.status = status;
    }

    public Long getId() { return id; }
    public String getTitulo() { return titulo; }
    public AnoLivro getAno() { return ano; }
    public Autor getAutor() { return autor; }
    public Descricao getDescricao() { return descricao; }
    public Genero getGenero() { return genero; }
    public Idioma getIdioma() { return idioma; }
    public Pagina getPagina() { return pagina; }
    public StatusLivro getStatus() { return status; }
}