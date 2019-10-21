package TPConcurrente5.Ej2;

public class Lector extends Thread{
    
    private int numeroLector;
    private Libro libro;

    public Lector(int numeroLector, Libro libro) {
        this.numeroLector = numeroLector;
        this.libro = libro;
    }

    @Override
    public void run() {
        while(true){
            libro.leer(numeroLector);
            try {
                sleep(50);} catch (Exception e) {}
        }
    }
    
}
