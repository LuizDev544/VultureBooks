package BookDonation.demo.Domain.Model.ValueObjects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.util.Optional;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Email {

    @Column(name = "endereco")
    private String endereco;

    public Email(String endereco) {
        this.endereco = Optional.ofNullable(endereco)
                .filter(texto -> texto.matches("^[A-Za-z0-9+_.-]+@(.+)$"))
                .orElseThrow(() -> new IllegalArgumentException("Endereco de email invalido"));
    }

    public String getEndereco() {
        return this.endereco;
    }
}