package TPConcurrente5.Ej2;

public class Escritor extends Thread{
    
    private int numeroEscritor;
    private Libro libro;

    public Escritor(int numeroEscritor, Libro libro) {
        this.numeroEscritor = numeroEscritor;
        this.libro = libro;
    }

    @Override
    public void run() {
        boolean repetir=true;
        while (repetir) {            
            repetir=libro.escribir(numeroEscritor);
        }
    }
    
}
