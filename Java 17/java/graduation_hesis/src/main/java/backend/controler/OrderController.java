package backend.controler;

import backend.model.Order;
import backend.model.Status;
import backend.service.OrderService;

import java.util.ArrayList;
import java.util.List;

public class OrderController {
    OrderService orderService = new OrderService();

    public void creatOrder(Order order) {
        orderService.creatOrder(order);
    }

    public int getIdOrder() {
        return orderService.getIdOrder();
    }

    public ArrayList<Order> getOrders(String email) {
        return orderService.getOrders(email);
    }

    public ArrayList<Order> getorderDone(String email) {
        return orderService.getorderDone(email);
    }

    public List<Order> getOrdersBystatus(Status status) {
        return orderService.getOrdersBystatus(status);
    }

    public void changeStatus(int id, Status status) {
        orderService.changeStatus(id, status);
    }

    public void changeAllStatus(Status status,Status newStatus) {
        orderService.changeAllStatus(status,newStatus);
    }

    public long countOrder(Status status) {
        return orderService.countOrder(status);
    }

    public long countAllOrder() {
        return orderService.countAllOrder();
    }

    public int getRevenueByYear(int year) {
        return orderService.getRevenueByYear(year);
    }

    public int getRevenueByMonth(int month, int year) {
        return orderService.getRevenueByMonth(month,year);
    }
}
