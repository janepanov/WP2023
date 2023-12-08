package mk.ukim.finki.wp_lab1.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.wp_lab1.bootstrap.DataHolder;
import mk.ukim.finki.wp_lab1.model.Movie;
import mk.ukim.finki.wp_lab1.model.TicketOrder;
import mk.ukim.finki.wp_lab1.model.User;
import mk.ukim.finki.wp_lab1.service.MovieService;
import mk.ukim.finki.wp_lab1.service.TicketOrderService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDateTime;
import java.util.Optional;

@Controller
public class TicketOrderController {
    private final TicketOrderService ticketOrderService;
    private final MovieService movieService;

    public TicketOrderController(TicketOrderService ticketOrderService, MovieService movieService){
        this.ticketOrderService = ticketOrderService;
        this.movieService = movieService;
    }

    @PostMapping("/ticketOrder")
    public String showOrderConfirmation(@RequestParam String selectedMovie,
                                        @RequestParam Long numTickets,
                                        @RequestParam (value = "localDateTime", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime localDateTime,
                                        Model model){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Optional<User> currentUser = (Optional<User>) request.getSession().getAttribute("user");

        if(currentUser.isPresent()){
            if(localDateTime == null){
                localDateTime = LocalDateTime.now();
            }

            TicketOrder newOrder = new TicketOrder(selectedMovie, numTickets, currentUser.get(), localDateTime);
            model.addAttribute("ticketOrder", newOrder);
            ticketOrderService.placeOrder(newOrder);

            return "orderConfirmation";
        }

        return "redirect:/movies";
    }

    @GetMapping("/ticketOrder/delete/{id}")
    public String deleteTicket(@RequestParam(required = false) String error,
                               @PathVariable("id") Long id)
    {
        ticketOrderService.deleteById(id);
        return "redirect:/movies";
    }

    @GetMapping("/ticketOrder/filterByDates")
    public String filterTicketsByDate (
            @RequestParam(value = "startDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam(value = "endDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate,
            Model model)
    {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Optional<User> loggedUser = (Optional<User>)request.getSession().getAttribute("user");

        model.addAttribute("movies", movieService.listAll());
        model.addAttribute("tickets", ticketOrderService.findAllByDateCreatedBetweenAndUser(startDate, endDate, loggedUser.get()));

        return "listMovies";
    }
}