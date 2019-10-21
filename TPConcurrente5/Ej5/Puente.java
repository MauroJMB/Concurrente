package TPConcurrente5.Ej5;

import java.util.concurrent.Semaphore;

public class Puente {
    
    private boolean sentido;//si es false, el sentido es SN, si es NS es true
    private int cantPasando;
    private int contadorautosporsentido;

    public Puente() {
        this.cantPasando=0;
        this.contadorautosporsentido=0;
    }
    
    public void entrar(boolean sentido, int numeroAuto){
        try {
            if(sentido){
                entrarSur(sentido);
                salirNorte();
            }else{
                entrarNorte(sentido);
                salirSur();
            }
        } catch (Exception e) {}
        
    }
    
    public synchronized void entrarNorte(boolean sentidoAuto) throws InterruptedException{
        while(!(cantPasando==0 || sentido==sentidoAuto)){
            wait();
        }
        System.out.println("Un auto esta cruzando en sentido N - S");
        sentido=sentidoAuto;
        contadorautosporsentido++;
        if(contadorautosporsentido>=5){
            sentidoAuto=!sentidoAuto;
            contadorautosporsentido=0;
        }
        cantPasando++;
        wait(2000);
    }
    
    public synchronized void entrarSur(boolean sentidoAuto) throws InterruptedException{
        while(!(cantPasando==0 || sentido==sentidoAuto)){
            wait();
        }System.out.println("Un auto esta cruzando en sentido S - N");
        contadorautosporsentido++;
        if(contadorautosporsentido>=5){
            sentidoAuto=!sentidoAuto;
            contadorautosporsentido=0;
        }
        cantPasando++;
        wait(2000);
    }
    
    public synchronized void salirNorte(){
        cantPasando--;
        contadorautosporsentido--;
        System.out.println("Un auto termino de cruzar en sentido S - N");
        notifyAll();
    }
    
    public synchronized void salirSur(){
        cantPasando--;
        contadorautosporsentido--;
        System.out.println("Un auto termino de cruzar en sentido N - S");
        notifyAll();
    }
    
}
