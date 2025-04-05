public class conta implements Iconta {
    private cliente titular;
    private int agencia;
    private int numero;
    private double saldo;
    private banco banco;

    conta(cliente titular, int agencia, int numero, double saldo, banco banco) {
        this.titular = titular;
        this.agencia = agencia;
        this.numero = numero;
        this.saldo = saldo;
        this.banco = banco;
    }

    

    public int getAgencia() {
        return this.agencia;
    }

    public int getNumero() {
        return this.numero;
    }

    public String getTitular() {
        return this.titular.getNome();
    }



    @Override
    public void depositar(double valor) {
        this.saldo += valor;
    }



    @Override
    public void sacar(double valor) {
        this.saldo -= valor;
    }



    @Override
    public double getSaldo() {
        return this.saldo;
    }

    public banco getBanco() {
        return this.banco;
    }

    protected void imprimirExtrato() {
        System.out.println("=== Extrato ===");
        System.out.println("Titular: " + this.titular.getNome());
        System.out.println("Agencia: " + this.agencia);
        System.out.println("Numero: " + this.numero);
        System.out.println("Banco: " + this.banco.getNome());
        System.out.println("Saldo: " + this.saldo);

    }

}
