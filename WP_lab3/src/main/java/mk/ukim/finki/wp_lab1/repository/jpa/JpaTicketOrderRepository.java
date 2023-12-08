package mk.ukim.finki.wp_lab1.repository.jpa;

import mk.ukim.finki.wp_lab1.model.TicketOrder;
import mk.ukim.finki.wp_lab1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface JpaTicketOrderRepository extends JpaRepository<TicketOrder, Long> {
    public List<TicketOrder> findAllByUser(User user);

    List<TicketOrder> findAllByDateCreatedBetweenAndUser(LocalDateTime startDate, LocalDateTime endDate, User user);
}
