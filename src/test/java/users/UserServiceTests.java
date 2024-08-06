package users;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import main.User;
import main.UserRepository;
import main.UserServiceException;
import main.UserServiceImpl;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {
	
	@InjectMocks
	UserServiceImpl userService;
	
	@Mock
	UserRepository userRepository;
	
	String firstName;
	String lastName;
	String email;
	String password;
	String repeatPassword;
	
	@BeforeEach
	void init() {
		
		firstName = "Christopher";
		lastName = "Olojede";
		email = "test@test.com";
		password = "12345678";
		repeatPassword = "12345678";
		
	}
	
	@Test
	void testCreateUser_whenUserDetailsProvided_returnsUserObject() {
		// Arrange
		when(userRepository.save(any(User.class))).thenReturn(true);
		
		//Act
		User user = userService.createUser(firstName, lastName, email, password, repeatPassword);
		
		//Assert 
		assertNotNull(user, () -> "The createUser should not have returned null");
		assertEquals(firstName, user.getFirstName(), () -> "First Name not the same");
		assertEquals(lastName, user.getLastName(), () -> "Last Name not the same");
		assertEquals(email, user.getEmail(), () -> "Email not the same");
		assertNotNull(user.getId());
		verify(userRepository, times(1)).save(any(User.class));
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
	
	
	@Test
	void testCreateUser_whenSaveMethodThrowsException_thenThrowsUserServiceException() {
		//Arrange
		when(userRepository.save(any(User.class))).thenThrow(RuntimeException.class);
		//Act
		
		assertThrows(UserServiceException.class, () -> {
			userService.createUser(firstName, lastName, email, password, repeatPassword);
		}, () -> "Should have thrown UserServiceException instead");
		
		//Assert
	}

}
