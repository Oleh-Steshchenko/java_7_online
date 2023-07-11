import controller.StreamController;
import java.io.IOException;
public class StartStream {
    public static void main(String[] args) throws IOException {
        StreamController streamController =new StreamController();
        streamController.start();
    }
}q