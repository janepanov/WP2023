package mk.ukim.finki.wp_lab1.web.controller;

import mk.ukim.finki.wp_lab1.bootstrap.DataHolder;
import mk.ukim.finki.wp_lab1.model.Movie;
import mk.ukim.finki.wp_lab1.service.MovieService;
import mk.ukim.finki.wp_lab1.service.ProductionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.Data;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/movies")
public class MovieController {
    private final MovieService movieService;
    private final ProductionService productionService;

    public MovieController(MovieService movieService, ProductionService productionService){
        this.movieService = movieService;
        this.productionService = productionService;
    }

    @GetMapping
    public String getMoviesPage(@RequestParam(required = false) String error, Model model){
        model.addAttribute("movies", movieService.listAll());
        model.addAttribute("totalTickets", DataHolder.totalTickets);
        model.addAttribute("tickets", DataHolder.ticketOrders);
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
