package BookDonation.demo.Domain.Model.ValueObjects;

import java.time.LocalDate;
import jakarta.persistence.Embeddable;

@Embeddable

public class AnoLivro {

    private int ano;

    public AnoLivro(int ano) {
        int anoAtual = LocalDate.now().getYear();
        
        if (ano <= 0 || ano > anoAtual) {
            throw new IllegalArgumentException("Ano do livro inválido. O ano deve ser entre 1 e " + anoAtual);
        }
        
        this.ano = ano;
    }

    public int getAno() {
        return ano;
    }
}