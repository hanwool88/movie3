package moviereservation.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import moviereservation.MovieApplication;
import moviereservation.domain.OutOfTicket;
import moviereservation.domain.TicketDecreased;
import moviereservation.domain.TicketIncreased;

@Entity
@Table(name = "Movie_table")
@Data
//<<< DDD / Aggregate Root
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer stock;

    private String movieName;

    private String date;

    @PostPersist
    public void onPostPersist() {
        TicketDecreased ticketDecreased = new TicketDecreased(this);
        ticketDecreased.publishAfterCommit();

        TicketIncreased ticketIncreased = new TicketIncreased(this);
        ticketIncreased.publishAfterCommit();
    }

    @PostUpdate
    public void onPostUpdate() {
        OutOfTicket outOfTicket = new OutOfTicket(this);
        outOfTicket.publishAfterCommit();
    }

    public static MovieRepository repository() {
        MovieRepository movieRepository = MovieApplication.applicationContext.getBean(
            MovieRepository.class
        );
        return movieRepository;
    }

    //<<< Clean Arch / Port Method
    public static void decreaseTicket(Reserved reserved) {
        //implement business logic here:

        /** Example 1:  new item 
        Movie movie = new Movie();
        repository().save(movie);

        TicketDecreased ticketDecreased = new TicketDecreased(movie);
        ticketDecreased.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        repository().findById(reserved.get???()).ifPresent(movie->{
            
            movie // do something
            repository().save(movie);

            TicketDecreased ticketDecreased = new TicketDecreased(movie);
            ticketDecreased.publishAfterCommit();

         });
        */

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void increaseTicket(ReserveCanceled reserveCanceled) {
        //implement business logic here:

        /** Example 1:  new item 
        Movie movie = new Movie();
        repository().save(movie);

        TicketIncreased ticketIncreased = new TicketIncreased(movie);
        ticketIncreased.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        repository().findById(reserveCanceled.get???()).ifPresent(movie->{
            
            movie // do something
            repository().save(movie);

            TicketIncreased ticketIncreased = new TicketIncreased(movie);
            ticketIncreased.publishAfterCommit();

         });
        */

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
