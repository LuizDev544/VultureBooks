package BookDonation.demo.Domain.Model.ValueObjects;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Senha {

    @Column(name = "senha")
    private String hashSenha;

    public boolean verificarComHash(String senhaDigitada, BCryptPasswordEncoder encoder) {
        return encoder.matches(senhaDigitada, this.hashSenha);
    }
}