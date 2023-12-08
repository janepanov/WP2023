package mk.ukim.finki.wp_lab1.web.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp_lab1.model.User;

import java.io.IOException;
import java.util.Optional;

@WebFilter(filterName="auth-filter",urlPatterns = "/*",
        dispatcherTypes = {DispatcherType.REQUEST,DispatcherType.FORWARD},
        initParams = {
                @WebInitParam(name="login-path", value="/login"),
                @WebInitParam(name="register-path", value="/register")
        })
public class LoginFilter implements Filter {
    private String loginPath;
    private String registerPath;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
        loginPath = filterConfig.getInitParameter("login-path");
        registerPath = filterConfig.getInitParameter("register-path");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //pre processing
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String path = request.getRequestURI();
        Optional<User> user = (Optional<User>) request.getSession().getAttribute("user");
        if(path.startsWith(loginPath) || path.startsWith(registerPath) || user != null){
            filterChain.doFilter(servletRequest, servletResponse);
        }

        else{
            response.sendRedirect("/login");
        }
        //post processing
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
