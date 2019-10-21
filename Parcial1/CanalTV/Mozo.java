package Parcial1.CanalTV;

public class Mozo extends Thread{
    
    private Mesa mesa;

    public Mozo(Mesa mesa) {
        this.mesa = mesa;
    }

    @Override
    public void run() {
        while(true){
            try {
                mesa.servirComida();
            } catch (InterruptedException ex) {}
        }
    }
    
}
