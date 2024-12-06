package com.example.MarketplaceTechforge4.domain.user;
import jakarta.persistence.*;

@Table(name = "usuario")
@Entity(name = "usuario")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome", length = 50, nullable = false)
    private String nome;

    @Column(name = "email", length = 120, nullable = false)
    private String email;

    public User(RequestUser requestUser) {
        this.nome = requestUser.getNome();
        this.email = requestUser.getEmail();
    }

    public User() {}

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
