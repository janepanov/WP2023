package mk.ukim.finki.wp_lab1.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "ticketorders")
public class TicketOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String movieTitle;
    Long numberOfTickets;
    @ManyToOne
    User user;
    @ManyToOne
    ShoppingCart shoppingCart;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dateCreated;

    public TicketOrder(String movieTitle, Long numberOfTickets, User user, LocalDateTime localDateTime) {
        this.id = (long) (Math.random() * 1000);
        this.movieTitle = movieTitle;
        this.numberOfTickets = numberOfTickets;
        this.user = user;
        this.dateCreated = localDateTime;
    }

    public TicketOrder() {

    }
}
