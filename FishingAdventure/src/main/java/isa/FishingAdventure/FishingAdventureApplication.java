package isa.FishingAdventure;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableAsync;

import isa.FishingAdventure.model.FileStorage;

@SpringBootApplication
@EnableAsync
@EnableConfigurationProperties({
		FileStorage.class
})
public class FishingAdventureApplication {
	public static void main(String[] args) {
		SpringApplication.run(FishingAdventureApplication.class, args);
	}
}
