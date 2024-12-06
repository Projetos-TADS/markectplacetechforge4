package com.example.MarketplaceTechforge4.domain.purchase;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public class RequestPurchase {
        @NotNull
        private Integer usuario_id;

        @NotNull
        private List<RequestPurchaseItem> itens;

        public Integer getUsuarioId() {
                return usuario_id;
        }

        public void setUsuarioId(Integer usuarioId) {
                this.usuario_id = usuarioId;
        }

        public List<RequestPurchaseItem> getItens() {
                return itens;
        }

        public void setItens(List<RequestPurchaseItem> itens) {
                this.itens = itens;
        }
};