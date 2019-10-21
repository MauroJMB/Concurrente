package TPConcurrente5.Ej3;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Main {
    
    public static void main(String[] args) {
        
        Semaphore semaforoMostradores=new Semaphore(5, true);
        Semaphore semaforoAbridores=new Semaphore(10, true);
        Semaphore semaforoMostradoresPostre=new Semaphore(3, true);
        Recinto recinto=new Recinto(semaforoMostradores, semaforoAbridores, semaforoMostradoresPostre);
        Soldado[] arregloSoldados=new Soldado[100];
        Thread[] arregloHilos=new Thread[100];
        Random r=new Random();
        
        for (int i = 0; i < 100; i++) {
            arregloSoldados[i]=new Soldado(i, r.nextBoolean(), recinto, r.nextBoolean());
        }
        
        for (int i = 0; i < 100; i++) {
            arregloHilos[i]=new Thread(arregloSoldados[i]);
            arregloHilos[i].start();
        }
        
    }
    
}
