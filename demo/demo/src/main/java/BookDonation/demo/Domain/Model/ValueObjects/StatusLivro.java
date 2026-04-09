package BookDonation.demo.Domain.Model.ValueObjects;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Embeddable
public class StatusLivro {

    public enum TipoStatus {
        DISPONIVEL,
        DOADO,
        INDISPONIVEL
    }

    @Enumerated(EnumType.STRING)
    private TipoStatus valor;

    protected StatusLivro() {}

    public StatusLivro(String status) {
        try {
            this.valor = TipoStatus.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Status inválido! Use: DISPONIVEL, RESERVADO ou DOADO.");
        }
    }
    
    public boolean isDisponivel() {
        return this.valor == TipoStatus.DISPONIVEL;
    }

    public String getStatus() {
        return valor.name();
    }
}