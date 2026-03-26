@Entity
@Table(name = "livros")
@Getter
@NoArgsConstructor
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String autor;
    private String descricao;
    private Integer anoPublicacao;
    private Integer quantidadePaginas;
    private Integer edicao;
    private String idioma;

    @Enumerated(EnumType.STRING)
    private StatusLivro status;

    private Double preco;

    public void marcarComoDoado() {
        this.status = StatusLivro.DOADO;
    }

    public void marcarComoDisponivel() {
        this.status = StatusLivro.DISPONIVEL;
    }
}