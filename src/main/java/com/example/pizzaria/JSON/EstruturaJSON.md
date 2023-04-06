# Estrutura JSON

Esse JSON representa "mais ou menos" um recibo, como dos que recebemos ao
fazer uma compra em um supermercado.

O modelo dele em java ficou assim:

```
JSONPedido {
    InfoCliente {
        idCliente
        nomeCliente
    }
    InfoPedido {
        idPedido
        Produto {
            descricao
            quantidade
            valorUnitario
            totalProduto
        }
        totalParcial
        descontos
        totalFinal
    }
}
```


