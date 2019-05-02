package services;

import entities.Car;
import entities.Order;
import storage.MainStorage;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.text.ParseException;
import java.util.List;

@ApplicationScoped
public class OrderService {


    @Inject
    MainStorage mainStorage;

    @Inject
    CarService carService;

    public Order createOrder(int id, String from, String to,String time) throws ParseException {
        Order order = new Order();
        order.setId(id);
        order.setFrom(from);
        order.setTo(to);
        order.setPrice((from.length() + to.length()) * 8.5);
        order.setTime(time);
        Car car = carService.findFastestCar();
        order.setCarId(car.getId());
        car.getOrders().add(order);
        return order;
    }

    public List<Order> getOrders() {
        return mainStorage.getOrders();
    }

    public Order getOrder(int id) {
        for (Order order : mainStorage.getOrders()) {
            if (order.getId() == id) {
                return order;
            }
        }
        return null;
    }

    public String deleteOrder(int id) {
        for (Car car : carService.getCars()) {
            for (Order order : car.getOrders()) {
                if (order.getId() == id) {
                    car.getOrders().remove(order);
                    return "Order deleted";
                }
            }
        }
        return "Order was not deleted";
    }
}
