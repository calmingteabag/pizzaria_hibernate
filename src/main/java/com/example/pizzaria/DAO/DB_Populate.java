
/*
 * Preenche o DB (Pizzas, Bebidas, Sobremesas) com valores de teste.
*/

package com.example.pizzaria.DAO;

import com.example.pizzaria.Interfaces.Produtos;
import com.example.pizzaria.Utils.HibernateSession;

import java.util.ArrayList;
import java.util.HashMap;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class DB_Populate {

    private HashMap<String, ArrayList<String[]>> produtos = new HashMap<>();

    public DB_Populate() {
    };

    public void populateDBMockupData() {

        ArrayList<String[]> pizzasTest = new ArrayList<>();
        pizzasTest.add(new String[] { "calabresa", "Pizza de Calabresa", "70" });
        pizzasTest.add(new String[] { "portuguesa", "Pizza Portuguesa", "65" });
        pizzasTest.add(new String[] { "marguerita", "Pizza Marguerita", "50" });
        pizzasTest.add(new String[] { "frango_catupiry", "Pizza de Frango com Catupiry", "66" });
        pizzasTest.add(new String[] { "mucarela", "Pizza de Muçarela", "50" });
        pizzasTest.add(new String[] { "milho_catupiry", "Pizza de Milho com Catupiry", "65" });

        ArrayList<String[]> bebidasTest = new ArrayList<>();
        bebidasTest.add(new String[] { "guarana", "Guaraná 2L", "20" });
        bebidasTest.add(new String[] { "coca", "Coca Cola", "20" });
        bebidasTest.add(new String[] { "breja", "Cevada com gás 1L", "10" });
        bebidasTest.add(new String[] { "agua", "Agua da bica da SABESP", "5" });
        bebidasTest.add(new String[] { "groselha", "Groselha (50% água, 50% açucar, 1% groselha)", "30" });

        ArrayList<String[]> sobremesasTest = new ArrayList<>();
        sobremesasTest.add(new String[] { "sonho", "Sonho", "20" });
        sobremesasTest.add(new String[] { "brigadeiro", "Doce de Brigadeiro", "10" });
        sobremesasTest.add(new String[] { "doce_leite", "Doce de Leite", "5" });
        sobremesasTest.add(new String[] { "rapadura", "Rapadura", "15" });
        sobremesasTest.add(new String[] { "pacoca", "Paçoca", "5" });

        produtos.put("pizzas", new ArrayList<>(pizzasTest));
        produtos.put("bebidas", new ArrayList<>(bebidasTest));
        produtos.put("sobremesas", new ArrayList<>(sobremesasTest));
    };

    public void productsInsert(Produtos entidadeHibernate, String tableName, String checkDuplicateColumn) {
        Session session = HibernateSession.getSession();
        ArrayList<String[]> productList = produtos.get(tableName);
        ChecagensBancoDados checkDb = new ChecagensBancoDados();

        for (int i = 0; i < productList.size(); i++) {
            String nome = productList.get(i)[0];

            if (!checkDb.isDuplicate(tableName, checkDuplicateColumn, nome)) {

                System.out.println(checkDb.isDuplicate(tableName, "nome", nome));
                String descricao = productList.get(i)[1];
                int preco = Integer.parseInt(productList.get(i)[2]);

                entidadeHibernate.setNome(nome);
                entidadeHibernate.setDescricao(descricao);
                entidadeHibernate.setPreco(preco);

                Transaction transaction = session.beginTransaction();
                session.merge(entidadeHibernate);
                transaction.commit();

            }
        }
    };
}
