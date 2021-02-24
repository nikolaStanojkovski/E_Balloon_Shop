package mk.finki.ukim.mk.lab.web.servlet;

import mk.finki.ukim.mk.lab.model.Order;
import mk.finki.ukim.mk.lab.service.BalloonService;
import mk.finki.ukim.mk.lab.service.OrderService;
import mk.finki.ukim.mk.lab.service.UserService;
import mk.finki.ukim.mk.lab.service.implementation.OrderServiceImpl;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Balloon Order", urlPatterns = "/BalloonOrderServlet")
public class BalloonOrderServlet extends HttpServlet {

    private final BalloonService balloonService;
    private final OrderService orderService;
    private final UserService userService;
    private final SpringTemplateEngine springTemplateEngine;

    public BalloonOrderServlet(BalloonService balloonService, OrderService orderService, UserService userService, SpringTemplateEngine springTemplateEngine) {
        this.balloonService = balloonService;
        this.orderService = orderService;
        this.userService = userService;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("balloonSize", req.getSession().getAttribute("balloonSize"));
        context.setVariable("balloonColor", req.getSession().getAttribute("balloonColor"));
        context.setVariable("clientAddress", null);
        context.setVariable("clientName", null);
        springTemplateEngine.process("deliveryInfo.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Order balloonOrder = orderService.save((String)req.getSession().getAttribute("balloonColor"),
                (String)req.getSession().getAttribute("balloonSize"),
                this.userService.findByUsername(req.getRemoteUser()).get(), null);

        String clientAddress = (String) req.getParameter("clientAddress");
        String clientName = (String) req.getParameter("clientName");

        req.getSession().setAttribute("clientAddress", clientAddress);
        req.getSession().setAttribute("clientName", clientName);
        req.getSession().setAttribute("balloonOrder", balloonOrder);

        resp.sendRedirect("/ConfirmationInfo");

    }
}
