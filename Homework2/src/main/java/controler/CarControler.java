package controler;
import entity.Cars;
import service.CarService;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
public class CarControler {
    private static CarService carService = new CarService();
    public static void start() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Hello,welcome to the car showroom");
        System.out.println("Please choose the car you would like:");
        String select;
        menu();
        while ((select = bufferedReader.readLine()) != null) {
            crud(bufferedReader, select);
        }
    }
    private static void menu() {
        System.out.println();
        System.out.println("Add Car, please enter 1");
        System.out.println("Update Car, please enter 2");
        System.out.println("Delete Car, please enter 3");
        System.out.println("Find a car by VIN, please enter 4");
        System.out.println("Find all Cars, please enter 5");
        System.out.println("Close application, please enter 6");
    }
    private static void crud(BufferedReader bufferedReader, String select) throws IOException {
        switch (select) {
            case "1" -> create(bufferedReader);
            case "2" -> update(bufferedReader);
            case "3" -> delete(bufferedReader);
            case "4" -> FindOne(bufferedReader);
            case "5" -> findAll(bufferedReader);
            case "6" -> System.exit(0);
            default -> System.out.println("You have an error, please enter again");
        }
        menu();
    }
    private static void create(BufferedReader bufferedReader) throws IOException {
        System.out.println("Please write the car brand you want:");
        System.out.println("We have: BMW,FORD,AUDI");
        String BrandCar = bufferedReader.readLine();
    System.out.println("Please write in what body you want the car");
        String BodyType = bufferedReader.readLine();
        System.out.println("Please write what gear do you want:");
        String Transmission = bufferedReader.readLine();
       Cars cars=new Cars();
        cars.setBrandCar(BrandCar);
        cars.setBodyType(BodyType);
        cars.setTransmission(Transmission);
        carService.create(cars);
    }
    private static void update(BufferedReader bufferedReader)throws IOException {
        System.out.println("Please enter the VIN of car whom you want to update: ");
        String id = bufferedReader.readLine();
        System.out.println("Please enter the BrandCar:");
        String BrandCar = bufferedReader.readLine();
        System.out.println("Please enter the BodyType:");
        String BodyType = bufferedReader.readLine();
        System.out.println("Please enter the Transmission:");
        String Transmission = bufferedReader.readLine();
        Cars cars = CarService.findById(id);
            cars.setBrandCar(BrandCar);
            cars.setBodyType(BodyType);
            cars.setTransmission(Transmission);
            carService.update(cars);
            System.out.println("Car was created");
        }
        private static void delete(BufferedReader bufferedReader)throws IOException {
            System.out.println("Please enter the VIN code of car whom you want to delete: ");
            String VIN = bufferedReader.readLine();
                carService.delete(VIN);
                System.out.println("The car has been removed");
            }
    private static void FindOne(BufferedReader bufferedReader)throws IOException {
        System.out.println("Please enter the VIN code of the car");
        String id=bufferedReader.readLine();
        Cars cars=carService.findById(id);
        if (cars !=null){
            System.out.println("BrandCar:" + cars.getBrandCar()+",BodyType:" + cars.getBodyType()+",Transmission:" + cars.getTransmission());
        }else {
            System.out.println("Car not found");
        }
    }
    private static void findAll(BufferedReader bufferedReader) {
        Cars[] cars = carService.findAll();
        for (int i = 0; i < cars.length; i++) {
            Cars cars1 = cars[i];
            if (cars1 != null) {
                System.out.println("VIN:"+cars1.getId()+", BrandCar:" + cars1.getBrandCar()+",BodyType:" + cars1.getBodyType()+",Transmission:" + cars1.getTransmission());
            }
        }
    }
}