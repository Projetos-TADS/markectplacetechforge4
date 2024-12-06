package com.example.MarketplaceTechforge4.domain.purchase;
import java.io.Serializable;
import java.util.Objects;

public class PurchaseItemId implements Serializable {

    private Integer pedidoId;
    private Integer produtoId;

    public PurchaseItemId() {}

    public PurchaseItemId(Integer pedidoId, Integer produtoId) {
        this.pedidoId = pedidoId;
        this.produtoId = produtoId;
    }

    public Integer getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(Integer pedidoId) {
        this.pedidoId = pedidoId;
    }

    public Integer getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Integer produtoId) {
        this.produtoId = produtoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PurchaseItemId that = (PurchaseItemId) o;
        return Objects.equals(pedidoId, that.pedidoId) &&
                Objects.equals(produtoId, that.produtoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pedidoId, produtoId);
    }
}