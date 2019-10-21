package Parcial1.Blancanieves;

public class Blancanieves extends Thread{
    
    private Mesa mesa;

    public Blancanieves(Mesa mesa) {
        this.mesa = mesa;
    }
    
    @Override
    public void run() {
        while(true){
            mesa.servirComida();
        }
    }
    
}
