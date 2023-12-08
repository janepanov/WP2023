package mk.ukim.finki.wp_lab1.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp_lab1.model.Movie;
import mk.ukim.finki.wp_lab1.service.MovieService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "movie-servlet", urlPatterns = "/servlet")
public class MovieServlet extends HttpServlet {
    private final SpringTemplateEngine springTemplateEngine;
    private final MovieService movieService;

    public MovieServlet(SpringTemplateEngine springTemplateEngine, MovieService movieService) {
        this.springTemplateEngine = springTemplateEngine;
        this.movieService = movieService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext context = new WebContext(webExchange);
        context.setVariable("movies", movieService.listAll());
        context.setVariable("totalTickets", getServletContext().getAttribute("totalTickets"));
        springTemplateEngine.process(
                "listMovies.html",
                context,
                resp.getWriter()
        );
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String ipAddress = req.getRemoteAddr();
        String userAgent = req.getHeader("User-agent");
        String title = req.getParameter("selectedMovie");
        int tickets = Integer.parseInt(req.getParameter("numTickets"));
        int totalTickets = (int) getServletContext().getAttribute("totalTickets");
        totalTickets = totalTickets + tickets;
        getServletContext().setAttribute("totalTickets", totalTickets);
        resp.sendRedirect("/servlet/ticketOrder?title=" + title + "&tickets=" + tickets + "&ipAddress=" + ipAddress + "&userAgent=" + userAgent);
    }
}
