package TPConcurrente5.Ej3;

public class Soldado implements Runnable{
    
    private int numeroSoldado;
    private boolean postre;
    private Recinto recinto;
    private boolean abridor;

    public Soldado(int numeroSoldado, boolean postre, Recinto recinto, boolean abridor) {
        this.numeroSoldado = numeroSoldado;
        this.postre = postre;
        this.recinto = recinto;
        this.abridor = abridor;
    }
    
    @Override
    public void run() {
        recinto.tomarBandeja(numeroSoldado);
        recinto.tomarAbridor(numeroSoldado);
        recinto.tomarPostre(numeroSoldado);
        System.out.println("Soldado "+numeroSoldado+" está comiendo");
        try {
            Thread.sleep(1000);
        } catch (Exception e) {}
        System.out.println("Soldado "+numeroSoldado+" salió del recinto");
    }
    
}
