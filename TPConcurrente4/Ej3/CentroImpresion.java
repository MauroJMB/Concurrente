package TPConcurrente4.Ej3;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CentroImpresion {
    
    private int cantImpresorasEnUsoA;
    private int cantImpresorasEnUsoB;
    private Lock lock=new ReentrantLock();
    private Condition colaEsperaA=lock.newCondition();
    private Condition colaEsperaB=lock.newCondition();
    private Condition colaEsperaGeneral=lock.newCondition();
    private Condition colaImprimiendoA=lock.newCondition();
    private Condition colaImprimiendoB=lock.newCondition();

    public CentroImpresion(int cantImpresorasA, int cantImpresorasB) {
        this.cantImpresorasEnUsoA = cantImpresorasA;
        this.cantImpresorasEnUsoB = cantImpresorasB;
    }
    
    public void imprimir(int tipoImpresora, int numeroUsuario){
        lock.lock();
        try {
            switch(tipoImpresora){
                case 1:
                    imprimirA(numeroUsuario);
                    break;

                case 2:
                    imprimirB(numeroUsuario);
                    break;

                default:
                    imprimirGeneral(numeroUsuario);
                    break;
            }
        } catch (Exception e) {}
        lock.unlock();
    }
    
    private void imprimirA(int numeroUsuario) throws Exception{
        lock.lock();
        while(cantImpresorasEnUsoA==0){
            System.out.println("El usuario "+numeroUsuario+" esta esperando una impresora A");
            colaEsperaA.await();
        }
        cantImpresorasEnUsoA--;
        System.out.println("El usuario "+numeroUsuario+" esta imprimiendo en una impresora A. Ahora hay "+cantImpresorasEnUsoA+" impresoras A disponibles");
        colaImprimiendoA.awaitNanos(2000);
        cantImpresorasEnUsoA++;
        System.out.println("El usuario "+numeroUsuario+" dejo de usar la impresora A. Ahora hay "+cantImpresorasEnUsoA+" impresoras A disponibles");
        colaEsperaA.signalAll();
        colaEsperaGeneral.signalAll();
        lock.unlock();
    }
    
    private void imprimirB(int numeroUsuario) throws Exception{
        lock.lock();
        while(cantImpresorasEnUsoB==0){
            System.out.println("El usuario "+numeroUsuario+" esta esperando una impresora B");
            colaEsperaB.await();
        }
        cantImpresorasEnUsoB--;
        System.out.println("El usuario "+numeroUsuario+" esta imprimiendo en una impresora B. Ahora hay "+cantImpresorasEnUsoB+" impresoras B disponibles");
        colaImprimiendoB.awaitNanos(2000);
        cantImpresorasEnUsoB++;
        System.out.println("El usuario "+numeroUsuario+" dejo de usar la impresora B. Ahora hay "+cantImpresorasEnUsoB+" impresoras B disponibles");
        colaEsperaB.signalAll();
        colaEsperaGeneral.signalAll();
        lock.unlock();
    }
    
    private void imprimirGeneral(int numeroUsuario) throws Exception{
        lock.lock();
        while(cantImpresorasEnUsoA==0 && cantImpresorasEnUsoB==0){
            System.out.println("El usuario "+numeroUsuario+" esta esperando una impresora A o B");
            colaEsperaGeneral.await();
        }
        if(cantImpresorasEnUsoA>0){
            imprimirA(numeroUsuario);
        }else{
            imprimirB(numeroUsuario);
        }
        lock.unlock();
    }
    
}
