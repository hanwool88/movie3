package moviereservation.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import moviereservation.NotificationApplication;

@Entity
@Table(name = "Notification_table")
@Data
//<<< DDD / Aggregate Root
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long reserveId;

    private String reserveStatus;
    
    private String userId;

    public static NotificationRepository repository() {
        NotificationRepository notificationRepository = NotificationApplication.applicationContext.getBean(
            NotificationRepository.class
        );
        return notificationRepository;
    }

    //<<< Clean Arch / Port Method
    public static void notify(TicketDecreased ticketDecreased) {
        
        Notification notification = new Notification();
        notification.setReserveId(ticketDecreased.getReserveId());
        notification.setUserId(ticketDecreased.getUserId());
        notification.setReserveStatus("영화예약완료");
        repository().save(notification);

       

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void notify(TicketIncreased ticketIncreased) {
        
        Notification notification = new Notification();
        notification.setReserveId(ticketIncreased.getReserveId());
        notification.setUserId(ticketIncreased.getUserId());
        notification.setReserveStatus("영화예약취소");
        repository().save(notification);

       

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void notify(OutOfTicket outOfTicket) {
        
        Notification notification = new Notification();
        notification.setReserveId(outOfTicket.getReserveId());
        notification.setUserId(outOfTicket.getUserId());
        notification.setReserveStatus("영화예약취소");
        repository().save(notification);

       

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
