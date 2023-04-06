package com.example.pizzaria.DAO;

import com.example.pizzaria.Interfaces.Produtos;

import org.hibernate.Session;
import org.hibernate.query.SelectionQuery;

import com.example.pizzaria.Utils.HibernateSession;

// Olhando agora, esses métodos parecem redundantes, pois uma busca pode ser 
// feita simplesmente com selectionquery ou outro metodo da session, então usar
// essa classe adiciona um passo extra talvez desnecessário no codigo.
public class BuscaProdutos {

    public BuscaProdutos() {
    }

    public Produtos buscaProdutoPorId(int idProduto, Class<? extends Produtos> classeProduto) {
        Session session = HibernateSession.getSession();
        Produtos resultProduto = session.get(classeProduto, idProduto);
        session.close();

        return resultProduto;
    }

    public Produtos buscaProdutoPorNome(String nomeTabela, String nomeColuna,
            String nomeProduto, Class<? extends Produtos> classeProduto) {
        Session session = HibernateSession.getSession();
        String buscaString = String.format("SELECT * FROM %s WHERE %s = :valor", nomeTabela,
                nomeColuna);

        SelectionQuery<?> buscaProdutos = session.createNativeQuery(buscaString, classeProduto);
        buscaProdutos.setParameter("valor", nomeProduto);
        Produtos produto = (Produtos) buscaProdutos.getSingleResult();

        return produto;
    }

}
