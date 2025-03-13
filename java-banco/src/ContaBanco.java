public class ContaBanco {
    final String nomeCliente;
    final String agencia;
    final int numeroConta;
    double saldo;

    public ContaBanco(String nomeCliente,final String agencia,int numeroConta, double saldo) {
        this.nomeCliente = nomeCliente;
        this.agencia = agencia;
        this.numeroConta = numeroConta;
        this.saldo = saldo;
    }
}
