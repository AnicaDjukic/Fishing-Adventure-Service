package isa.FishingAdventure.service;

import isa.FishingAdventure.model.User;
import isa.FishingAdventure.repository.ClientRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private ClientRepository repository;

	@Autowired
	private UserTypeService userTypeService;

	// Funkcija koja na osnovu username-a iz baze vraca objekat User-a
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
}
