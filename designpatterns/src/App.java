import gof.facade.Facade;
import gof.strategy.Comportamento;
import gof.strategy.ComportamentoAgressivo;
import gof.strategy.ComportamentoDefensivo;
import gof.strategy.ComportamentoNormal;
import gof.strategy.Robo;

public class App {
    public static void main(String[] args) throws Exception {
        Comportamento defensivo = new ComportamentoDefensivo();
        Comportamento normal = new ComportamentoNormal();
        Comportamento agressivo = new ComportamentoAgressivo();

        Robo robo = new Robo();

        robo.setComportamento(defensivo);
        robo.mover();
        
        robo.setComportamento(normal);
        robo.mover();

        robo.setComportamento(agressivo);
        robo.mover();
        
        Facade facade = new Facade();
        facade.migrarCliente("Joao", "123456");
    }
}
