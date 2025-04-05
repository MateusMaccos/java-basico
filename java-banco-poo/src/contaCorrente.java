public class contaCorrente extends conta{

    contaCorrente(cliente titular, int agencia, int numero, double saldo, banco banco) {
        super(titular, agencia, numero, saldo, banco);
    }

    @Override
    public void sacar(double valor) {
        super.sacar(valor + 0.1);        
    }

    @Override
    public void depositar(double valor) {
        super.depositar(valor - 0.1);        
    }

}
