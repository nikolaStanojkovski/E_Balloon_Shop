package mk.finki.ukim.mk.lab.web.controller;

import mk.finki.ukim.mk.lab.model.Balloon;
import mk.finki.ukim.mk.lab.model.ShoppingCart;
import mk.finki.ukim.mk.lab.model.User;
import mk.finki.ukim.mk.lab.model.enums.Type;
import mk.finki.ukim.mk.lab.service.BalloonService;
import mk.finki.ukim.mk.lab.service.ManufacturerService;
import mk.finki.ukim.mk.lab.service.ShoppingCartService;
import mk.finki.ukim.mk.lab.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
@RequestMapping(value = {"/balloons", "/home", "/"})
public class BalloonController {

    private final BalloonService balloonService;
    private final ShoppingCartService shoppingCartService;
    private final UserService userService;
    private final ManufacturerService manufacturerService;

    public BalloonController(BalloonService balloonService, ShoppingCartService shoppingCartService, UserService userService, ManufacturerService manufacturerService) {
        this.balloonService = balloonService;
        this.shoppingCartService = shoppingCartService;
        this.userService = userService;
        this.manufacturerService = manufacturerService;
    }

    private void checkAndSetErrors(HttpServletRequest request, Model model, String error) {
        if(error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }

        if(request.getSession().getAttribute("filteredBalloons") != null &&
                ((List<String>)request.getSession().getAttribute("filteredBalloons")).size()==0) {
            model.addAttribute("typeErrorSort", true);
            model.addAttribute("typeErrorMessage", "Sorry, there are no such balloons");
        }
    }

    private List<Balloon> filterBalloons(HttpServletRequest request, List<Balloon> balloonList) {
        if(request.getSession().getAttribute("filteredBalloons") != null) {
            List<Balloon> forFilter = (List<Balloon>) request.getSession().getAttribute("filteredBalloons");
            return balloonList.stream().filter(i -> forFilter.stream().
                    anyMatch(u -> u.getName().equals(i.getName()))).collect(Collectors.toList());
        }
        return null;
    }

    private void createShoppingCart(HttpServletRequest request) {
        if(((Long)request.getSession().getAttribute("currentShoppingCart")) == null) {
            User user = userService.findByUsername(request.getRemoteUser()).get();
            ShoppingCart cart = new ShoppingCart(user, LocalDateTime.now());
            shoppingCartService.addShoppingCart(cart);
            request.getSession().setAttribute("currentShoppingCart", cart.getId());
        }
    }

    @GetMapping
    public String getBalloonsPage(@RequestParam(required = false) String error,
                                  Model model, HttpServletRequest request) {

        checkAndSetErrors(request, model, error);
        List<Balloon> balloonList = balloonService.sort();

        if(userService.findByUsername(request.getRemoteUser()).isPresent())
            createShoppingCart(request);

        if(filterBalloons(request, balloonList) != null)
            balloonList = filterBalloons(request, balloonList);

        request.getSession().setAttribute("filteredBalloons", null);
        model.addAttribute("balloonList", balloonList);
        request.getSession().setAttribute("balloonList", balloonList);
        List<String> enumNames = Stream.of(Type.values()).map(Type::name)
                .collect(Collectors.toList());
        model.addAttribute("type", enumNames);
        return "listBalloons";
    }

    @PostMapping("/add-to-order/{id}")
    public String addToOrder(@PathVariable Long id, HttpServletRequest request) throws IOException {
        String balloonColor = this.balloonService.searchById(id).getName();
        request.getSession().setAttribute("balloonColor", balloonColor);
        return "redirect:/selectBalloon";
    }

    @GetMapping("/edit-form/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getEditBalloonPage(@PathVariable Long id, Model model, HttpServletRequest request) {
        if(this.balloonService.searchById(id) != null) {
            model.addAttribute("balloonToEdit", this.balloonService.searchById(id));
            model.addAttribute("manufacturerList", this.manufacturerService.findAll());
            List<String> enumNames = Stream.of(Type.values()).map(Type::name)
                    .collect(Collectors.toList());
            model.addAttribute("type", enumNames);
            request.getSession().setAttribute("toChange", false);
            return "add-balloon";
        }
        else {
            return "redirect:/balloons?error=Requested+balloon+is+not+found";
        }
    }

    @GetMapping("/add-balloon")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String saveBalloonPage(Model model, HttpServletRequest request) {
        model.addAttribute("manufacturerList", this.manufacturerService.findAll());
        request.getSession().setAttribute("toChange", true);
        List<String> enumNames = Stream.of(Type.values()).map(Type::name)
                .collect(Collectors.toList());
        model.addAttribute("type", enumNames);
        return "add-balloon";
    }

    @PostMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteBalloon(@PathVariable Long id) {
        balloonService.delete(id);
        return "redirect:/balloons";
    }

    @PostMapping("/add")
    public String saveBalloon(@RequestParam String name,
                              @RequestParam String description,
                              @RequestParam Long id,
                              @RequestParam Long manufacturer,
                              @RequestParam String type, HttpServletRequest request) {
        if(request.getSession().getAttribute("toChange") != null  &&
                (boolean)request.getSession().getAttribute("toChange")) {
            request.getSession().setAttribute("toChange", null);
            if(this.balloonService.findByName(name).isPresent())
                return "redirect:/balloons?error=Cannot+add+balloon+with+the+same+name";
        }

        this.balloonService.save(name, description, id, this.manufacturerService.findById(manufacturer), Type.valueOf(type));
        return "redirect:/balloons";
    }

    @PostMapping("/filter")
    public String filterByType(@RequestParam String type, HttpServletRequest request) {
        List<Balloon> balloons = this.balloonService.filterByType(Type.valueOf(type));
        request.getSession().setAttribute("filteredBalloons", balloons);
        return "redirect:/balloons";
    }

    @PostMapping("/search")
    public String search(@RequestParam String resultBallons, HttpServletRequest request) {
        List<Balloon> balloons = this.balloonService.findByNameOrDescriptionOrTypeOrManufacturer(resultBallons.trim());
        request.getSession().setAttribute("filteredBalloons", balloons);
        return "redirect:/balloons";
    }

}
