package Dao;
import entity.Cars;
import entity.EntityVin;
import java.util.Set;
import java.util.UUID;
import java.util.HashSet;
public class CarDao {
    private Cars[] cars = new Cars[10];
    public void create(Cars car) {
        for (int i = 0; i < cars.length; i++) {
            if (cars[i] == null) {
                String id = UUID.randomUUID().toString();
                car.setId(id);
                cars[i] = car;
                Set<String> ids = new HashSet<>();
                while (!ids.add(id))
                    System.out.println("The Student ID: " +id+ " already exist.");
                    break;
            }
        }
    }
        public void update(Cars car) {
        for (int i = 0; i < cars.length; i++) {
            try {
                if (cars[i].getId().equals(car.getId())) {
                    cars[i] = car;
                }
            } catch (Exception e) {
                i++;
            }
        }
    }
    public void delete(String id) {
        for (int i = 0; i < cars.length; i++) {
            try {
                if (cars[i].getId().equals(id)) {
                    cars[i] = null;
                }
            }
            catch (Exception e) {
                i++;
            }
        }
    }
    public Cars FindOne(String id) {
        for (int i = 0; i < cars.length; i++) {
            Cars cars1 = cars[i];
            if (cars1 != null) {
                if (cars1.getId().equals(id)) {
                    return cars1;
                }
            }
        }
        return null;
    }
    public Cars[] findAll() {
        return cars;
    }
}