import java.util.ArrayList;

public class main {
    public static void main(String[] args) {
        cliente cliente1 = new cliente("Joaquim", "123", "1234");
        banco banco1 = new banco("Banco do Joaquim", new ArrayList<conta>());
        conta conta1 = new contaCorrente(cliente1, 1, 1, 100, banco1);

        conta1.imprimirExtrato();
    }
}
