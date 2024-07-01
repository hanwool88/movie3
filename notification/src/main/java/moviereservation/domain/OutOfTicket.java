package moviereservation.domain;

import java.util.*;
import lombok.*;
import moviereservation.domain.*;
import moviereservation.infra.AbstractEvent;

@Data
@ToString
public class OutOfTicket extends AbstractEvent {

    private Long id;
    private Long reserveId;
}
