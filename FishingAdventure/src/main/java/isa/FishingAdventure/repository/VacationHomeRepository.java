package isa.FishingAdventure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import isa.FishingAdventure.model.VacationHome;

@Repository
public interface VacationHomeRepository extends JpaRepository<VacationHome, Integer>{

}
