package Parcial1.CanalTV;

public class Main {
    
    public static void main(String[] args) {
        
        Mesa mesa=new Mesa(4, 2);
        Invitado[] arregloInvitados=new Invitado[5];
        Mozo[] arregloMozos=new Mozo[2];
        
        arregloInvitados[0]=new Invitado(0, true, mesa);
        for (int i = 1; i < 5; i++) {
            arregloInvitados[i]=new Invitado(i, false, mesa);
        }
        
        for (int i = 0; i < 2; i++) {
            arregloMozos[i]=new Mozo(mesa);
            arregloMozos[i].start();
        }
        
        for (int i = 0; i < 5; i++) {
            arregloInvitados[i].start();
        }
        
    }
    
}
