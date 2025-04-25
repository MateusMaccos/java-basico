package subsistema2.cep;

public class CepApi {
    private static CepApi instance = new CepApi();
    private CepApi() {}

    public static CepApi getInstance() {
        return instance;
    }

    public String recuperarCidade(String cep){
        return "Rio de Janeiro";
    }

    public String recuperarEstado(String cep){
        return "RJ";
    }
}
