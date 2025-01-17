package moviereservation.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;
import moviereservation.infra.AbstractEvent;

@Data
public class TicketIncreased extends AbstractEvent {

    private Long id;
    private Integer stock;
    private String movieName;
    private Long reserveId;
    private String date;
    private String userId;
}
