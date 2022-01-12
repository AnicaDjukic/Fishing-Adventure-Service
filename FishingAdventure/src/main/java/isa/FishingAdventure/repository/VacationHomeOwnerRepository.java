package isa.FishingAdventure.repository;

import isa.FishingAdventure.model.VacationHomeOwner;
import org.springframework.stereotype.Repository;

@Repository
public interface VacationHomeOwnerRepository extends UserRepository {

    VacationHomeOwner findByEmail(String email);

    VacationHomeOwner save(VacationHomeOwner vacationHomeOwner);

}
