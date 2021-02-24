package mk.finki.ukim.mk.lab.web.servlet;

import mk.finki.ukim.mk.lab.model.Order;
import mk.finki.ukim.mk.lab.model.ShoppingCart;
import mk.finki.ukim.mk.lab.model.User;
import mk.finki.ukim.mk.lab.service.BalloonService;
import mk.finki.ukim.mk.lab.service.OrderService;
import mk.finki.ukim.mk.lab.service.ShoppingCartService;
import mk.finki.ukim.mk.lab.service.UserService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Confirmation Information", urlPatterns = "/ConfirmationInfoServlet")
public class ConfirmationInfoServlet extends HttpServlet {

    private final BalloonService balloonService;
    private final OrderService orderService;
    private final ShoppingCartService shoppingCartService;
    private final UserService userService;
    private final SpringTemplateEngine springTemplateEngine;

    public ConfirmationInfoServlet(BalloonService balloonService, OrderService orderService, ShoppingCartService shoppingCartService,
                                   UserService userService, SpringTemplateEngine springTemplateEngine) {
        this.balloonService = balloonService;
        this.orderService = orderService;
        this.shoppingCartService = shoppingCartService;
        this.userService = userService;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        WebContext context = new WebContext(req, resp, req.getServletContext());
        req.getSession().setAttribute("ClientIP", req.getRemoteAddr());
        req.getSession().setAttribute("ClientBrowser", req.getHeader("User-Agent"));
        req.getSession().setAttribute("clientName", (String) req.getSession().getAttribute("clientName"));
        req.getSession().setAttribute("clientAddress", (String) req.getSession().getAttribute("clientAddress"));

        Order order = (Order) req.getSession().getAttribute("balloonOrder");
        Long cartId = (Long) req.getSession().getAttribute("currentShoppingCart");
        ShoppingCart shoppingCart = this.shoppingCartService.findById(cartId);

        shoppingCartService.addOrderToCartWithUser(order, req.getRemoteUser(), shoppingCart.getId());

        springTemplateEngine.process("/confirmationInfo.html", context, resp.getWriter());
    }
}
