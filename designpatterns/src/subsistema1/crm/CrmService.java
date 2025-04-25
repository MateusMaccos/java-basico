package subsistema1.crm;

public class CrmService {
    private CrmService(){}
    public static void gravarCliente(String nome,String cep,String estado,String cidade){
        System.out.println("Cliente salvo no sistema de CRM");
        System.out.println("Nome: "+nome+", CEP: "+cep+", Estado: "+estado+", Cidade: "+cidade);
    }
}
