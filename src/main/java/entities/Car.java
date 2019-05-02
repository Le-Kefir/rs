package entities;

import java.util.ArrayList;
import java.util.List;

public class Car {

    private int id;
    private String licensePlate;
    private List<Order> orders;
    private int time;

    private static final int TIME_TO_CUSTOMER = 10; //time to reach customer's location in min
    private static final int TIME_FOR_RIDE = 15; //time to get to the final destination

    public Car() {
        this.orders = new ArrayList<>();
        this.time = TIME_TO_CUSTOMER;
    }

    public int calculateTime() {
        return (TIME_FOR_RIDE + TIME_TO_CUSTOMER) * orders.size() + TIME_TO_CUSTOMER;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public int getTime() {
        return calculateTime();
    }

    public void setTime(int time) {
        this.time = time;
    }
}
