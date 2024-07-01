package moviereservation.domain;

import moviereservation.domain.*;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//<<< PoEAA / Repository
@RepositoryRestResource(collectionResourceRel = "movies", path = "movies")
public interface MovieRepository
    extends PagingAndSortingRepository<Movie, Long> {}
