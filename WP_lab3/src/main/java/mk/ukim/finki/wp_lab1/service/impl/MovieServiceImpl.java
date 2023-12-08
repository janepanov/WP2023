package mk.ukim.finki.wp_lab1.service.impl;

import mk.ukim.finki.wp_lab1.model.Movie;
import mk.ukim.finki.wp_lab1.repository.inmemory.MovieRepository;
import mk.ukim.finki.wp_lab1.repository.inmemory.ProductionRepository;
import mk.ukim.finki.wp_lab1.repository.jpa.JpaMovieRepository;
import mk.ukim.finki.wp_lab1.repository.jpa.JpaProductionRepository;
import mk.ukim.finki.wp_lab1.service.MovieService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {
    private final JpaMovieRepository movieRepository;
    private final JpaProductionRepository productionRepository;

    public MovieServiceImpl(JpaMovieRepository movieRepository, JpaProductionRepository productionRepository){
        this.movieRepository = movieRepository;
        this.productionRepository = productionRepository;
    }
    @Override
    public List<Movie> listAll() {
        return this.movieRepository.findAll();
    }

    @Override
    public List<Movie> searchMovies(String text) {
        return this.movieRepository.findAllByTitleLike(text);
    }

    @Override
    public void saveMovie(Long id, String title, String summary, double rating, Long production){
        Movie movieToSave = new Movie(title, summary, rating, this.productionRepository.findById(production).get());
        movieToSave.setId(id);
        movieRepository.save(movieToSave);
    }

    @Override
    public Optional<Movie> findMovieById(Long id){
        Optional<Movie> movie = this.movieRepository.findById(id);
        return movie;
    }

    public void deleteById(Long id){
        this.movieRepository.deleteById(id);
    }
}
