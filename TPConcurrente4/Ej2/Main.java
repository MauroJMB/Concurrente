package TPConcurrente4.Ej2;

public class Main {
    
    public static void main(String[] args) {
        
        Persona[] arregloPersonas=new Persona[60];
        Thread[] arregloHilos=new Thread[60];
        SalaEspera sala=new SalaEspera();
        Termometro term=new Termometro(sala);
        Thread termometro=new Thread(term);
        
        for (int i = 0; i < 20; i++) {
            arregloPersonas[i]=new Persona(i, sala, true);
        }
        for (int i = 20; i < 60; i++) {
            arregloPersonas[i]=new Persona(i, sala, false);
        }
        
        for (int i = 0; i < 60; i++) {
            arregloHilos[i]=new Thread(arregloPersonas[i]);
        }
        
        for (int i = 0; i < 60; i++) {
            arregloHilos[i].start();
        }
        termometro.start();
        
    }
    
}
