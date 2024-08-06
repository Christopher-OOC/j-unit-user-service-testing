package main;

public interface EmailVerificationService {
	
	void scheduleEmailConfirmation(User user);

}
