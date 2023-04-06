# Estrutura do Banco de Dados

Clientes -< Pedidos -<PedidosPizzas>- Pizzas
                    -<PedidosBebidas>- Bebidas
                    -<PedidosSobremesas>- Sobremesas

Construí as tabelas intermediárias manualmente pois precisava
guardar informações extras sobre os 'pedidos_____' tais como
a chave do map (que se usa em Pedidos e em Pizzas, por exemplo),
a quantidade, e outros atributos.