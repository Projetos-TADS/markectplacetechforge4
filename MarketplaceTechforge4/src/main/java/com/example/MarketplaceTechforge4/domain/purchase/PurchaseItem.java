package com.example.MarketplaceTechforge4.domain.purchase;
import com.example.MarketplaceTechforge4.domain.product.Product;
import jakarta.persistence.*;

@Entity
@Table(name = "item_pedido")
@IdClass(PurchaseItemId.class)
public class PurchaseItem {

    @Id
    @Column(name = "pedido_id")
    private Integer pedidoId;

    @Id
    @Column(name = "produto_id")
    private Integer produtoId;

    @ManyToOne
    @JoinColumn(name = "pedido_id", insertable = false, updatable = false)
    private Purchase pedido;

    @ManyToOne
    @JoinColumn(name = "produto_id", insertable = false, updatable = false)
    private Product produto;

    @Column(nullable = false)
    private int quantidade;

    public PurchaseItem() {}

    public PurchaseItem(Purchase pedido, Product produto, int quantidade) {
        this.pedido = pedido;
        this.produto = produto;
        this.quantidade = quantidade;
        this.pedidoId = pedido.getId();
        this.produtoId = produto.getId();
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

    public Purchase getPedido() {
        return pedido;
    }

    public void setPedido(Purchase pedido) {
        this.pedido = pedido;
    }

    public Product getProduto() {
        return produto;
    }

    public void setProduto(Product produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}