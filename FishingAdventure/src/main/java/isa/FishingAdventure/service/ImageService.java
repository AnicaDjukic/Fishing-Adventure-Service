package isa.FishingAdventure.service;

import isa.FishingAdventure.repository.ImageRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageService {

	@Autowired
	private ImageRepository imageRepository;
}
