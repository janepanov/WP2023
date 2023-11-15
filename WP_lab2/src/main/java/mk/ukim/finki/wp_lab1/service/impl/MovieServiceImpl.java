package mk.ukim.finki.wp_lab1.service.impl;

import mk.ukim.finki.wp_lab1.model.Movie;
import mk.ukim.finki.wp_lab1.repository.MovieRepository;
import mk.ukim.finki.wp_lab1.repository.ProductionRepository;
import mk.ukim.finki.wp_lab1.service.MovieService;
import org.springframework.stereotype.Service;
import org.thymeleaf.spring6.expression.Mvc;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;
    private final ProductionRepository productionRepository;

    public MovieServiceImpl(MovieRepository movieRepository, ProductionRepository productionRepository){
        this.movieRepository = movieRepository;
        this.productionRepository = productionRepository;
    }
    @Override
    public List<Movie> listAll() {
        return this.movieRepository.findAll();
    }

    @Override
    public List<Movie> searchMovies(String text) {
        return this.movieRepository.searchMovies(text);
    }

    @Override
    public void saveMovie(Long id, String title, String summary, double rating, Long production){
        Movie movieToSave = new Movie(title, summary, rating, this.productionRepository.findProductionById(production));
        movieToSave.setId(id);
        movieRepository.saveMovie(movieToSave);
    }

    @Override
    public Optional<Movie> findMovieById(Long id){
        Optional<Movie> movie = this.movieRepository.findMovieById(id);
        return movie;
    }

    public void deleteById(Long id){
        this.movieRepository.deleteById(id);
    }
}
