package isa.FishingAdventure.repository;

import isa.FishingAdventure.model.Appointment;
import isa.FishingAdventure.model.ServiceProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceProfileRepository extends JpaRepository<ServiceProfile, Integer> {
    ServiceProfile getByName(String name);

    ServiceProfile getByAppointmentsIsContaining(Appointment appointment);
}
