package BookDonation.demo.Domain.Model.ValueObjects;

import jakarta.persistence.Embeddable;

@Embeddable
public class Descricao {

    private String descricao;

    protected Descricao() {}

    public Descricao(String Descricao) {
        if (Descricao == null || Descricao.trim().isEmpty()) {
            throw new IllegalArgumentException("Descricao do Autor tá vazio");
        }
        
        this.descricao = Descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}