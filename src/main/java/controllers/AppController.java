package controllers;

import entities.Car;
import entities.Order;
import services.CarService;
import services.OrderService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Named
@Path("/taxi")
@ApplicationScoped
public class AppController {

    @Inject
    CarService carService;

    @Inject
    OrderService orderService;

    private static int carIdCounter = 0;
    private static int orderIdCounter = 0;

    @POST
    @Path("/car")
    @Produces(MediaType.APPLICATION_JSON)
    public Car createCar() {
        carIdCounter++;
        return carService.createCar(carIdCounter);
    }

    @POST
    @Path("/order")
    @Produces(MediaType.APPLICATION_JSON)
    public Order createOrder(@QueryParam("from") String from,
                             @QueryParam("to") String to,
                             @QueryParam("time") String time) throws ParseException {
        orderIdCounter++;
        return orderService.createOrder(orderIdCounter, from, to, time);
    }

    @GET
    @Path("/cars")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Car> getAllCars() {
        return carService.getCars();
    }

    @GET
    @Path("/orders")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Order> getAllOrders() {
        return orderService.getOrders();
    }

    @GET
    @Path("/car/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Car getCar(@PathParam("id") int id) {
        return carService.getCar(id);
    }


    @GET
    @Path("/order/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Order getOrder(@PathParam("id") int id) {
        return orderService.getOrder(id);
    }

    @GET
    @Path("/check")
    public String checkAvailability(@QueryParam("time") String time) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy,HH:mm");
        return carService.checkAvailability(simpleDateFormat.parse(time));
    }

    @DELETE
    @Path("/order/{id}")
    public String deleteOrder(@PathParam("id") int id) {
        return orderService.deleteOrder(id);
    }

    @DELETE
    @Path("car/{id}")
    public String deleteCar(@PathParam("id") int id) {
        return carService.deleteCar(id);
    }



//    @Inject
//    CarService carService;
//
//    @Inject
//    OrderService orderService;
//
//    @POST
//    @Path("/car")
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public Car createCar() {
//        Car car = carService.createCar(carIdCounter);
//        carIdCounter++;
//        return car;
//    }
//
//    @POST
//    @Path("/order")
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public Order createOrder() {
//        Order order = orderService.createOrder(orderIdCounter);
//        orderIdCounter++;
//        return order;
//    }
//
//    @GET
//    @Path("/cars")
//    @Produces(MediaType.APPLICATION_JSON)
//    public List<Car> getAllCars() {
//        return carService.getCars();
//    }
//
//    @GET
//    @Path("/orders")
//    @Produces(MediaType.APPLICATION_JSON)
//    public List<Order> getAllOrders() {
//        return orderService.getOrders();
//    }
//
//    @DELETE
//    @Path("/car")
//    @Produces(MediaType.APPLICATION_JSON)
//    public void deleteCar(@QueryParam("id") int id) {
//        carService.deleteCar(id);
//    }
}
