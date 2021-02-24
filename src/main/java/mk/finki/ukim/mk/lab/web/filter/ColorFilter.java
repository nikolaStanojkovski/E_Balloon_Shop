package mk.finki.ukim.mk.lab.web.filter;

import mk.finki.ukim.mk.lab.model.User;
import org.springframework.context.annotation.Profile;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter
@Profile("servlet")
public class ColorFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String color = (String)request.getSession().getAttribute("balloonColor");
        String path = request.getServletPath();

        if(( path!= null && !"/servlet".equals(path) && (color==null || color.isEmpty())) && !"/SearchServlet".equals(path)
                && !"/SearchResults".equals(path) && !path.contains("/balloons") && !path.contains("/orders") && !"/login".equals(path))
            response.sendRedirect("/balloons");

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
