import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class ProcessoSeletivo {
    public static void main(String[] args) {
        String [] candidatos = {"Fulano", "Ciclano", "Beltrano","Goku","Vegeta"};
        for (String candidato : candidatos) {
            entrandoEmContato(candidato);
        }
    }

    static void entrandoEmContato(String candidato){
        int tentativasRealizadas = 1;
        boolean continuarTentando = true;
        boolean atendeu = false;
        do{
            atendeu = atender();
            continuarTentando = !atendeu;
            if(continuarTentando){
                tentativasRealizadas++;

            }
            else{
                System.out.println("Contato realizado com sucesso!");
            }
        }while(continuarTentando && tentativasRealizadas<3);

        if(atendeu){
            System.out.println("Conseguimos contato com " + candidato + ", número de tentativas: " + tentativasRealizadas); 
        }else{
            System.out.println("Não conseguimos contato com " + candidato);
        }
    }
    static boolean atender(){
        return new Random().nextInt(3)==1;
    }
    static void imprimirSelecionados(){
        String [] candidatos = {"Fulano", "Ciclano", "Beltrano"};
        System.out.println("Os candidatos selecionados foram: ");
        for (String candidato : candidatos) {
            System.out.println(candidato);
        }
    }
    static void selecaoDeCandudatos(){
        String [] candidatos = {"Fulano", "Ciclano", "Beltrano"};

        int candidatosSelecionados = 0;
        int candidatosAtual = 0;
        double salarioBase = 2000.0;
        while(candidatosSelecionados<5 && candidatosAtual<candidatos.length){
            String candidato = candidatos[candidatosAtual];
            double salarioPretendido = valorPretendido();
            System.out.println("O candidato " + candidato + " Solicitou " + salarioPretendido);
            if(salarioBase>=salarioPretendido){
                candidatosSelecionados++;
                System.out.println("O candidato " + candidato + " foi selecionado para a vaga");
            }
            candidatosAtual++;
        }
    }

    static double valorPretendido(){
        return ThreadLocalRandom.current().nextDouble(1800, 2200);
    }
    static void analisarCandidato(double salarioPretendido){
        double salarioBase = 2000.0;
        if(salarioBase >salarioPretendido){
            System.out.println("Ligar para o candidato");
        }
        else if(salarioBase==salarioPretendido){
            System.out.println("Ligar para o candidato com contra proposta");
        }else{
System.out.println("Aguardando o resultado dos demais candidatos");
        }
    }
}
