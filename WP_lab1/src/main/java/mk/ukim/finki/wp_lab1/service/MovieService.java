package mk.ukim.finki.wp_lab1.service;

import mk.ukim.finki.wp_lab1.model.Movie;

import java.util.List;

public interface MovieService {
    List<Movie> listAll();
    List<Movie> searchMovies(String text);
}

