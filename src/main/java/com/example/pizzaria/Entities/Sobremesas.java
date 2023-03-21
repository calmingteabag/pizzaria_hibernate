package com.example.pizzaria.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Table;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import com.example.pizzaria.Interfaces.Produtos;

import java.util.List;

import java.time.LocalDateTime;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "sobremesas")
public class Sobremesas implements Produtos {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "sobremesa_id")
    private int sobremesaId;

    @Column(name = "nome")
    private String sobremesaNome;

    @Column(name = "descricao")
    private String sobremesaDescricao;

    @Column(name = "preco")
    private int sobremesaPreco;

    @OneToMany(mappedBy = "sobremesa", fetch = FetchType.EAGER, cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    private List<PedidosSobremesas> pedidoSobremesa;

    @CreationTimestamp
    @Column(name = "data_criacao")
    private LocalDateTime horaCriada;

    @UpdateTimestamp
    @Column(name = "data_modificacao")
    private LocalDateTime horaModificada;

    public Sobremesas() {
    };

    public Sobremesas(String sobremesaNome, String sobremesaDescricao, int sobremesaPreco) {
        this.sobremesaNome = sobremesaNome;
        this.sobremesaDescricao = sobremesaDescricao;
        this.sobremesaPreco = sobremesaPreco;
    }

    public int getId() {
        return sobremesaId;
    };

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

    public List<PedidosSobremesas> getPedidosSobremesas() {
        return pedidoSobremesa;
    };

    public void setPedidosSobremesas(List<PedidosSobremesas> newPedidosSobremesas) {
        this.pedidoSobremesa = newPedidosSobremesas;
    }
}
