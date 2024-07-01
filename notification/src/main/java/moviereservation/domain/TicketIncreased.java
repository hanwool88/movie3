package moviereservation.domain;

import java.util.*;
import lombok.*;
import moviereservation.domain.*;
import moviereservation.infra.AbstractEvent;

@Data
@ToString
public class TicketIncreased extends AbstractEvent {

    private Long id;
    private Integer stock;
    private String movieName;
    private Long reserveId;
    private String date;
}
