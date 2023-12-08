package mk.ukim.finki.wp_lab1.repository.jpa;

import mk.ukim.finki.wp_lab1.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaMovieRepository extends JpaRepository<Movie, Long> {

    List<Movie> findAllByTitleLike(String text);
    List<Movie> findAllByTitleContainingIgnoreCaseOrSummaryContainingIgnoreCaseAndRatingGreaterThanEqual(String searchText, String searchText2, double searchRating);
}
