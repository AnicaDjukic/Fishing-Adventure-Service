package isa.FishingAdventure.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import isa.FishingAdventure.model.UserType;

@Repository
public interface UserTypeRepository extends JpaRepository<UserType, Integer> {

	List<UserType> findByName(String name);

}
