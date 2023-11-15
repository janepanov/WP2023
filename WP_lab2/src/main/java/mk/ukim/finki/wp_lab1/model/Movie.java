package mk.ukim.finki.wp_lab1.model;

import lombok.Data;
import org.springframework.context.annotation.Primary;

@Data
public class Movie {
    Long id;
    String title;
    String summary;
    double rating;
    Production production;

    public Movie(String title, String summary, double rating, Production production) {
        this.id = (long) (Math.random() * 1000);
        this.title = title;
        this.summary = summary;
        this.rating = rating;
        this.production = production;
    }
}
