@Entity
@Table(name = "livro")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Embedded // <-- Ele CONFIA que vai receber o valor certo 
    private Autor autor;

    @Embedded
    private DetalhesLivros detalhes;

    @Enumerated(EnumType.STRING)
    private StatusLivro condicao;

    public Livro(String titulo, Autor autor, DetalhesLivros detalhes) {
        this.titulo = Objects.requireNonNull(titulo, "Título é obrigatório");
        this.autor = Objects.requireNonNull(autor, "Autor é obrigatório");
        this.detalhes = Objects.requireNonNull(detalhes, "Detalhes são obrigatórios");
        this.condicao = StatusLivro.DISPONIVEL;
    }

    public void marcarComoDoado() {
        if (this.condicao == StatusLivro.DOADO) {
            throw new IllegalStateException("Livro já foi doado.");
        }
        this.condicao = StatusLivro.DOADO;
    }
}