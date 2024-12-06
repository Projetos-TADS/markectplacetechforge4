package com.example.MarketplaceTechforge4.domain.purchase;
import jakarta.validation.constraints.NotNull;

public class RequestPurchaseItem {

        @NotNull
        private Integer produto_id;

        @NotNull
        private Integer quantidade;

        public Integer getProdutoId() {
                return produto_id;
        }

        public void setProdutoId(Integer produto_id) {
                this.produto_id = produto_id;
        }

        public Integer getQuantidade() {
                return quantidade;
        }

        public void setQuantidade(Integer quantidade) {
                this.quantidade = quantidade;
        }

}
