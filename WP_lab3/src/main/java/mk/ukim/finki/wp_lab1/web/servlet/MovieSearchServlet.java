package mk.ukim.finki.wp_lab1.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp_lab1.model.Movie;
import mk.ukim.finki.wp_lab1.repository.inmemory.MovieRepository;
import mk.ukim.finki.wp_lab1.repository.jpa.JpaMovieRepository;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "movie-search", urlPatterns = "/servlet/search")
public class MovieSearchServlet extends HttpServlet {
    private final JpaMovieRepository movieRepository;
    private final SpringTemplateEngine springTemplateEngine;

    public MovieSearchServlet(JpaMovieRepository movieRepository, SpringTemplateEngine springTemplateEngine){
        this.movieRepository = movieRepository;
        this.springTemplateEngine = springTemplateEngine;
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String searchText = req.getParameter("searchText");
        double searchRating = Double.parseDouble(req.getParameter("searchRating"));

        List<Movie> searchResults = movieRepository.findAllByTitleContainingIgnoreCaseOrSummaryContainingIgnoreCaseAndRatingGreaterThanEqual(searchText, searchText, searchRating);

        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext context = new WebContext(webExchange);
        context.setVariable("searchResults", searchResults);
        context.setVariable("totalTickets", getServletContext().getAttribute("totalTickets"));

        springTemplateEngine.process(
                "filteredMovies.html",
                context,
                resp.getWriter()
        );
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
