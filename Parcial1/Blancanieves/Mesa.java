package Parcial1.Blancanieves;

public class Mesa {
    
    private int sillasOcupadas;
    private int sillasMax;
    private int turnosParaComer;

    public Mesa() {
        this.sillasOcupadas = 0;
        this.sillasMax = 4;
        this.turnosParaComer = 0;
    }
    
    public void comer(int numEnano){
        comenzarComer(numEnano);
        terminarComer(numEnano);
    }
    
    private void comenzarComer(int numEnano){
        try {
            System.out.println("El enano "+numEnano+" empieza a comer");
            Thread.sleep(1000);
            System.out.println("El enano "+numEnano+" termina de comer");
        } catch (Exception e) {}
    }
    
    private synchronized void terminarComer(int numEnano){
        try {
            System.out.println("El enano "+numEnano+" se levanta de la silla");
            sillasOcupadas--;
            notifyAll();
        } catch (Exception e) {}
    }
    
    public synchronized void sentarse(int numEnano){
        try {
            while(sillasOcupadas==sillasMax){
                System.out.println("No hay sillas disponibles. El enano "+numEnano+" se pone a esperar");
                wait();
            }
            sillasOcupadas++;
            System.out.println("El enano "+numEnano+" se sentó en una silla. Sillas usadas: "+sillasOcupadas);
            turnosParaComer++;
            notifyAll();
            System.out.println("El enano "+numEnano+" avisó a Blancanieves que le sirva");
            while (turnosParaComer>0) {
                System.out.println("El enano "+numEnano+" esta esperando que Blancanieves le sirva la comida");
                wait();
            }
        } catch (Exception e) {}
    }
    
    public synchronized void servirComida(){
        try {
            while (turnosParaComer>0){
                Thread.sleep(1000);
                turnosParaComer--;
                System.out.println("Blancanieves sirvió una silla");
            }
            System.out.println("Blancanieves avisa a los enanos que la comida ya esta servida");
            notifyAll();
            while(turnosParaComer==0){
                System.out.println("Blancanieves esta paseando con el principe");
                wait();
            }
        } catch (Exception e) {}
    }
    
}
