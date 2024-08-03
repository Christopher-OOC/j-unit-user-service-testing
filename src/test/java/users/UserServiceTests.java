package users;

import org.junit.jupiter.api.Test;

import main.UserService;
import main.UserServiceImpl;

public class UserServiceTests {
	
	@Test
	void testCreateUser_whenUserDetailsProvided_returnsUserObject() {
		// Arrange
		UserService userService = new UserServiceImpl();
		String firstName = "Christopher";
		String lastName = "Olojede";
		String email = "test@test.com";
		String password = "12345678";
		String repeatPassword = "12345678";
		
		//Act
		userService.createUser(firstName, lastName, email, password, repeatPassword);
		
		//Assert 
	}

}
