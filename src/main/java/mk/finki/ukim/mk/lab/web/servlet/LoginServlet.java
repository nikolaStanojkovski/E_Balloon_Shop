package mk.finki.ukim.mk.lab.web.servlet;

import mk.finki.ukim.mk.lab.model.User;
import mk.finki.ukim.mk.lab.service.UserService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Login Servlet", urlPatterns = "/loginServlet")
public class LoginServlet extends HttpServlet {

    private final SpringTemplateEngine springTemplateEngine;
    private final UserService userService;

    public LoginServlet(SpringTemplateEngine springTemplateEngine, UserService userService) {
        this.springTemplateEngine = springTemplateEngine;
        this.userService = userService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());
        req.getSession().invalidate();
        // context.setVariable("user", new User());
        this.springTemplateEngine.process("login.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String username = (String) req.getParameter("username");
//        String password = (String) req.getParameter("password");
//
//        if(( username != null && password != null
//        && !userService.checkIfExists(username, password) ) || username==null || password==null ||
//                username.isEmpty() || password.isEmpty() )
//            resp.sendRedirect("/login");
//        else {
//            req.getSession().setAttribute("user", userService.findByUsername(username).getUsername());
//            resp.sendRedirect("/balloons");
//        }

        resp.sendRedirect("/balloons");

    }
}
