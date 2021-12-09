package isa.FishingAdventure.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;

import org.springframework.stereotype.Service;

import isa.FishingAdventure.dto.UserDto;
import isa.FishingAdventure.model.ConfirmationToken;
import isa.FishingAdventure.security.util.TokenUtils;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	private ConfirmationTokenService confirmationTokenService;
	
	@Autowired
	private TokenUtils tokenUtils;

	/*
	 * Koriscenje klase za ocitavanje vrednosti iz application.properties fajla
	 */
	@Autowired
	private Environment env;

	/*
	 * Anotacija za oznacavanje asinhronog zadatka
	 * Vise informacija na: https://docs.spring.io/spring/docs/current/spring-framework-reference/integration.html#scheduling
	 */
	@Async
	public void sendNotificaitionAsync(UserDto user) throws MailException, InterruptedException {
		System.out.println("Async metoda se izvrsava u drugom Threadu u odnosu na prihvaceni zahtev. Thread id: " + Thread.currentThread().getId());
		//Simulacija duze aktivnosti da bi se uocila razlika
		Thread.sleep(10000);
		System.out.println("Slanje emaila...");
		
		System.out.println(user.getEmail());
		System.out.println(user.getPassword());
		System.out.println(user.getName());
		System.out.println(user.getPhoneNumber());
		System.out.println(user.getSurname());
		//System.out.println(user.getAddress().getStreet());
		System.out.println(user.getUserType());
		
		ConfirmationToken confirmationToken = new ConfirmationToken();
		confirmationToken.setEmail(user.getEmail());
		confirmationToken.setToken(tokenUtils.generateToken(user.getEmail()));
		confirmationTokenService.save(confirmationToken);
		
		
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(user.getEmail());
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject("Primer slanja emaila pomoću asinhronog Spring taska");
		mail.setText("Hello " + user.getName() + ",\n\nto confirm your account, please click here : " + "http://localhost:8080/auth/confirm-account?token=" +confirmationToken.getToken());
		javaMailSender.send(mail);

		System.out.println("Email poslat!");
	}
}