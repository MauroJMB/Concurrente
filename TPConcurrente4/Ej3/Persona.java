package TPConcurrente4.Ej3;

public class Persona implements Runnable{
    
    private int numeroUsuario;
    private int tipoImpresora; // 1=A // 2=B // 3=A o B
    private CentroImpresion centro;

    public Persona(int numeroUsuario, int tipoImpresora, CentroImpresion centro) {
        this.numeroUsuario = numeroUsuario;
        this.tipoImpresora = tipoImpresora;
        this.centro = centro;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1; i++) {
            centro.imprimir(tipoImpresora, numeroUsuario);
        }
    }
    
}
