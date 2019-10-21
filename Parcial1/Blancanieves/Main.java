package Parcial1.Blancanieves;

public class Main {
    
    public static void main(String[] args) {
        
        Mesa mesa=new Mesa();
        Enano[] arregloEnanos=new Enano[7];
        Blancanieves blancanieves=new Blancanieves(mesa);
        
        for (int i = 0; i < 7; i++) {
            arregloEnanos[i]=new Enano(i, mesa);
            arregloEnanos[i].start();
        }
        blancanieves.start();
        
    }
    
}
