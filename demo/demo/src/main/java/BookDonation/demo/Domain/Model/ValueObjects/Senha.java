package BookDonation.demo.Domain.Model.ValueObjects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.util.Optional;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Senha {
    
    @Column(name = "senha")
    private String valor;

    public Senha(String valor) {
        validar(valor);
        this.valor = valor;
    }

    private void validar(String valor) {
        Optional.ofNullable(valor)
                .filter(texto -> texto.length() >= 8)
                .orElseThrow(() -> new IllegalArgumentException("A senha deve conter pelo menos 8 caracteres."));
    }

    public boolean verificarComHash(String senhaDigitada, BCryptPasswordEncoder encoder) {
        return encoder.matches(senhaDigitada, this.valor);
    }
}