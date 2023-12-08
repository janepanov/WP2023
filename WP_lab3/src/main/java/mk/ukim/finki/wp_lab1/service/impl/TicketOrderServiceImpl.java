package mk.ukim.finki.wp_lab1.service.impl;

import mk.ukim.finki.wp_lab1.model.TicketOrder;
import mk.ukim.finki.wp_lab1.model.User;
import mk.ukim.finki.wp_lab1.repository.inmemory.TicketOrderRepository;
import mk.ukim.finki.wp_lab1.repository.jpa.JpaTicketOrderRepository;
import mk.ukim.finki.wp_lab1.repository.jpa.JpaUserRepository;
import mk.ukim.finki.wp_lab1.service.TicketOrderService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TicketOrderServiceImpl implements TicketOrderService {
    private final JpaTicketOrderRepository ticketOrderRepository;
    private final JpaUserRepository userRepository;

    public TicketOrderServiceImpl(JpaTicketOrderRepository ticketOrderRepository, JpaUserRepository userRepository) {
        this.ticketOrderRepository = ticketOrderRepository;
        this.userRepository = userRepository;
    }

    @Override
    public TicketOrder placeOrder(TicketOrder ticketOrder) {
        return this.ticketOrderRepository.save(ticketOrder);
    }

    @Override
    public Optional<TicketOrder> findById(Long id) {
        return ticketOrderRepository.findById(id);
    }

    @Override
    public List<TicketOrder> findAllByUser(User user){
        return this.ticketOrderRepository.findAllByUser(user);
    }

    @Override
    public void deleteById(Long id){
        ticketOrderRepository.deleteById(id);
    }

    @Override
    public List<TicketOrder> findAllByDateCreatedBetweenAndUser(LocalDateTime startDate, LocalDateTime endDate, User user){
        return ticketOrderRepository.findAllByDateCreatedBetweenAndUser(startDate, endDate, user);
    }
}
