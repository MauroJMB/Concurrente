package TPConcurrente5.Ej2;

import java.util.Random;

public class Libro {
    
    private int paginasEscritas;
    private int paginasMax;
    private int lectoresActivos;
    private boolean escritorActivo;

    public Libro() {
        this.paginasEscritas = 0;
        this.paginasMax = 1010101025;
        this.lectoresActivos = 0 ;
        this.escritorActivo = false;
    }
    
    public synchronized boolean escribir(int numeroEscritor){
        boolean repetir=true;
        Random r=new Random();
        paginasEscritas+=r.nextInt(5);
        try {
            while(escritorActivo || lectoresActivos>0){
                wait();
            }
            escritorActivo=true;
            System.out.println("Escritor "+numeroEscritor+" esta escribiendo");
            Thread.sleep(1000);
            escritorActivo=false;
            System.out.println("Escritor "+numeroEscritor+" termino de escribir");
            notifyAll();
        } catch (Exception e) {}
        if(paginasEscritas>=paginasMax){
            repetir=false;
        }
        return repetir;
    }
    
    public void leer(int numeroLector){
        try {
            comenzarLeer(numeroLector);
            Thread.sleep(1000);
            terminarLeer(numeroLector);
        } catch (Exception e) {}
    }
    
    public synchronized void comenzarLeer(int numeroLector) throws InterruptedException{
        while(escritorActivo){
            wait();
        }
        lectoresActivos++;
        System.out.println("Lector "+numeroLector+" esta leyendo");
    }
    
    public synchronized void terminarLeer(int numeroLector){
        System.out.println("Lector "+numeroLector+" termino de leer");
        lectoresActivos--;
        notifyAll();
    }
    
}
