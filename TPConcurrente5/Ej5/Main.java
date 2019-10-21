
package TPConcurrente5.Ej5;

import java.util.Random;

public class Main {
    
    public static void main(String[] args) {
        
        Puente puente=new Puente();
        Auto[] arregloAutos=new Auto[10];
        Random r=new Random();
        
        for (int i = 0; i < 5; i++) {
            arregloAutos[i]=new Auto(i, true, puente);
            arregloAutos[i+4]=new Auto(i, false, puente);
            arregloAutos[i].start();
        }
        
    }
    
}
