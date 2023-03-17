package com.example.pizzaria.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Table;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

import com.example.pizzaria.Interfaces.ProdutosInterface;

import java.util.List;

@Entity
@Table(name = "bebidas")
public class Bebidas implements ProdutosInterface {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "bebida_id")
    private int bebidaId;

    @Column(name = "nome", unique = true)
    private String bebidaNome;

    @Column(name = "descricao")
    private String bebidaDescricao;

    @Column(name = "preco")
    private int bebidaPreco;

    @OneToMany(mappedBy = "bebida", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<PedidosBebidas> pedidoBebida;

    public Bebidas() {
    };

    public Bebidas(String bebidasNome, String bebidasDescricao, int bebidasPreco) {
        this.bebidaNome = bebidasNome;
        this.bebidaDescricao = bebidasDescricao;
        this.bebidaPreco = bebidasPreco;
    }

    public int getId() {
        return bebidaId;
    };

    public void setNome(String novoNome) {
        this.bebidaNome = novoNome;
    };

    public String getNome() {
        return bebidaNome;
    };

    public void setDescricao(String novaDescricao) {
        this.bebidaDescricao = novaDescricao;
    }

    public String getDescricao() {
        return bebidaDescricao;
    }

    public void setPreco(int novoPreco) {
        this.bebidaPreco = novoPreco;
    }

    public int getPreco() {
        return bebidaPreco;
    }

    public List<PedidosBebidas> getPedidosPizzas() {
        return pedidoBebida;
    };

    public void setPedidosBebidas(List<PedidosBebidas> newPedidosBebidas) {
        this.pedidoBebida = newPedidosBebidas;
    }
}
