import java.util.List;

public class banco {
    private String nome;
    private List<conta> contas;

    banco(String nome, List<conta> contas) {
        this.nome = nome;
        this.contas = contas;
    }

    public String getNome() {
        return this.nome;
    }

    public List<conta> getContas() {
        return this.contas;
    }
}