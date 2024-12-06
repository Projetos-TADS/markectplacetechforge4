package com.example.MarketplaceTechforge4.domain.purchase;
import com.example.MarketplaceTechforge4.domain.user.User;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Table(name = "pedido")
@Entity(name = "pedido")
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private User usuario;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal valor = BigDecimal.ZERO;

    @Column(name = "data_pedido", insertable = false, updatable = false)
    private LocalDateTime data_pedido;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PurchaseItem> itens = new ArrayList<>();

    public Purchase() {}

    public Purchase(User usuario, List<PurchaseItem> itens) {
        this.usuario = usuario;
        this.itens = itens;
        this.valor = calcularValorTotal();
    }

    public Integer getId() {
        return id;
    }

    public User getUsuario() {
        return usuario;
    }

    public void setUsuario(User usuario) {
        this.usuario = usuario;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public LocalDateTime getDataPedido() {
        return data_pedido;
    }

    public List<PurchaseItem> getItens() {
        return itens;
    }

    public void setItens(List<PurchaseItem> itens) {
        this.itens = itens;
        this.valor = calcularValorTotal();
    }

    private BigDecimal calcularValorTotal() {
        return itens.stream()
                .map(item -> item.getProduto().getPreco().multiply(new BigDecimal(item.getQuantidade())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}