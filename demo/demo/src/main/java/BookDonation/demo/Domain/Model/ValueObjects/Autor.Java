package BookDonation.demo.Domain.Model.ValueObjects;

import jakarta.persistence.Embeddable;

@Embeddable
public class Autor {

    private String autor;

    protected Autor() {}

    public Autor(String autor) {
        if (autor == null || autor.trim().isEmpty()) {
            throw new IllegalArgumentException("nome do Autor tá vazio");
        }
        
        this.autor = autor;
    }

    public String getAutor() {
        return autor;
    }
}