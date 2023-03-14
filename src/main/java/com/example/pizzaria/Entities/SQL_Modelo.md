# Organização do SQL

## Database

PostgreSQL

## Relações

Clientes -> OneToMany <-> Pedidos <-> ManyToMany <-> Pizzas
                                  <-> ManyToMany <-> Bebidas
                                  <-> ManyToMany <-> Sobremesas
                                  <-> OneToOne   <-> PedidosDetalhes

## Processo para gerar os pedidos

- Sabemos que o objeto Pedidos (no caso desse App) recebe 4 argumentos:
    - Objeto cliente
    - List de Pizzas
    - List de Bebidas
    - List de Sobremesas

- Então basicamente é criar cada argumento separadamente, ou melhor, busca-los
no DB:

Clientes cliente = session.get(Clientes.class, clienteId)
 
- No caso dos pedidos, precisa-se manter a estrutura que está na Entity
List<Pizzas> insertPizzasPedido = new ArrayList<Pizzas>() 

- E preenchemos essa List (insertPizzasPedido) "lendo" o pedido do cliente
e fazendo queries no DB. Devem existir maneiras melhores, a que uso é essa:

SelectionQuery<?> dbQuery = session.createNativeQuery("SELECT FROM etc etc",
Pizzas.class)

List<?> dbSearchResultList = dbQuery.getResultList();

Pizza dbObject = dbSearchResultList.get(0);

insertPizzasPedido.add(dbObject)

- Depois que fizermos isso com todos os argumentos (pizzas, bebidas e sobremesas) basta
criar o objeto pedidos com os argumentos, session.merge() e transaction.commit()

Pedidos novoPedido = new Pedidos(cliente, pizzas, bebidas, sobremesas);
Transaction transaction = session.beginTransaction();
session.merge(novoPedido);
transaction.commit();

E o pedido está criado.
