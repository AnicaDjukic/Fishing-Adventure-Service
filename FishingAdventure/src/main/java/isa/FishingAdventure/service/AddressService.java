package isa.FishingAdventure.service;

import isa.FishingAdventure.model.Address;
import isa.FishingAdventure.repository.AddressRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    @Autowired
    private AddressRespository addressRespository;


    public Address save(Address original, Address updated) {
        original.setCity(updated.getCity());
        original.setCountry(updated.getCountry());
        original.setZipCode(updated.getZipCode());
        original.setStreet(updated.getStreet());
        return addressRespository.save(original);
    }
}
