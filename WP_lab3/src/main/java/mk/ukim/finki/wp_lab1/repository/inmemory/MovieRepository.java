package mk.ukim.finki.wp_lab1.repository.inmemory;

import mk.ukim.finki.wp_lab1.bootstrap.DataHolder;
import mk.ukim.finki.wp_lab1.model.Movie;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
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

    public void saveMovie(Movie movie) {
        if(movie.getId() == null)
            DataHolder.movies.add(new Movie(movie.getTitle(), movie.getSummary(), movie.getRating(), movie.getProduction()));
        else
        {
            Optional<Movie> movieExists = DataHolder.movies.stream().filter(mov -> mov.getId().equals(movie.getId())).findFirst();
            if(movieExists.isPresent())
            {
                DataHolder.movies.stream().filter(r -> r.getId().equals(movieExists.get().getId())).findFirst().get().setTitle(movie.getTitle());
                DataHolder.movies.stream().filter(r -> r.getId().equals(movieExists.get().getId())).findFirst().get().setSummary(movie.getSummary());
                DataHolder.movies.stream().filter(r -> r.getId().equals(movieExists.get().getId())).findFirst().get().setRating(movie.getRating());
                DataHolder.movies.stream().filter(r -> r.getId().equals(movieExists.get().getId())).findFirst().get().setProduction(movie.getProduction());
            }
            else
                DataHolder.movies.add(new Movie(movie.getTitle(), movie.getSummary(), movie.getRating(), movie.getProduction()));
        }
    }

    public Optional<Movie> findMovieById(Long id){
        return DataHolder.movies.stream().filter(r -> r.getId().equals(id)).findFirst();
    }

    public void deleteById(Long id){
        Optional<Movie> movie = DataHolder.movies.stream().filter(r -> r.getId().equals(id)).findFirst();

        if(movie.isPresent()){
            DataHolder.movies.remove(movie.get());
        }
    }

}