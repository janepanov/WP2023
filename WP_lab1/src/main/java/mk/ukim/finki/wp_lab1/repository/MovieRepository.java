package mk.ukim.finki.wp_lab1.repository;

import mk.ukim.finki.wp_lab1.bootstrap.DataHolder;
import mk.ukim.finki.wp_lab1.model.Movie;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class MovieRepository {
    public List<Movie> findAll(){
        return DataHolder.movies;
    }

    public List<Movie> searchMovies(String text){
        return DataHolder.movies
                .stream()
                .filter(m -> m.getTitle().contains(text) ||
                        m.getSummary().contains(text))
                .collect(Collectors.toList());
    }

    public List<Movie> filterMovies(String searchText, Double searchRating) {
        return DataHolder.movies
                .stream()
                .filter(m -> (searchText == null || m.getTitle().contains(searchText)) &&
                        (searchRating == null || m.getRating() >= searchRating))
                .collect(Collectors.toList());
    }
}