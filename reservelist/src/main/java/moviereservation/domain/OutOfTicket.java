package moviereservation.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;
import moviereservation.infra.AbstractEvent;

@Data
public class OutOfTicket extends AbstractEvent {

    private Long id;
    private Long reserveId;
}
