package mk.ukim.finki.wp_lab1.service.impl;

import mk.ukim.finki.wp_lab1.model.Movie;
import mk.ukim.finki.wp_lab1.repository.MovieRepository;
import mk.ukim.finki.wp_lab1.service.MovieService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;

    public MovieServiceImpl(MovieRepository movieRepository){
        this.movieRepository = movieRepository;
    }
    @Override
    public List<Movie> listAll() {
        return this.movieRepository.findAll();
    }

    @Override
    public List<Movie> searchMovies(String text) {
        return this.movieRepository.searchMovies(text);
    }
}
