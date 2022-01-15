package isa.FishingAdventure.service;

import isa.FishingAdventure.repository.AddressRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    @Autowired
    private AddressRespository addressRespository;
}
