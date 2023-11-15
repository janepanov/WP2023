package mk.ukim.finki.wp_lab1.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.wp_lab1.bootstrap.DataHolder;
import mk.ukim.finki.wp_lab1.model.TicketOrder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Optional;

@Controller
public class TicketOrderController {
    @PostMapping("/ticketOrder")
    public String showOrderConfirmation(@RequestParam String selectedMovie,
                                        @RequestParam Long numTickets,
                                        Model model){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        TicketOrder ticketOrder = new TicketOrder(selectedMovie, request.getHeader("User-Agent"), request.getRemoteAddr(), numTickets);
        DataHolder.ticketOrders.add(ticketOrder);

        DataHolder.totalTickets = (int) (DataHolder.totalTickets + numTickets);
        model.addAttribute("userAgent", request.getHeader("User-Agent"));
        model.addAttribute("ipAddress", request.getRemoteAddr());
        model.addAttribute("title", selectedMovie);
        model.addAttribute("tickets", numTickets);
        model.addAttribute("ticketOrder", ticketOrder);

        return "orderConfirmation";
    }

    @GetMapping("/ticketOrder/delete/{id}")
    public String deleteTicket(@RequestParam(required = false) String error,
                               @PathVariable("id") Long id)
    {
        Optional<TicketOrder> order = DataHolder.ticketOrders.stream().filter(r -> r.getId().equals(id)).findFirst();
        if(order.isPresent())
        {
            DataHolder.ticketOrders.remove(order.get());
            DataHolder.totalTickets -= order.get().getNumberOfTickets();
        }

        return "redirect:/movies";
    }
}