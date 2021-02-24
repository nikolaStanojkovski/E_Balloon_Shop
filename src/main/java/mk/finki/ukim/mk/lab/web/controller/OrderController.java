package mk.finki.ukim.mk.lab.web.controller;

import mk.finki.ukim.mk.lab.model.Order;
import mk.finki.ukim.mk.lab.model.ShoppingCart;
import mk.finki.ukim.mk.lab.service.OrderService;
import mk.finki.ukim.mk.lab.service.ShoppingCartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.context.WebContext;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;
    private final ShoppingCartService shoppingCartService;

    public OrderController(OrderService orderService, ShoppingCartService shoppingCartService) {
        this.orderService = orderService;
        this.shoppingCartService = shoppingCartService;
    }

    @GetMapping
    public String getOrderPage(HttpServletRequest request, Model model) {
        Long shoppingCartId = (Long) request.getSession().getAttribute("currentShoppingCart");
        ShoppingCart shoppingCart = shoppingCartService.listOrders(shoppingCartId);
        model.addAttribute("shoppingCart", shoppingCart);
        return "userOrders";
    }

}
