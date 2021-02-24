package mk.finki.ukim.mk.lab.web.controller;

import mk.finki.ukim.mk.lab.model.Order;
import mk.finki.ukim.mk.lab.model.User;
import mk.finki.ukim.mk.lab.service.BalloonService;
import mk.finki.ukim.mk.lab.service.OrderService;
import mk.finki.ukim.mk.lab.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
@RequestMapping("/balloonOrder")
public class OrderBalloonController {

    private final BalloonService balloonService;
    private final OrderService orderService;
    private final UserService userService;

    public OrderBalloonController(BalloonService balloonService, OrderService orderService, UserService userService) {
        this.balloonService = balloonService;
        this.orderService = orderService;
        this.userService = userService;
    }

    @GetMapping
    public String getOrderBalloonPage(Model model, HttpServletRequest req) {
        model.addAttribute("balloonSize", req.getSession().getAttribute("balloonSize"));
        model.addAttribute("balloonColor", req.getSession().getAttribute("balloonColor"));
        model.addAttribute("clientAddress", null);
        model.addAttribute("clientName", null);
        return "/deliveryInfo";
    }

    @PostMapping
    public String getOrderPostBalloonPage(@RequestParam String clientAddress,
                                          @RequestParam String clientName,
                                          HttpServletRequest req) throws IOException {
        Order balloonOrder = orderService.save((String)req.getSession().getAttribute("balloonColor"),
                (String)req.getSession().getAttribute("balloonSize"),
                this.userService.findByUsername(req.getRemoteUser()).get(), null);

        req.getSession().setAttribute("clientAddress", clientAddress);
        req.getSession().setAttribute("clientName", clientName);
        req.getSession().setAttribute("balloonOrder", balloonOrder);

        return "redirect:/confirmationInfo";

    }

}
