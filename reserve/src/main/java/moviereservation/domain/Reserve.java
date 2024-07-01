package moviereservation.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import moviereservation.ReserveApplication;
import moviereservation.domain.Reserved;

@Entity
@Table(name = "Reserve_table")
@Data
//<<< DDD / Aggregate Root
public class Reserve {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String userId;

    private String reserveStatus;

    private Integer amount;

    private Long movieId;

    private String date;

    @PostPersist
    public void onPostPersist() {
        Reserved reserved = new Reserved(this);
        reserved.publishAfterCommit();
    }

    public static ReserveRepository repository() {
        ReserveRepository reserveRepository = ReserveApplication.applicationContext.getBean(
            ReserveRepository.class
        );
        return reserveRepository;
    }

    //<<< Clean Arch / Port Method
    public void reserveCancel() {
        //implement business logic here:

        ReserveCanceled reserveCanceled = new ReserveCanceled(this);
        reserveCanceled.publishAfterCommit();
    }



    //<<< Clean Arch / Port Method
    public static void updateStatus(OutOfTicket outOfTicket) {
        repository().findById(outOfTicket.getReserveId()).ifPresent(reserve ->{
            
            reserve.setReserveStatus("reserveCanceled");
            repository().save(reserve);
        });
    }


    public static void updateStatus(TicketIncreased ticketIncreased) {
        repository().findById(ticketIncreased.getReserveId()).ifPresent(reserve ->{
            
            reserve.setReserveStatus("reserveCanceled");
            repository().save(reserve);
        });
    }

    public static void updateStatus(TicketDecreased ticketDecreased) {
        repository().findById(ticketDecreased.getReserveId()).ifPresent(reserve ->{
            
            reserve.setReserveStatus("reserveCompleted");
            repository().save(reserve);
        });
    }

}
//>>> DDD / Aggregate Root
