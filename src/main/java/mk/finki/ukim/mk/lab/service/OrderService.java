package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Order;
import mk.finki.ukim.mk.lab.model.User;

import java.util.List;

public interface OrderService {
    void removeOrder(Long id);
    Order getById(Long id);
    List<Order> getAll();
    Order save(String balloonColor, String balloonSize, User user, Long orderId);
}
