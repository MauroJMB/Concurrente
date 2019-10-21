package Parcial1.CanalTV;

public class Invitado extends Thread{
    
    private boolean esMirtha;
    private Mesa mesa;
    private int numeroInvitado;

    public Invitado(int numInvitado, boolean esMirtha, Mesa mesa) {
        this.mesa=mesa;
        this.numeroInvitado=numInvitado;
        this.esMirtha=esMirtha;
    }

    @Override
    public void run() {
        try {
            if(esMirtha){
                mesa.sentarseMirtha();
                mesa.comerMirtha();
                mesa.lanzarPregunta();
                mesa.enojarseMirtha();
                mesa.levantarseMirtha();
            }else{
                mesa.sentarse();
                mesa.comer();
                mesa.lanzarRespuesta();
                mesa.levantarse();
            }
        } catch (Exception e) {
        }
    }
    
}
