public class cliente {
    private String nome;
    private String cpf;
    private String telefone;

    public cliente(String nome, String cpf, String telefone) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
    }

    public String getNome() {
        return this.nome;
    }

    public String getCpf() {        
        return this.cpf;
    }

    public String getTelefone() {
        return this.telefone;
    }
}
