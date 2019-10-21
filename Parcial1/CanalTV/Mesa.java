package Parcial1.CanalTV;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Mesa {
    
    private Random r;
    private int cantInvitados;
    private int cantidadComensalesEsperando;
    private int cantMozos;
    private Semaphore mutex;
    private Semaphore mozos;
    private Semaphore mirtha;
    private Semaphore comerInvitados;
    private Semaphore pregunta;
    private Semaphore levantarse;

    public Mesa(int cantInvitados, int cantMozos) {
        r=new Random();
        this.cantInvitados=cantInvitados;
        this.cantidadComensalesEsperando=0;
        this.cantMozos=cantMozos;
        this.mutex=new Semaphore(1);
        this.mozos=new Semaphore(0);
        this.mirtha=new Semaphore(0);
        this.comerInvitados=new Semaphore(0);
        this.pregunta=new Semaphore(0);
        this.levantarse=new Semaphore(0);
    }
    
    public void sentarse() throws InterruptedException{
        System.out.println("Un invitado se ha sentado en la silla");
        mutex.acquire();
        cantidadComensalesEsperando++;
        mutex.release();
        mirtha.release();
    }
    
    public void sentarseMirtha() throws InterruptedException{
        mirtha.acquire(cantInvitados);
        System.out.println("Todos los invitados se han sentado. Mirtha se sienta");
        mozos.release(cantInvitados);
    }
    
    public void servirComida() throws InterruptedException{
        mozos.acquire();
        System.out.println("El Mozo está sirviendo comida");
        Thread.sleep(1000);
        System.out.println("El Mozo termino de servir comida");
        mutex.acquire();
        cantidadComensalesEsperando--;
        if(cantidadComensalesEsperando==0){
            System.out.println("Todos los invitados han sido servidos y ahora pueden comer");
            comerInvitados.release(cantInvitados);
        }
        mutex.release();
    }
    
    public void comer() throws InterruptedException{
        comerInvitados.acquire();
        System.out.println("Un invitado está comiendo");
        Thread.sleep(1000);
        System.out.println("Un invitado terminó de comer");
    }
    
    public void comerMirtha() throws InterruptedException{
        System.out.println("Mirtha ha empezado a comer");
        Thread.sleep(1000);
        System.out.println("Mirtha terminó de comer");
    }
    
    public void enojarseMirtha() throws InterruptedException{
        mirtha.acquire();
        System.out.println("Mirtha se indigna y se levanta de la mesa");
    }
    
    public void levantarseMirtha(){
        System.out.println("Mirtha se levanta enojada de la mesa y se va");
        levantarse.release(cantInvitados);
    }
    
    public void levantarse() throws InterruptedException{
        levantarse.acquire();
        System.out.println("El invitado se ha retirado de la mesa");
    }
    
    public void lanzarPregunta() {
        System.out.println("Mirtha lanza una pregunta polémica, y espera la respuesta");
        pregunta.release();
    }
    
    public void lanzarRespuesta(){
        if(pregunta.tryAcquire()){
            System.out.println("El invitadado responde la pregunta polémica");
            mirtha.release();
        }
    }
    
}
