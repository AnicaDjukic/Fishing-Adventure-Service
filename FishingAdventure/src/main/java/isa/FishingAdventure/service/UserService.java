package isa.FishingAdventure.service;

import isa.FishingAdventure.dto.UserPointsDto;
import isa.FishingAdventure.model.User;

import java.util.ArrayList;
import java.util.List;

import isa.FishingAdventure.model.UserCategory;
import isa.FishingAdventure.repository.UserRepository;
import isa.FishingAdventure.security.util.TokenUtils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository repository;

	@Autowired
	private EmailService emailService;

	@Autowired
	private UserCategoryService categoryService;

	@Autowired
	private TokenUtils tokenUtils;

	protected final Log loggerLog = LogFactory.getLog(getClass());

	public String getRoleIfActivated(String token) {
		String email = tokenUtils.getEmailFromToken(token);
		User user = findByEmail(email);
		String retVal = "";
		if (user.isActivated())
			retVal = user.getUserType().getName();
		return retVal;
	}

	public User findByToken(String token) {
		String email = tokenUtils.getEmailFromToken(token);
		return findByEmail(email);
	}

	public String getRoleFromToken(String token) {
		return tokenUtils.getRoleFromToken(token);
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = repository.findByEmail(email);
		if (user == null) {
			throw new UsernameNotFoundException(String.format("No user found with email '%s'.", email));
		} else {
			return user;
		}
	}

	public String getFullNameByEmail(String email) {
		User user = findByEmail(email);
		return user.getName() + " " + user.getSurname();
	}

	public User findByEmail(String email) throws UsernameNotFoundException {
		return repository.findByEmail(email);
	}

	public List<User> findAllUnactivatedAdvertisers() {
		List<User> users = new ArrayList<>();
		for (User user : findAllNotDeleted()) {
			if (!user.isActivated()
					&& !user.getUserType().getName().equals("ROLE_CLIENT")
					&& !user.getUserType().getName().equals("ROLE_ADMIN")) {
				users.add(user);
			}
		}
		return users;
	}

	public UserPointsDto getUserPointsInfo(User user) {
		UserCategory category = findByEmail(user.getEmail()).getCategory();
		return new UserPointsDto(category.getName(), user.getPoints(), category.getPercentage());
	}

	public User findById(Integer id) throws AccessDeniedException {
		return repository.getById(id);
	}

	public List<User> findAllNotDeleted() throws AccessDeniedException {
		List<User> users = new ArrayList<>();
		for (User user : repository.findAll()) {
			if (user.getDeleted().equals(false)) {
				users.add(user);
			}
		}
		return users;
	}

	public void save(User user) {
		repository.save(user);
	}

	public void delete(String email) {
		User user = findByEmail(email);
		user.setActivated(false);
		user.setDeleted(true);
		save(user);
	}

	public UserCategory getUserCategory(String email) {
		return findByEmail(email).getCategory();
	}

	public Boolean isEmailRegistered(String email) {
		return findByEmail(email) != null;
	}

	public void rejectRegistrationRequest(String email, String reason) {
		User user = findByEmail(email);
		user.setDeleted(true);
		save(user);
		sendRegistrationRequestEmail(email, "Reservation rejected", reason);
	}

	public void approveRegistrationRequest(String email) {
		User user = findByEmail(email);
		user.setActivated(true);
		user.setBiography("");
		user.setCategory(categoryService.getUserCategoryByName("REGULAR_ADVERTISER"));
		save(user);
		sendRegistrationRequestEmail(email, "Reservation approved", "");
	}

	private void sendRegistrationRequestEmail(String email, String subject, String reason) {
		String emailText;
		if (!reason.equals("")) {
			emailText = emailService.createGenericEmail("Registration request rejected",
					"Unfortunately your registration was not approved due to the following reason: \n"
							+ reason);
		} else {
			emailText = emailService.createGenericEmail("WELCOME!",
					"We're excited to start working with you! Your account has been activated, press the button below so you&nbsp;can start setting up your profile.");
		}
		try {
			emailService.sendEmail(email, subject, emailText);
		} catch (Exception e) {
			loggerLog.debug("Email could not be sent.");
		}
	}
}
