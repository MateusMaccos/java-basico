public abstract class Usuario {
    String nome;
    String email;
    String senha;
    boolean admin;

    public Usuario(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }
    
    void realizarLogin(){
        System.out.println("Realizando login...");
    }
    void realizarLogoff(){
        System.out.println("Realizando logoff...");
    }
    void alterarDados(String nome, String email){
        this.nome = nome;
        this.email = email; 
    }
    void alterarSenha(String senha){
        this.senha = senha;
    }
}
