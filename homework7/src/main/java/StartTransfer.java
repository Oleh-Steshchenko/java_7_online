import controller.TransferController;
import java.io.IOException;
public class StartTransfer {
    public static void main(String[] args) throws IOException {
        TransferController transferController = new TransferController();
        transferController.start();
    }
}