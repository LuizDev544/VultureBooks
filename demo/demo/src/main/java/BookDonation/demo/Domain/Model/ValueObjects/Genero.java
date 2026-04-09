package BookDonation.demo.Domain.Model.ValueObjects;

import jakarta.persistence.Embeddable;

@Embeddable

public class Genero {

    private String genero;

    protected Genero() {}

    public Genero(String genero) {
        if (genero == null || genero.trim().isEmpty()) {
            throw new IllegalArgumentException("O genero do livro não pode ser vazio.");
        }

        this.genero = genero ;
    }

    public String getNome () {
        return genero;
    }
}