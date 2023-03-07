package com.example.pizzaria.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "sobremesas")
public class Sobremesas implements ProdutosInterface {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private int sobremesaId;

    @Column(name = "nome")
    private String sobremesaNome;

    @Column(name = "descricao")
    private String sobremesaDescricao;

    @Column(name = "preco")
    private int sobremesaPreco;

    @ManyToMany(mappedBy = "pedidoSobremesas", fetch = FetchType.EAGER)
    private List<Pedidos> pedidos = new ArrayList<>();

    public Sobremesas() {
    };

    public Sobremesas(String sobremesaNome, String sobremesaDescricao, int sobremesaPreco) {
        this.sobremesaNome = sobremesaNome;
        this.sobremesaDescricao = sobremesaDescricao;
        this.sobremesaPreco = sobremesaPreco;
    }

    public void setNome(String novoNome) {
        this.sobremesaNome = novoNome;
    };

    public String getNome() {
        return sobremesaNome;
    };

    public void setDescricao(String novaDescricao) {
        this.sobremesaDescricao = novaDescricao;
    }

    public String getDescricao() {
        return sobremesaDescricao;
    }

    public void setPreco(int novoPreco) {
        this.sobremesaPreco = novoPreco;
    }

    public int getPreco() {
        return sobremesaPreco;
    }

}
