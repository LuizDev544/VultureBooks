package BookDonation.demo.Domain.Model.ValueObjects;

import jakarta.persistence.Embeddable;

@Embeddable

public class Pagina {

    private int paginas;

    protected Pagina() {}

    public Pagina(int  paginas) {
        if (paginas <= 0) {
            throw new IllegalArgumentException("Número de páginas inválido. Deve ser um valor positivo.");
        }
        
        this.paginas = paginas;
    }

    public int getQuantidade() {
        return paginas;
    }
}