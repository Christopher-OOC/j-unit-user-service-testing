package main;

import java.util.UUID;

public class UserServiceImpl implements UserService {

	UserRepository userRepository;
	
	EmailVerificationService emailVerificationService;

	public UserServiceImpl() {
		super();
	}

	public UserServiceImpl(UserRepository userRepository, EmailVerificationService emailVerificationService) {
		super();
		this.userRepository = userRepository;
		this.emailVerificationService = emailVerificationService;
	}

	@Override
	public User createUser(String firstName, String lastName, String email, String password, String repeatPassword) {

		if (firstName == null || firstName.trim().length() == 0) {
			throw new IllegalArgumentException("User's first name is empty!");
		}

		if (lastName == null || lastName.trim().length() == 0) {
			throw new IllegalArgumentException("User's last name is empty!");
		}

		User user = new User(UUID.randomUUID().toString(), firstName, lastName, email, password, repeatPassword);

		boolean isUserCreated;
		
		try {
			
			isUserCreated = userRepository.save(user);
		}
		catch (RuntimeException ex) {
			throw new UserServiceException(ex.getMessage());
			
		}
		

		if (!isUserCreated) {
			throw new UserServiceException("Could not create user!");
		}
		
		try {
			
			emailVerificationService.scheduleEmailConfirmation(user);
		}
		catch (RuntimeException ex) {
			throw new UserServiceException(ex.getMessage());
		}
		
		return user;
	}

}
