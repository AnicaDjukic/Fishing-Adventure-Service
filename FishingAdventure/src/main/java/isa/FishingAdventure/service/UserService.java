package isa.FishingAdventure.service;

import isa.FishingAdventure.model.User;

import java.util.List;

import isa.FishingAdventure.repository.UserRepository;
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
	private UserTypeService userTypeService;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = repository.findByEmail(email);
		if (user == null) {
			throw new UsernameNotFoundException(String.format("No user found with email '%s'.", email));
		} else {
			return user;
		}
	}

	public User findByEmail(String email) throws UsernameNotFoundException {
		return repository.findByEmail(email);
	}

	public User findById(Integer id) throws AccessDeniedException {
		return repository.findById(id).orElseGet(null);
	}

	public List<User> findAll() throws AccessDeniedException {
		return repository.findAll();
	}

	public void save(User user) {
		repository.save(user);
	}

	public Boolean isEmailRegistered(String email) {
		return findByEmail(email) != null;
	}
}
