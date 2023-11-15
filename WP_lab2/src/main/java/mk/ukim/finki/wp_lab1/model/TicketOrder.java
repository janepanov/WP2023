package mk.ukim.finki.wp_lab1.model;

import lombok.Data;

@Data
public class TicketOrder {
    Long id;
    String movieTitle;
    String clientName;
    String clientAddress;
    Long numberOfTickets;

    public TicketOrder(String movieTitle, String clientName, String clientAddress, Long numberOfTickets) {
        this.id = (long) (Math.random() * 1000);
        this.movieTitle = movieTitle;
        this.clientName = clientName;
        this.clientAddress = clientAddress;
        this.numberOfTickets = numberOfTickets;
    }
}
