
package TPConcurrente4.Ej2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SalaEspera {
    
    private int temp;
    private int cantidadPersonasEnSala;
    private int cantJubiladosEnCola;
    private int cantPersonasNoJubiladasEnCola;
    private int topePersonas;
    private final Lock lock=new ReentrantLock();
    private Condition colaJubilados=lock.newCondition();
    private Condition colaPersonas=lock.newCondition();

    public SalaEspera() {
        this.temp=29;
        this.cantidadPersonasEnSala=0;
        this.topePersonas=50;
    }
    
    public void hacerCola(int numeroPersona){
        lock.lock();
        this.cantPersonasNoJubiladasEnCola++;
        System.out.println("La persona "+numeroPersona+" esta haciendo la cola");
        lock.unlock();
    }
    
    public void hacerColaJubilado(int numeroPersona){
        lock.lock();
        this.cantJubiladosEnCola++;
        System.out.println("El jubilado "+numeroPersona+" esta haciendo la cola");
        lock.unlock();
    }
    
    public void entrarSala(int numeroPersona){
        lock.lock();
        while(!(this.cantidadPersonasEnSala<this.topePersonas && this.cantJubiladosEnCola==0)){
            try {
                colaPersonas.await();
            } catch (Exception e) {}
        }
        this.cantPersonasNoJubiladasEnCola--;
        this.cantidadPersonasEnSala++;
        System.out.println("Estado cola: jubilados: "+cantJubiladosEnCola+" | personas: "+cantPersonasNoJubiladasEnCola);
        System.out.println("La persona "+numeroPersona+" ha entrado a la sala. Ahora hay "+cantidadPersonasEnSala+" personas en la sala");
        lock.unlock();
    }
    
    public void entrarSalaJubilados(int numeroPersona){
        lock.lock();
        while(!(this.cantidadPersonasEnSala<this.topePersonas)){
            try {
                colaJubilados.await();
            } catch (Exception e) {}
        }
        this.cantJubiladosEnCola--;
        this.cantidadPersonasEnSala++;
        System.out.println("Estado cola: jubilados: "+cantJubiladosEnCola+" | personas: "+cantPersonasNoJubiladasEnCola);
        System.out.println("El jubilado "+numeroPersona+" ha entrado a la sala. Ahora hay "+cantidadPersonasEnSala+" personas en la sala");
        lock.unlock();
    }
    
    public void salirSala(boolean esJubilado, int numeroPersona){
        lock.lock();
        cantidadPersonasEnSala--;
        String x="La persona";
        if(esJubilado){
            x="El jubilado";
        }
        System.out.println(x+" "+numeroPersona+" ha salido de la sala de espera. Ahora hay "+cantidadPersonasEnSala+" personas en la sala");
        colaPersonas.signalAll();
        colaJubilados.signalAll();
        lock.unlock();
    }
    
    public void notificarTemperatura(int temp){
        this.temp=temp;
        if(this.temp<=30){
            this.topePersonas=50;
        }else{
            this.topePersonas=35;
        }
        System.out.println("La nueva temperatura de la sala es "+temp+". El nuevo limite de personas es "+topePersonas);
    }
    
}
