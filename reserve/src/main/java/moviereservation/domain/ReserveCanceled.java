package moviereservation.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import moviereservation.domain.*;
import moviereservation.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class ReserveCanceled extends AbstractEvent {

    private Long id;
    private String userId;
    private String reserveStatus;
    private Integer amount;
    private Long movieId;
    private String date;

    public ReserveCanceled(Reserve aggregate) {
        super(aggregate);
    }

    public ReserveCanceled() {
        super();
    }
}
//>>> DDD / Domain Event
