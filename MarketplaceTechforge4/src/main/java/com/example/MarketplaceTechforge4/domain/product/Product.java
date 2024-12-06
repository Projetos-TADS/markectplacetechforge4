package com.example.MarketplaceTechforge4.domain.product;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Table(name = "produto")
@Entity(name = "produto")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome", length = 120, nullable = false)
    private String nome;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal preco;

    private Boolean promocao;

    @Column(name = "descricao", nullable = false)
    private String descricao;

    @Column(name = "url_imagem", length = 500)
    private String url_imagem;

    public Product(RequestProduct requestProduct) {
        this.nome = requestProduct.getNome();
        this.preco = requestProduct.getPreco();
        this.descricao = requestProduct.getDescricao();
        this.url_imagem = requestProduct.getUrlImagem();
        this.promocao = requestProduct.getPromocao();
    }

    public Product() {}

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getUrlImagem() {
        return url_imagem;
    }

    public Boolean getPromocao() {
        return promocao;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setPromocao(Boolean promocao) {
        this.promocao = promocao;
    }

    public void setUrlImagem(String url_imagem) {
        this.url_imagem = url_imagem;
    }
}