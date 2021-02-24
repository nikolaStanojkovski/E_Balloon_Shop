package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Order;
import mk.finki.ukim.mk.lab.model.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {
    void addOrderToCartWithUser(Order order, String username, Long cartId);
    ShoppingCart listOrders(Long id);
    void addShoppingCart(ShoppingCart cart);
    ShoppingCart findById(Long id);
}
