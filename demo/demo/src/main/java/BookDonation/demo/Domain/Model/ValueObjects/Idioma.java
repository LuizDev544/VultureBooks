package BookDonation.demo.Domain.Model.ValueObjects;

import jakarta.persistence.Embeddable;

@Embeddable

public class Idioma {

    private String idioma;

    public Idioma(String idioma) {
        if (idioma == null || idioma.trim().isEmpty()) {
            throw new IllegalArgumentException("Idioma do livro não pode ser vazio.");
        }
        
        this.idioma = idioma;
    }

    public String getIdioma() {
        return idioma;
    }
}