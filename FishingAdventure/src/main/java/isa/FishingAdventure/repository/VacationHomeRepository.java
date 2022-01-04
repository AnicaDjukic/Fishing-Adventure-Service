package isa.FishingAdventure.repository;

import isa.FishingAdventure.model.AdditionalService;
import isa.FishingAdventure.model.VacationHome;
import isa.FishingAdventure.model.VacationHomeOwner;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VacationHomeRepository extends JpaRepository<VacationHome, Integer>{

    List<VacationHome> findByVacationHomeOwner(VacationHomeOwner owner);
}
