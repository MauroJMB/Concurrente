package TPConcurrente4.Ej1;

public class Saludo {

    synchronized void esperarJefe(String empleado) {
        Personal.aumentarLlegados();
        notifyAll();
        while(/*Personal.numeroLlegados() < Personal.numeroEmpleados() || */!Personal.getSaludoJefe()){
            try {
                wait();
            } catch (Exception e) {}
        }
        System.out.println(empleado + "> Buenos dias jefe!");
    }

    synchronized void saludoJefe() {
        while(Personal.numeroLlegados() < Personal.numeroEmpleados()){
            try {
                wait();
            } catch (Exception e) {}
        }
        System.out.println("JEFE> Buenos dias!");
        Personal.JefeSaludo();
        notifyAll();
    }
}
