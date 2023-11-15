package mk.ukim.finki.wp_lab1.service;

import mk.ukim.finki.wp_lab1.model.TicketOrder;

import java.util.Optional;

public interface TicketOrderService {
    TicketOrder placeOrder(String movieTitle, String clientName, String address, int numberOfTickets);
    Optional<TicketOrder> findById(Long id);
}