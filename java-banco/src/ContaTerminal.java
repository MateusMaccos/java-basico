import java.util.Scanner;

public class ContaTerminal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Por favor, digite o número da conta: ");
        int numeroDaConta = scanner.nextInt();

        System.out.println("Por favor, digite o número da agência: ");
        String agencia = scanner.next();

        System.out.println("Por favor, digite o nome do cliente: ");
        String nomeDoCliente = scanner.next();

        System.out.println("Por favor, digite o saldo da conta: ");
        double saldo = scanner.nextDouble();
        scanner.close();
        ContaBanco contaBanco = new ContaBanco( nomeDoCliente, agencia, numeroDaConta, saldo );
        
        System.out.println("Olá "+ contaBanco.nomeCliente +", obrigado por criar uma conta em nosso banco, sua agência é "+contaBanco.agencia+", conta "+contaBanco.numeroConta+" e seu saldo "+contaBanco.saldo+" já está disponível para saque");
    }
}
