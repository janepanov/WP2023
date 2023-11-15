package mk.ukim.finki.wp_lab1.service;

import mk.ukim.finki.wp_lab1.model.Movie;

import java.util.List;
import java.util.Optional;

public interface MovieService {
    List<Movie> listAll();
    List<Movie> searchMovies(String text);

    void saveMovie(Long id, String title, String summary, double rating, Long production);

    Optional<Movie> findMovieById(Long id);

    void deleteById(Long id);
}

