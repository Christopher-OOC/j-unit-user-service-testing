package main;

import java.util.UUID;

public class UserServiceImpl implements UserService {

	@Override
	public User createUser(String firstName, String lastName, String email, String password, String repeatPassword) {
		
		if (firstName == null || firstName.trim().length() == 0) {
			throw new IllegalArgumentException("User's first name is empty!");
		}
		
		return new User(UUID.randomUUID().toString(), firstName, lastName, email, password, repeatPassword);
	}

}
