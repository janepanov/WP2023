package mk.ukim.finki.wp_lab1.service.impl;

import mk.ukim.finki.wp_lab1.model.TicketOrder;
import mk.ukim.finki.wp_lab1.repository.TicketOrderRepository;
import mk.ukim.finki.wp_lab1.service.TicketOrderService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TicketOrderServiceImpl implements TicketOrderService {
    public TicketOrderRepository ticketOrderRepository;

    public TicketOrderServiceImpl(TicketOrderRepository ticketOrderRepository){
        this.ticketOrderRepository = ticketOrderRepository;
    }

    @Override
    public TicketOrder placeOrder(String movieTitle, String clientName, String address, int numberOfTickets) {
        return null;
    }

    @Override
    public Optional<TicketOrder> findById(Long id) {
        return this.ticketOrderRepository.findById(id);
    }
}
