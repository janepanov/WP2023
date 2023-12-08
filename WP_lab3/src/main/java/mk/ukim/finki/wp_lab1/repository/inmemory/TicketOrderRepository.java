package mk.ukim.finki.wp_lab1.repository.inmemory;

import mk.ukim.finki.wp_lab1.bootstrap.DataHolder;
import mk.ukim.finki.wp_lab1.model.TicketOrder;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class TicketOrderRepository {
    public Optional<TicketOrder> findById(Long id) {
        return DataHolder.ticketOrders.stream().filter(i -> i.getId().equals(id)).findFirst();
    }

}
