package users;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import main.User;
import main.UserService;
import main.UserServiceImpl;

public class UserServiceTests {
	
	UserService userService;
	String firstName;
	String lastName;
	String email;
	String password;
	String repeatPassword;
	
	@BeforeEach
	void init() {
		
		userService = new UserServiceImpl();
		firstName = "Christopher";
		lastName = "Olojede";
		email = "test@test.com";
		password = "12345678";
		repeatPassword = "12345678";
		
	}
	
	@Test
	void testCreateUser_whenUserDetailsProvided_returnsUserObject() {
		
		//Act
		User user = userService.createUser(firstName, lastName, email, password, repeatPassword);
		
		//Assert 
		assertNotNull(user, () -> "The createUser should not have returned null");
		assertEquals(firstName, user.getFirstName(), () -> "First Name not the same");
		assertEquals(lastName, user.getLastName(), () -> "Last Name not the same");
		assertEquals(email, user.getEmail(), () -> "Email not the same");
		assertNotNull(user.getId());
	}
	
	@DisplayName("Empty first name causes correct exception")
	@Test
	void testCreateUser_whenFirstNameIsEmpty_throwsIllegalArgmentException() {
		firstName = "";
		String expectedExceptionMessage = "User's first name is empty!";
	
		//Act $ Assert
		 IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
			 userService.createUser(firstName, lastName, email, password, repeatPassword);
		 }, () -> "Empty first name should have caused an Illegal Argument Exception");
	
		 assertEquals(expectedExceptionMessage, thrown.getMessage());
	
	}

}
