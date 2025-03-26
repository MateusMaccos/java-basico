public class Atendente extends Usuario{
    private float valorEmCaixa;

    Atendente(String nome, String email, String senha) {
        super(nome, email, senha);
        admin = false;
    }

    public float getValorEmCaixa() {
        return valorEmCaixa;
    }

    void receberPagamento(float valor){
        valorEmCaixa += valor;
    }

    void fecharCaixa(){
        System.out.println("O valor em caixa foi de: " + valorEmCaixa);
    }
}
