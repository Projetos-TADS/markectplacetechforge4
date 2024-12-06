package com.example.MarketplaceTechforge4.controllers;
import com.example.MarketplaceTechforge4.domain.purchase.*;
import com.example.MarketplaceTechforge4.domain.product.Product;
import com.example.MarketplaceTechforge4.domain.user.User;
import com.example.MarketplaceTechforge4.domain.product.ProductRepository;
import com.example.MarketplaceTechforge4.domain.purchase.PurchaseRepository;
import com.example.MarketplaceTechforge4.domain.user.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/pedidos")
public class PurchaseController {

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<?> createPurchase(@RequestBody @Valid RequestPurchase data) {
        try {
            User usuario = userRepository.findById(data.getUsuarioId())
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

            Purchase purchase = new Purchase();
            purchase.setUsuario(usuario);
            purchase = purchaseRepository.save(purchase);

            for (RequestPurchaseItem requestItem : data.getItens()) {
                Product produto = productRepository.findById(requestItem.getProdutoId())
                        .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

                PurchaseItem purchaseItem = new PurchaseItem();
                purchaseItem.setPedidoId(purchase.getId());
                purchaseItem.setProdutoId(produto.getId());
                purchaseItem.setQuantidade(requestItem.getQuantidade());

                purchase.getItens().add(purchaseItem);
            }

            purchase = purchaseRepository.save(purchase);

            purchase = purchaseRepository.findById(purchase.getId())
                    .orElseThrow(() -> new RuntimeException("Pedido não encontrado após salvar"));


            return ResponseEntity.status(HttpStatus.CREATED).body(purchase);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao criar pedido: " + e.getMessage());
        }
    }
}