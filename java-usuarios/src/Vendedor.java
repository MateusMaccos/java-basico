public class Vendedor extends Usuario{

    int qntDeVendas;
    Vendedor(String nome, String email, String senha) {
        super(nome, email, senha);
        admin=false;
    }
    void realizarVenda(){
        qntDeVendas++;	    
    }

    void consultarVendas(){
        System.out.println("Quantidade de vendas: " + qntDeVendas);
    }
}
