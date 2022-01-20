package isa.FishingAdventure.service;

import isa.FishingAdventure.dto.LoyaltyProgramDto;
import isa.FishingAdventure.model.Reservation;
import isa.FishingAdventure.model.ReservationPoints;
import isa.FishingAdventure.model.User;
import isa.FishingAdventure.model.UserCategory;
import isa.FishingAdventure.repository.UserCategoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
public class UserCategoryService {
	
	@Autowired
	private UserCategoryRepository categoryRepository;

	@Autowired
	private ReservationPointsService pointsService;

	@Autowired
	private UserService userService;

	public List<UserCategory> findAll() {
		return categoryRepository.findAll();
	}

	public void save(UserCategory category) { categoryRepository.save(category); }

	public double calculateAmountEarned(String advertiserEmail, Reservation reservation) {
		double originalPrice = reservation.getAppointment().getPrice();
		double percentage = userService.getUserCategory(advertiserEmail).getPercentage()
				+ userService.getUserCategory(reservation.getClient().getEmail()).getPercentage();
		return originalPrice - (originalPrice * percentage / 100);
	}

	public void checkUserCategories(User client, User advertiser) {
		checkUserPointsAndCategory(client, "client");
		checkUserPointsAndCategory(advertiser, "advertiser");
	}

	private void checkUserPointsAndCategory(User user, String role) {
		for (UserCategory category : findAll()) {
			if (category.getName().toLowerCase().contains(role)
					&& category.getPoints() < user.getPoints())
				user.setCategory(category);
		}
		System.out.println("OVDEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
		System.out.println(user.getPoints());
		userService.save(user);
	}

	public LoyaltyProgramDto getLoyaltyProgramInfo() {
		LoyaltyProgramDto dto = new LoyaltyProgramDto(findAll());
		ReservationPoints rp = pointsService.getReservationPoints();
		dto.setAdvertiserPointsPercentage(rp.getAdvertiserPointsPercentage());
		dto.setClientPointsPercentage(rp.getClientPointsPercentage());

		return dto;
	}

	public UserCategory getUserCategoryByName(String name) {
		UserCategory userCategory = new UserCategory();
		for (UserCategory category : findAll()) {
			if (category.getName().equals(name))
				userCategory = category;
		}
		return userCategory;
	}

	public void updateCategoryPoints(String name, double points) {
		UserCategory category = getUserCategoryByName(name);
		category.setPoints(points);
		save(category);
	}

	public void updateAdvertiserCuts(double percentage) {
		UserCategory regular = getUserCategoryByName("REGULAR_ADVERTISER");
		regular.setPercentage(percentage);
		UserCategory silver = getUserCategoryByName("SILVER_ADVERTISER");
		silver.setPercentage(percentage - 2);
		UserCategory gold = getUserCategoryByName("GOLD_ADVERTISER");
		gold.setPercentage(percentage - 4);
		save(regular);
		save(silver);
		save(gold);
	}

	public void updateReservationPointPercentages(double clientPoints, double advertiserPoints) {
		pointsService.updateReservationPointPercentages(clientPoints, advertiserPoints);
	}

}
