@Entity
@Table(nome = "admin") 
@Getter
@NoArgsConstructor
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String senha;
    private String nome;

    @Column(nullable = false)
    private String role = "ROLE_ADMIN";
}

/*

@Entity
@Table(name = "admins") // Plural é uma boa prática para nomes de tabela
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // Protege o construtor vazio para uso exclusivo do JPA
@AllArgsConstructor
@Builder // Design Pattern: Builder para facilitar a criação de objetos complexos
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false)
    private String password; // Alterado para inglês e mantendo padrão camelCase

    @Column(nullable = false, length = 150)
    private String name;

    @Column(nullable = false, length = 20)
    private String role = "ROLE_ADMIN";

    // Exemplo de regra do Object Calisthenics: encapsular comportamento
    public void updateName(String newName) {
        if (newName == null || newName.isBlank()) {
            throw new IllegalArgumentException("O nome não pode ser vazio");
        }
        this.name = newName;
    }
}

*/
