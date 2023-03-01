public class MovementNotAllowedException extends Exception {


    public MovementNotAllowedException() {
        super("Movimento invalido");
    }

    public MovementNotAllowedException(String message) {
        super(message);
    }
    
}
