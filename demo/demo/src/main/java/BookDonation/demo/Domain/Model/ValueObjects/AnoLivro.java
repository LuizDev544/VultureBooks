package BookDonation.demo.Domain.Model.ValueObjects;

import jakarta.persistence.Embeddable;

@Embeddable
public class AnoLivro {

    private int ano;

    protected AnoLivro() {}

    public AnoLivro(int ano) {
        if (ano <= 0 || ano > 2026) { 
            throw new IllegalArgumentException("Ano do Livro inválido");
        }
        this.ano = ano;
    }
    
    public int getAno(){ 
        return ano; 
    }
}