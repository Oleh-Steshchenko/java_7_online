import controller.HandshakesController;
import java.io.IOException;
public class HandshakesStart {
    public static void main(String[] args) throws IOException {
        HandshakesController handshakesController =new HandshakesController();
        handshakesController.start();
    }
}