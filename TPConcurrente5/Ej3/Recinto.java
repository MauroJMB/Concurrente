package TPConcurrente5.Ej3;

import java.util.concurrent.Semaphore;


public class Recinto {
    
    private Semaphore cantMostradores;
    private Semaphore cantAbridores;
    private Semaphore cantMostradoresPostre;

    public Recinto(Semaphore cantMostradores, Semaphore cantAbridores, Semaphore cantMostradoresPostre) {
        this.cantMostradores = cantMostradores;
        this.cantAbridores = cantAbridores;
        this.cantMostradoresPostre = cantMostradoresPostre;
    }
    
    public void tomarBandeja (int numeroSoldado){
        try {
            cantMostradores.acquire();
            Thread.sleep(100);
        } catch (InterruptedException ex) {}
        System.out.println("El soldado "+numeroSoldado+" ha tomado la bandeja");
        cantMostradores.release();
    }
    
    public void tomarAbridor(int numeroSoldado){
        System.out.println("El soldado "+numeroSoldado+" esta abriendo el refresco");
        try {
            cantAbridores.acquire();
            Thread.sleep(100);
        } catch (Exception e) {}
        System.out.println("El soldado "+numeroSoldado+" ha tomado el abridor");
        cantAbridores.release();
    }
    
    public void tomarPostre(int numeroSoldado){
        try {
            cantMostradoresPostre.acquire();
            Thread.sleep(100);
        } catch (Exception e) {}
        System.out.println("El soldado "+numeroSoldado+" ha tomado su postre");
        cantMostradoresPostre.release();
    }
    
}
