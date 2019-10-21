package TPConcurrente4.Ej1;

public class Main {

    public static void main(String argv[]) {
        String[] nombresEmpleados = {"Pablo", "Luis", "Andrea",
            "Pedro", "Paula"};
        Saludo hola = new Saludo();
        Personal[] elPersonal = new Personal[6];
        elPersonal[0] = new Personal(hola, "JEFE", 5);
        for (int i = 1; i < 6; i++) {
            elPersonal[i] = new Personal(hola,nombresEmpleados[i - 1]);
        }
        for (int i = 0; i < 6; i++) {
            elPersonal[i].start();
        }
        try {
            for (int i = 0; i < 6; i++) {
                elPersonal[i].join();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
