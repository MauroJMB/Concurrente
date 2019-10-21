package TPConcurrente4.Ej2;

import java.util.Random;

public class Termometro implements Runnable{
    
    private SalaEspera sala;
    private Random r=new Random();

    public Termometro(SalaEspera sala) {
        this.sala = sala;
    }
    
    @Override
    public void run() {//trye
        try {
            Thread.sleep(2000);
        } catch (Exception e) {}
        sala.notificarTemperatura(35+r.nextInt(10));
    }
    
}
