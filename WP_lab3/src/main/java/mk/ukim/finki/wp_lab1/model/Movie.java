package mk.ukim.finki.wp_lab1.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.context.annotation.Primary;

@Data
@Entity
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String title;
    String summary;
    double rating;
    @ManyToOne
    Production production;

    public Movie(String title, String summary, double rating, Production production) {
        this.id = (long) (Math.random() * 1000);
        this.title = title;
        this.summary = summary;
        this.rating = rating;
        this.production = production;
    }

    public Movie() {

    }
}
