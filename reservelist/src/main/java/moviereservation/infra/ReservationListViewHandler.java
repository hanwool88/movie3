package moviereservation.infra;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import moviereservation.config.kafka.KafkaProcessor;
import moviereservation.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class ReservationListViewHandler {

    //<<< DDD / CQRS
    @Autowired
    private ReservationListRepository reservationListRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenReserved_then_CREATE_1(@Payload Reserved reserved) {
        try {
            if (!reserved.validate()) return;

            // view 객체 생성
            ReservationList reservationList = new ReservationList();
            // view 객체에 이벤트의 Value 를 set 함
            reservationList.setUserId(reserved.getUserId());
            reservationList.setReserveId(reserved.getId());
            reservationList.setMovieId(reserved.getMovieId());
            reservationList.setOrderStatus("영화예약");
            // view 레파지 토리에 save
            reservationListRepository.save(reservationList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenTicketDecreased_then_UPDATE_1(
        @Payload TicketDecreased ticketDecreased
    ) {
        try {
            if (!ticketDecreased.validate()) return;
            // view 객체 조회

            List<ReservationList> reservationListList = reservationListRepository.findByReserveId(
                ticketDecreased.getReserveId()
            );
            for (ReservationList reservationList : reservationListList) {
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                reservationList.setOrderStatus("영화예약완료");
                // view 레파지 토리에 save
                reservationListRepository.save(reservationList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenTicketIncreased_then_UPDATE_2(
        @Payload TicketIncreased ticketIncreased
    ) {
        try {
            if (!ticketIncreased.validate()) return;
            // view 객체 조회

            List<ReservationList> reservationListList = reservationListRepository.findByReserveId(
                ticketIncreased.getReserveId()
            );
            for (ReservationList reservationList : reservationListList) {
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                // view 레파지 토리에 save
                reservationListRepository.save(reservationList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenOutOfTicket_then_UPDATE_3(
        @Payload OutOfTicket outOfTicket
    ) {
        try {
            if (!outOfTicket.validate()) return;
            // view 객체 조회

            List<ReservationList> reservationListList = reservationListRepository.findByReserveId(
                outOfTicket.getReserveId()
            );
            for (ReservationList reservationList : reservationListList) {
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                reservationList.setOrderStatus("영화예약취소");
                // view 레파지 토리에 save
                reservationListRepository.save(reservationList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //>>> DDD / CQRS
}
