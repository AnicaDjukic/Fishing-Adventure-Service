package isa.FishingAdventure;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class FishingAdventureApplication {
	public static void main(String[] args) {
		SpringApplication.run(FishingAdventureApplication.class, args);
	}
}
