package services;

import entities.Car;
import entities.Order;
import storage.MainStorage;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@ApplicationScoped
public class CarService {

    @Inject
    MainStorage mainStorage;

    public Car createCar(int id) {
        Car car = new Car();
        car.setId(id);
        car.setLicensePlate("CNO" + id);
        mainStorage.getCars().add(car);
        return car;
    }

    public Car findFastestCar() {
        Car fastest = mainStorage.getCars().get(0);
        for (Car car : mainStorage.getCars()) {
            if (car.getTime() < fastest.getTime()) {
                fastest = car;
            }
        }
        return fastest;
    }

    public List<Car> getCars() {
        return mainStorage.getCars();
    }

    public Car getCar(int id) {
        for (Car car : mainStorage.getCars()) {
            if (car.getId() == id) {
                return car;
            }
        }
        return null;
    }

    public String checkAvailability(Date date) {
        String msg = "There are no car available at this moment!";
        for (Car car : mainStorage.getCars()) {
            if (((date.getTime() - new Date().getTime()) / 1000 / 60) > car.getTime()) {
                msg = "There is cars available in " + car.getTime() + " minutes";
            }
        }
        return msg;
    }

    public String deleteCar(final int id) {
        ArrayList<Order> ordersToReallocate = new ArrayList<>();
        for (Car carToDelete : getCars()) {
            if (carToDelete.getId() == id) {
                ordersToReallocate.addAll(carToDelete.getOrders());
                getCars().removeIf(c -> (c.getId() == id));
                return "Car deleted";
            }
        }
        return "Car was not found";
    }

}
