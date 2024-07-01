package moviereservation.infra;

import java.util.List;
import moviereservation.domain.*;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(
    collectionResourceRel = "reservationLists",
    path = "reservationLists"
)
public interface ReservationListRepository
    extends PagingAndSortingRepository<ReservationList, Long> {
    List<ReservationList> findByReserveId(Long reserveId);
}
