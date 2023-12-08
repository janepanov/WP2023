package mk.ukim.finki.wp_lab1.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    @Convert(converter = UserFullNameConverter.class)
    private UserFullName fullName;
    private String password;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateOfBirth;
    @OneToMany(mappedBy = "user")
    private List<ShoppingCart> carts;
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<TicketOrder> ticketOrders;

    public User(String username, UserFullName fullName, String password, LocalDate dateOfBirth, List<ShoppingCart> carts) {
        this.id = (long) (Math.random() * 1000);
        this.username = username;
        this.fullName = fullName;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.carts = carts;
    }

    public User() {

    }

    public User(String username, String password, UserFullName fullName) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.dateOfBirth = LocalDate.now();
    }
}
