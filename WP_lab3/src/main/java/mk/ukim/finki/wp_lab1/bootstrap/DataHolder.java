package mk.ukim.finki.wp_lab1.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp_lab1.model.Movie;
import mk.ukim.finki.wp_lab1.model.Production;
import mk.ukim.finki.wp_lab1.model.TicketOrder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Movie> movies = null;
    public static List<Production> productions = null;
    public static int totalTickets;
    public static List<TicketOrder> ticketOrders;

    @PostConstruct
    public void init() {
        movies = new ArrayList<>();
        productions = new ArrayList<>();
        totalTickets = 0;
        ticketOrders = new ArrayList<>();

        movies.add(new Movie("American Psycho", "A wealthy New York City investment banking executive, Patrick Bateman, hides his alternate psychopathic ego from his co-workers and friends as he delves deeper into his violent, hedonistic fantasies.", 7.6, new Production("Production 1", "USA", "Address of production 1")));
        movies.add(new Movie("Joker", "During the 1980s, a failed stand-up comedian is driven insane and turns to a life of crime and chaos in Gotham City while becoming an infamous psychopathic crime figure.", 8.4, new Production("Production 1", "USA", "Address of production 1")));
        movies.add(new Movie("Drive", "A mysterious Hollywood action film stuntman gets in trouble with gangsters when he tries to help his neighbor's husband rob a pawn shop while serving as his getaway driver.", 7.8, new Production("Production 2", "UK", "Address of production 2")));
        movies.add(new Movie("Taxi Driver", "A mentally unstable veteran works as a nighttime taxi driver in New York City, where the perceived decadence and sleaze fuels his urge for violent action.", 8.2, new Production("Production 2", "UK", "Address of production 2")));
        movies.add(new Movie("Fight Club", "An insomniac office worker and a devil-may-care soap maker form an underground fight club that evolves into much more.", 8.8, new Production("Production 3", "GER", "Address of production 3")));
        movies.add(new Movie("Blade Runner 2049", "Young Blade Runner K's discovery of a long-buried secret leads him to track down former Blade Runner Rick Deckard, who's been missing for thirty years.", 8, new Production("Production 3", "GER", "Address of production 3")));
        movies.add(new Movie("The Wolf of Wall Street", "Based on the true story of Jordan Belfort, from his rise to a wealthy stock-broker living the high life to his fall involving crime, corruption and the federal government.", 8.2, new Production("Production 4", "MK", "Address of production 4")));
        movies.add(new Movie("The Dark Knight", "When the menace known as the Joker wreaks havoc and chaos on the people of Gotham, Batman must accept one of the greatest psychological and physical tests of his ability to fight injustice.", 9, new Production("Production 4", "MK", "Address of production 4")));
        movies.add(new Movie("Prisoners", "When Keller Dover's daughter and her friend go missing, he takes matters into his own hands as the police pursue multiple leads and the pressure mounts.", 8.1, new Production("Production 5", "JPN", "Address of production 5")));
        movies.add(new Movie("The Pursuit of Happyness", "A struggling salesman takes custody of his son as he's poised to begin a life-changing professional career.", 8, new Production("Production 5", "JPN", "Address of production 5")));

        productions.add(new Production("Production 1", "USA", "Address of production 1"));
        productions.add(new Production("Production 2", "UK", "Address of production 2"));
        productions.add(new Production("Production 3", "GER", "Address of production 3"));
        productions.add(new Production("Production 4", "MK", "Address of production 4"));
        productions.add(new Production("Production 5", "JPN", "Address of production 5"));
    }
}
