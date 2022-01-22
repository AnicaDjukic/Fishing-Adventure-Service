package isa.FishingAdventure.repository;

import isa.FishingAdventure.model.Client;
import isa.FishingAdventure.model.User;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.List;

@Repository
public interface ClientRepository extends UserRepository {

    Client save(Client client);

    Client findByEmail(String email);

    @Lock(value = LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT c FROM Client c WHERE c.email = :email")
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value = "0")})
    Client findClientWithPessimisticLock(String email);

    @Query(value = "SELECT * FROM client_subscriptions WHERE subscriptions_id = ?1", nativeQuery = true)
    List<Integer> findClientIdBySubscription(Integer serviceProfileId);

    User getByUserId(Integer id);
}
