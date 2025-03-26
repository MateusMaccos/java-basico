public class Gerente extends Usuario{

    Gerente(String nome, String email, String senha) {
        super(nome, email, senha);
        admin = true;
    }

    void gerarRelatorioFinanceiro(){
        System.out.println("Gerando relatorio financeiro...");
    }

    void consultarVendas(){
        
    }


}
