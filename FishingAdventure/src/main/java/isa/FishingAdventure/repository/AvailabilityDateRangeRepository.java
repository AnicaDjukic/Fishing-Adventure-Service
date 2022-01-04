package isa.FishingAdventure.repository;

import isa.FishingAdventure.model.AvailabilityDateRange;
import isa.FishingAdventure.model.ServiceProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AvailabilityDateRangeRepository extends JpaRepository<AvailabilityDateRange, Integer> {

    List<AvailabilityDateRange> findByServiceProfile(ServiceProfile serviceProfile);
}
