package TPConcurrente5.Ej5;

import java.util.Random;

public class Auto extends Thread{
    
    private int numero;
    private boolean NS;//si falase, va SN
    private Puente puente;

    public Auto(int numero, boolean sentido, Puente puente) {
        this.numero = numero;
        this.NS = sentido;
        this.puente = puente;
    }
    
    @Override
    public void run() {
        while(true){
            puente.entrar(NS, numero);
            try {
                sleep(1000);
            } catch (Exception e) {
            }
        }
    }
    
}
