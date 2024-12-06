package com.example.MarketplaceTechforge4.domain.product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

public class RequestProduct {

        @NotBlank
        @Size(max = 120)
        private String nome;

        @NotNull
        private BigDecimal preco;

        @NotNull
        private Boolean promocao;

        @NotBlank
        private String descricao;

        @NotBlank
        @Size(max = 500)
        private String urlImagem;

        public String getNome() {
                return nome;
        }

        public Boolean getPromocao() {
                return promocao;
        }

        public void setNome(String nome) {
                this.nome = nome;
        }

        public BigDecimal getPreco() {
                return preco;
        }

        public void setPreco(BigDecimal preco) {
                this.preco = preco;
        }

        public String getDescricao() {
                return descricao;
        }

        public void setDescricao(String descricao) {
                this.descricao = descricao;
        }

        public String getUrlImagem() {
                return urlImagem;
        }

        public void setUrlImagem(String urlImagem) {
                this.urlImagem = urlImagem;
        }
}