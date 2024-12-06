package com.example.MarketplaceTechforge4.controllers;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.MarketplaceTechforge4.domain.product.Product;
import com.example.MarketplaceTechforge4.domain.product.ProductRepository;
import com.example.MarketplaceTechforge4.domain.product.RequestProduct;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class ProductController {

    @Autowired
    private ProductRepository repository;

    @GetMapping
    public ResponseEntity<?> getAllProducts() {
        try {
            List<Product> allProducts = repository.findAll();
            return ResponseEntity.ok(allProducts);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao buscar produtos");
        }
    }

    @GetMapping("/promocao")
    public ResponseEntity<?> getAllPromocaoProducts() {
        try {
            List<Product> allPromocaoProducts = repository.findAllByPromocaoTrue();
            return ResponseEntity.ok(allPromocaoProducts);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao buscar produtos em promoção");
        }
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> updateProduct(@RequestBody @Valid RequestProduct data, @PathVariable String id) {
        try {
            Optional<Product> optionalProduct = repository.findById(Integer.parseInt(id));
            if (optionalProduct.isPresent()) {
                Product product = optionalProduct.get();
                product.setNome(data.getNome());
                product.setPreco(data.getPreco());
                product.setDescricao(data.getDescricao());
                product.setUrlImagem(data.getUrlImagem());
                product.setPromocao(data.getPromocao());

                repository.save(product);
                return ResponseEntity.ok(product);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar preço do produto");
        }
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody @Valid RequestProduct data) {
        try {
            Product newProduct = new Product(data);
            Product savedProduct = repository.save(newProduct);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteProduct(@PathVariable String id){
        Optional<Product> optionalProduct = repository.findById(Integer.parseInt(id));
        if (optionalProduct.isPresent()) {
            repository.delete(optionalProduct.get());

            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
