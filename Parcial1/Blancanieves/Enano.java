package Parcial1.Blancanieves;

public class Enano extends Thread{
    
    private int numeroEnano;
    private Mesa mesa;

    public Enano(int numeroEnano, Mesa mesa) {
        this.numeroEnano = numeroEnano;
        this.mesa = mesa;
    }
    
    public void trabajar(){
        try {
            System.out.println("El enano "+numeroEnano+" está trabajando");
            sleep(1000);
            System.out.println("El enano "+numeroEnano+" terminó de trabajar");
        } catch (Exception e) {}
    }
    
    public void dormir(){
        try {
            System.out.println("El enano "+numeroEnano+" se va a dormir");
            sleep(3000);
        } catch (Exception e) {}
    }

    @Override
    public void run() {
        while(true){            
            trabajar();
            mesa.sentarse(numeroEnano);
            mesa.comer(numeroEnano);
            trabajar();
            dormir();
        }
    }
    
}
