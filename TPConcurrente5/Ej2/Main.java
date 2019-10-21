package TPConcurrente5.Ej2;

public class Main {
    
    public static void main(String[] args) {
        
        Libro libro=new Libro();
        Escritor[] arregloEscritores=new Escritor[5];
        Lector[] arregloLectores=new Lector[5];
        
        for (int i = 0; i < 5; i++) {
            arregloEscritores[i]=new Escritor(i, libro);
            arregloLectores[i]=new Lector(i, libro);
            arregloEscritores[i].start();
            arregloLectores[i].start();
        }
        
    }
    
}
