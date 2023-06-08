package org.java.pizzeria.demo.auth.serv;

import java.util.List;
import java.util.Optional;

import org.java.pizzeria.demo.auth.pojo.User;
import org.java.pizzeria.demo.auth.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService{
	
	@Autowired
	private UserRepo userRepo;

	public List<User> findAll() {

		return userRepo.findAll();
	}
	public Optional<User> findById(int id) {

		return userRepo.findById(id);
	}
	public User save(User user) {

		return userRepo.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<User> adminOpt = userRepo.findByUsername(username);

		if (adminOpt.isEmpty())
			throw new UsernameNotFoundException("Username not found");

		return adminOpt.get();
	}

}
