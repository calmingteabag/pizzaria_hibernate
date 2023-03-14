/* Por que essa interface existe:
 * 
 * Por que as vezes vc não sabe que tipo de Classe vai chamar. Por exemplo, 
 * vc tem um metodo escrito assim:
 * 
 * public void metodoTest(Pintassilgo passarin) {}
 * 
 * Mas vc não sabe se é a classe Pintassilgo, JoaoBarro, Maritaca, mas sabe que
 * todas elas tem um metodo getNome().
 * 
 * O problema é que se no seu método vc fizer um passarin.getNome() ele vai retornar
 * um erro. Vc pode criar um metodo pra cada passarinho mas é inviável pois podem
 * existir milhoes de aves. Então vc generaliza, fazendo isso:
 * 
 * public void metodoTest(Class<?> passarin) {}
 * 
 * E escreve uma interface, fazendo todas as classes que vc pretende usar implementarem
 * essa interface. Então quando vc fizer um passarin.getNome(), ele vai funcionar e executar
 * o que vc quer.
 */
package com.example.pizzaria.Interfaces;

public interface ProdutosInterface {
    public String getNome();

    public void setNome(String novoNome);

    public String getDescricao();

    public void setDescricao(String novaDescricao);

    public int getPreco();

    public void setPreco(int novoPreco);
}