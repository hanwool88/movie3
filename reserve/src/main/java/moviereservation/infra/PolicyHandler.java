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
    ReserveRepository reserveRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='OutOfTicket'"
    )
    public void wheneverOutOfTicket_UpdateStatus(
        @Payload OutOfTicket outOfTicket
    ) {
        OutOfTicket event = outOfTicket;
        System.out.println(
            "\n\n##### listener UpdateStatus : " + outOfTicket + "\n\n"
        );

        // Sample Logic //
        Reserve.updateStatus(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='TicketIncreased'"
    )
    public void wheneverTicketIncreased_UpdateStatus(
        @Payload TicketIncreased ticketIncreased
    ) {
        TicketIncreased event = ticketIncreased;
        System.out.println(
            "\n\n##### listener UpdateStatus : " + ticketIncreased + "\n\n"
        );

        // Sample Logic //
        Reserve.updateStatus(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='TicketDecreased'"
    )
    public void wheneverTicketDecreased_UpdateStatus(
        @Payload TicketDecreased ticketDecreased
    ) {
        TicketDecreased event = ticketDecreased;
        System.out.println(
            "\n\n##### listener UpdateStatus : " + ticketDecreased + "\n\n"
        );

        // Sample Logic //
        Reserve.updateStatus(event);
    }

   
}
//>>> Clean Arch / Inbound Adaptor
