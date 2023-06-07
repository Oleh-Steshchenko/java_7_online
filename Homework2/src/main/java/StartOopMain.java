import controler.CarControler;
import java.io.IOException;
public class StartOopMain {
    public static void main(String[] args)throws IOException {
        CarControler carControler=new CarControler();
        CarControler.start();
    }
}