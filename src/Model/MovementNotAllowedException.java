package Model;

public class MovementNotAllowedException extends Exception {


    public MovementNotAllowedException() {
        super("Movimento invalido");
    }

    public MovementNotAllowedException(String message) {
        super(message);
    }

    public MovementNotAllowedException(String className, String partida, String destino) {
        super("Mover " + className + " de " + partida + " para " + destino + " é invalido");
    }
    
}
