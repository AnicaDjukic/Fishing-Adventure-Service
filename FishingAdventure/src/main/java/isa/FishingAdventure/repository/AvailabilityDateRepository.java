package isa.FishingAdventure.repository;

import isa.FishingAdventure.model.AvailabilityDateRange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AvailabilityDateRepository extends JpaRepository<AvailabilityDateRange, Integer> {

    List<AvailabilityDateRange> getAllByServiceProfileId(Integer id);
}
