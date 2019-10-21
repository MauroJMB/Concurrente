
package TPConcurrente4.Ej3;

import java.util.Random;

public class Main {
    
    public static void main(String[] args) {
        
        CentroImpresion centro=new CentroImpresion(2, 3);
        Persona[] arregloPersonas=new Persona[13];
        Thread[] arregloHilosPersonas=new Thread[13];
        Random r=new Random();
        
        for (int i = 0; i < 13; i++) {
            arregloPersonas[i]=new Persona(i, r.nextInt(3)+1, centro);
        }
        
        for (int i = 0; i < 13; i++) {
            arregloHilosPersonas[i]=new Thread(arregloPersonas[i]);
            arregloHilosPersonas[i].start();
        }
        
    }
    
}
