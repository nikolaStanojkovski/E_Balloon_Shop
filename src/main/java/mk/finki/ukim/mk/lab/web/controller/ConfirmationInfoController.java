package mk.finki.ukim.mk.lab.web.controller;

import mk.finki.ukim.mk.lab.model.Order;
import mk.finki.ukim.mk.lab.model.ShoppingCart;
import mk.finki.ukim.mk.lab.service.ShoppingCartService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/confirmationInfo")
public class ConfirmationInfoController {

    private final ShoppingCartService shoppingCartService;

    public ConfirmationInfoController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @GetMapping
    public String getConfirmationInfoPage(HttpServletRequest req) {

        req.getSession().setAttribute("ClientIP", req.getRemoteAddr());
        req.getSession().setAttribute("ClientBrowser", req.getHeader("User-Agent"));
        req.getSession().setAttribute("clientName", (String) req.getSession().getAttribute("clientName"));
        req.getSession().setAttribute("clientAddress", (String) req.getSession().getAttribute("clientAddress"));

        Order order = (Order) req.getSession().getAttribute("balloonOrder");
        Long cartId = (Long) req.getSession().getAttribute("currentShoppingCart");
        ShoppingCart shoppingCart = this.shoppingCartService.findById(cartId);

        shoppingCartService.addOrderToCartWithUser(order, req.getRemoteUser(), shoppingCart.getId());

        return "confirmationInfo";
    }

}
