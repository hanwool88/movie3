package moviereservation.infra;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.naming.NameParser;
import javax.naming.NameParser;
import javax.transaction.Transactional;
import moviereservation.config.kafka.KafkaProcessor;
import moviereservation.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

//<<< Clean Arch / Inbound Adaptor
@Service
@Transactional
public class PolicyHandler {

    @Autowired
    NotificationRepository notificationRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='ReserveCanceled'"
    )
    public void wheneverReserveCanceled_Notify(
        @Payload ReserveCanceled reserveCanceled
    ) {
        ReserveCanceled event = reserveCanceled;
        System.out.println(
            "\n\n##### listener Notify : " + reserveCanceled + "\n\n"
        );

        // Sample Logic //
        Notification.notify(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='Reserved'"
    )
    public void wheneverReserved_Notify(@Payload Reserved reserved) {
        Reserved event = reserved;
        System.out.println("\n\n##### listener Notify : " + reserved + "\n\n");

        // Sample Logic //
        Notification.notify(event);
    }
}
//>>> Clean Arch / Inbound Adaptor
