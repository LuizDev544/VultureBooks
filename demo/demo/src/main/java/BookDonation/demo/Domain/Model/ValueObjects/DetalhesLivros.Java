@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DetalhesLivros {

    private String descricao;
    
    @Column(name = "ano_publicacao")
    private Integer ano;
    
    @Column(name = "quantidade_paginas")
    private Integer paginas;
    
    private String idioma;
    private String genero;

    public DetalhesLivros(String descricao, Integer ano, Integer paginas, String idioma, String genero) {
        if (paginas != null && paginas < 0) {
            throw new IllegalArgumentException("Quantidade de páginas não pode ser negativa.");
        }
        this.descricao = descricao;
        this.ano = ano;
        this.paginas = paginas;
        this.idioma = idioma;
        this.genero = genero;
    }
}