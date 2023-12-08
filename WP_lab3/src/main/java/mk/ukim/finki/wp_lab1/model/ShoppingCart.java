package mk.ukim.finki.wp_lab1.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "shoppingcarts")
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private User user;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dateCreated;
    @OneToMany(mappedBy = "shoppingCart")
    private List<TicketOrder> ticketOrders;

    public ShoppingCart(User user, LocalDateTime dateCreated, List<TicketOrder> ticketOrders) {
        this.id = (long) (Math.random() * 1000);
        this.user = user;
        this.dateCreated = dateCreated;
        this.ticketOrders = ticketOrders;
    }

    public ShoppingCart() {

    }
}
