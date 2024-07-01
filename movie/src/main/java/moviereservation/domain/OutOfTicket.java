package moviereservation.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import moviereservation.domain.*;
import moviereservation.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class OutOfTicket extends AbstractEvent {

    private Long id;
    private Long reserveId;

    public OutOfTicket(Movie aggregate) {
        super(aggregate);
    }

    public OutOfTicket() {
        super();
    }
}
//>>> DDD / Domain Event
