package isa.FishingAdventure.repository;

import isa.FishingAdventure.model.DeleteRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeleteRequestRepository extends JpaRepository<DeleteRequest, Integer> {

    DeleteRequest findByEmail(String email);
}
