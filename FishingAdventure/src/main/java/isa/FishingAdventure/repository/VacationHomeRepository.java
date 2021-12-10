package isa.FishingAdventure.repository;

import isa.FishingAdventure.model.ServiceProfile;
import isa.FishingAdventure.model.VacationHome;
import isa.FishingAdventure.model.VacationHomeOwner;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VacationHomeRepository extends ServiceProfileRepository{

    List<VacationHome> findByVocationHomeOwner(VacationHomeOwner owner);
}
