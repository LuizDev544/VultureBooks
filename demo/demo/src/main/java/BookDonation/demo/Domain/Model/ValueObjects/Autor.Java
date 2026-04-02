package BookDonation.demo.Domain.Model.ValueObjects;

import jakarta.persistence.Embeddable;

@Embeddable

public class Autor {

    private String nome;

    public Autor(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do Autor tá vazio");
        }
        
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}