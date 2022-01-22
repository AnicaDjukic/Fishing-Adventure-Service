package isa.FishingAdventure.service;

import isa.FishingAdventure.model.Image;
import isa.FishingAdventure.repository.ImageRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageService {

	@Autowired
	private ImageRepository imageRepository;

	public Image save(Image image) {
		return imageRepository.save(image);
	}
}
