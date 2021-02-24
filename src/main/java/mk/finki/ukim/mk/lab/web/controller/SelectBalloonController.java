package mk.finki.ukim.mk.lab.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/selectBalloon")
public class SelectBalloonController {

    @GetMapping
    public String getSelectBalloonPage(Model model, HttpServletRequest req) {
        model.addAttribute("balloonColor", req.getSession().getAttribute("balloonColor"));
        return "selectBalloonSize";
    }

    @PostMapping
    public String postSelectBalloonsPage(@RequestParam String size, HttpServletRequest req) {
        req.getSession().setAttribute("balloonSize", size);
        req.getSession().setAttribute("balloonColor", req.getSession().getAttribute("balloonColor"));
        return "redirect:/balloonOrder";
    }

}
