package mk.ukim.finki.wp_lab1.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.wp_lab1.bootstrap.DataHolder;
import mk.ukim.finki.wp_lab1.model.Movie;
import mk.ukim.finki.wp_lab1.model.User;
import mk.ukim.finki.wp_lab1.service.MovieService;
import mk.ukim.finki.wp_lab1.service.ProductionService;
import mk.ukim.finki.wp_lab1.service.TicketOrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.xml.crypto.Data;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/movies")
public class MovieController {
    private final MovieService movieService;
    private final ProductionService productionService;
    private final TicketOrderService ticketOrderService;

    public MovieController(MovieService movieService, ProductionService productionService, TicketOrderService ticketOrderService){
        this.movieService = movieService;
        this.productionService = productionService;
        this.ticketOrderService = ticketOrderService;
    }

    @GetMapping
    public String getMoviesPage(@RequestParam(required = false) String error, Model model){
        model.addAttribute("movies", movieService.listAll());
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Optional<User> loggedUser = (Optional<User>)request.getSession().getAttribute("user");

        model.addAttribute("tickets", ticketOrderService.findAllByUser(loggedUser.get()));
        model.addAttribute("totalTickets", ticketOrderService.findAllByUser(loggedUser.get()).stream().mapToInt(o -> Math.toIntExact(o.getNumberOfTickets())).sum());
        model.addAttribute("movies", movieService.listAll());

        return "listMovies";
    }

    @GetMapping("/add")
    public String addMovie(@RequestParam(required = false) String error, Model model){
        model.addAttribute("productions", productionService.findAll());
        model.addAttribute("movieId", (long) (Math.random() * 1000));

        return "add-movie";
    }

    @PostMapping("/save")
    public String saveMovie(@RequestParam(required = false) String error,
                            @RequestParam Long id,
                            @RequestParam String title,
                            @RequestParam String summary,
                            @RequestParam double rating,
                            @RequestParam Long production)
    {
        movieService.saveMovie(id, title, summary, rating, production);
        return "redirect:/movies";
    }

    @GetMapping("/edit/{id}")
    public String editMovie(@RequestParam(required = false) String error,
                            @PathVariable(required = false) Long id,
                            Model model)
    {
        Optional<Movie> movie = this.movieService.findMovieById(id);

        if(!movie.isPresent()){
            return "redirect:/movies";
        }

        model.addAttribute("movie", movie.get());
        model.addAttribute("productions", productionService.findAll());
        return "add-movie";
    }

    @GetMapping("/delete/{id}")
    public String deleteMovie(@RequestParam(required = false) String error,
                              @PathVariable Long id){
        this.movieService.deleteById(id);
        return "redirect:/movies";
    }
}
