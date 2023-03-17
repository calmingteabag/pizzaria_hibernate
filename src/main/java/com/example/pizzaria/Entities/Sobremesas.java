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
@Table(name = "sobremesas")
public class Sobremesas implements ProdutosInterface {

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

    @OneToMany(mappedBy = "sobremesa", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<PedidosSobremesas> pedidoSobremesa;

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
