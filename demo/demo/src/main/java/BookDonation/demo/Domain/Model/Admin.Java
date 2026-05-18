package BookDonation.demo.Domain.Model;

import java.util.List;
import java.util.Optional;
import BookDonation.demo.Domain.Model.ValueObjects.Email;
import BookDonation.demo.Domain.Model.ValueObjects.Senha;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
@Table(name = "admin")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Email email;

    @Embedded
    private Senha senha;

    @OneToMany(mappedBy = "adminRegistrador") 
    private List<Livro> livrosCadastrados;

    public Admin(Email email, Senha senha) {
        this.email = email;
        this.senha = senha;
    }

    public boolean autenticarComCriptografia(String senhaDigitada, BCryptPasswordEncoder encoder) {
        return Optional.ofNullable(this.senha)
                .map(valorSenha -> Boolean.valueOf(valorSenha.verificarComHash(senhaDigitada, encoder)))
                .orElse(false);
    }

    public Long getId() {
        return this.id;
    }
}