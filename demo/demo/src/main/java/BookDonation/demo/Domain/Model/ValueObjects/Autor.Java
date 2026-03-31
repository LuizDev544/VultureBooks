@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Autor {

    @Column(name = "autor", nullable = false)
    private String nome;

    public Autor(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do Autor tá vazio");
        }
        this.nome = nome;
    }
}