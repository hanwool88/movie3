package moviereservation.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

//<<< EDA / CQRS
@Entity
@Table(name = "ReservationList_table")
@Data
public class ReservationList {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private String userId;
    private String orderStatus;
    private Long movieId;
    private Long reserveId;
}
