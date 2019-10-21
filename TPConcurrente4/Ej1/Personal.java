package TPConcurrente4.Ej1;

public class Personal extends Thread {

    private String nombre;
    private Saludo saludo;
    private boolean esJefe;
    static int llegaron = 0;
    static boolean jefeSaludo = false;
    private static int numEmp;

    Personal(Saludo s, String n) {
        esJefe = false;
        nombre = n;
        saludo = s;
    }

    public static void JefeSaludo() {
        jefeSaludo = true;
    }
    public static boolean getSaludoJefe(){
        return jefeSaludo;
    }
    
    public synchronized static void aumentarLlegados(){
        llegaron++;
    }
    
    public static int numeroLlegados(){
        return llegaron;
    }
    
    public static int numeroEmpleados(){
        return numEmp;
    }

    Personal(Saludo s, String n, int x) {
        esJefe = true;
        nombre = n;
        saludo = s;
        numEmp = x;
    }

    public void run() {
        System.out.println("(" + nombre + " llega)");
        if (esJefe) {
            saludo.saludoJefe();
        } else {
            saludo.esperarJefe(nombre);
        }
    }
}
