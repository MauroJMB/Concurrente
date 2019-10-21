package TPConcurrente4.Ej2;

public class Persona implements Runnable{
    
    private SalaEspera sala;
    private int numeroPersona;
    private boolean jubilado;

    public Persona(int numero, SalaEspera sala, boolean jubilado) {
        this.sala=sala;
        this.numeroPersona=numero;
        this.jubilado=jubilado;
    }
    
    public boolean esJubilado(){
        return this.jubilado;
    }

    @Override
    public void run() {
        if(jubilado){
            sala.hacerColaJubilado(this.numeroPersona);
            esperar(100);
            sala.entrarSalaJubilados(this.numeroPersona);
        }else{
            sala.hacerCola(this.numeroPersona);
            esperar(100);
            sala.entrarSala(this.numeroPersona);
        }           
        esperar(4000);
        sala.salirSala(this.jubilado, this.numeroPersona);
    }
    
    private void esperar(int n){
        try {
            Thread.sleep(n);
        } catch (Exception e) {} 
    }
    
}
