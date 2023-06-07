package service;
import Dao.CarDao;
import entity.Cars;
public class CarService {
    private static CarDao carDao = new CarDao();
    public void create(Cars cars) {
        if (cars.getBrandCar().equals("BMW")) {
            System.out.println("Great choice, BMW is a great Car");
            carDao.create(cars);
        } else if (cars.getBrandCar().equals("FORD")) {
            System.out.println("We have great Ford cars");
            carDao.create(cars);
        } else if (cars.getBrandCar().equals("AUDI")) {
            System.out.println("Great,Audi is a great car");
            carDao.create(cars);
        } else
            return;
        carDao.update(cars);
    }
    public void update(Cars cars) {
        if (cars.getBrandCar().equals("BMW")) {
            System.out.println("Great choice, BMW is a great Car");
            carDao.update(cars);
        } else if (cars.getBrandCar().equals("FORD")) {
            System.out.println("We have great Ford cars");
            carDao.update(cars);
        } else if (cars.getBrandCar().equals("AUDI")) {
            System.out.println("Great,Audi is a great car");
            carDao.update(cars);
        } else
            return;
        carDao.create(cars);
    }
    public void delete(String id) {
        carDao.delete(id);
    }
    public static Cars findById(String id) {
        return carDao.FindOne(id);
    }
    public Cars[] findAll() {
        return carDao.findAll();
    }
}