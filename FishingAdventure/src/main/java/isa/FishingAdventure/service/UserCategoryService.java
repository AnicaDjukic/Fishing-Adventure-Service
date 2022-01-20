package isa.FishingAdventure.service;

import isa.FishingAdventure.model.Reservation;
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
	private UserService userService;

	public List<UserCategory> findAll() {
		return categoryRepository.findAll();
	}

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
		userService.save(user);
	}

}
