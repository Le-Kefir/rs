package storage;

import entities.Car;
import entities.Order;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class MainStorage {

    private List<Car> cars;

    public MainStorage() {
        cars = new ArrayList<>();
    }

    public List<Order> getOrders() {
        List<Order> orders = new ArrayList<>();
        for (Car car : cars) {
            orders.addAll(car.getOrders());
        }
        return orders;
    }

    public List<Car> getCars() {
        return cars;
    }

}
