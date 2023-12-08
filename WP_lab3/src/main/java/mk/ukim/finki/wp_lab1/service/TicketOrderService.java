package mk.ukim.finki.wp_lab1.service;

import mk.ukim.finki.wp_lab1.model.TicketOrder;
import mk.ukim.finki.wp_lab1.model.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface TicketOrderService {
    TicketOrder placeOrder(TicketOrder ticketOrder);
    Optional<TicketOrder> findById(Long id);
    public List<TicketOrder> findAllByUser(User user);
    void deleteById(Long id);
    public List<TicketOrder> findAllByDateCreatedBetweenAndUser(LocalDateTime startDate, LocalDateTime endDate, User user);

}