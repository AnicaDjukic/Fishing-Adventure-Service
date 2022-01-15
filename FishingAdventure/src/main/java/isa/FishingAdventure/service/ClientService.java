package isa.FishingAdventure.service;

import isa.FishingAdventure.dto.UserDto;
import isa.FishingAdventure.model.Client;
import isa.FishingAdventure.model.ServiceProfile;
import isa.FishingAdventure.model.User;
import isa.FishingAdventure.model.UserType;
import isa.FishingAdventure.repository.ClientRepository;
import isa.FishingAdventure.security.util.TokenUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserTypeService userTypeService;

    @Autowired
    private ServiceProfileService serviceProfileService;

    public Client findByEmail(String email) {
        return (Client) clientRepository.findByEmail(email);
    }

    public Client save(UserDto userDto) throws InterruptedException {
        Client u = new Client();
        u.setEmail(userDto.getEmail());

        // pre nego sto postavimo lozinku u atribut hesiramo je kako bi se u bazi
        // nalazila hesirana lozinka
        // treba voditi racuna da se koristi isi password encoder bean koji je
        // postavljen u AUthenticationManager-u kako bi koristili isti algoritam
        u.setPassword(passwordEncoder.encode(userDto.getPassword()));

        u.setName(userDto.getName());
        u.setSurname(userDto.getSurname());
        u.setPhoneNumber(userDto.getPhoneNumber());
        u.setEmail(userDto.getEmail());
        u.setAddress(userDto.getAddress());
        u.setBiography(userDto.getBiography());

        // u primeru se registruju samo obicni korisnici i u skladu sa tim im se i
        // dodeljuje samo rola USER
        List<UserType> roles = userTypeService.findByName("ROLE_CLIENT");
        u.setUserType(roles.get(0));
        u.setPoints(0.0);
        u.setPenalties(0);
        u.setDeleted(false);

        return this.clientRepository.save(u);
    }

    public Client save(Client client) {
        return clientRepository.save(client);
    }

    public void saveNewClient(Client client) {
        client.setPassword(passwordEncoder.encode(client.getPassword()));
        List<UserType> roles = userTypeService.findByName("ROLE_CLIENT");
        client.setUserType(roles.get(0));
        clientRepository.save(client);
    }

    public void addPenaltyToClient(Client client) {
        client.setPenalties(client.getPenalties() + 1);
        save(client);
    }

    public boolean subscribe(Client client, Integer id) {
        ServiceProfile serviceProfile = serviceProfileService.getById(id);
        if (isSubscribed(client, id))
            return false;
        client.getSubscriptions().add(serviceProfile);
        clientRepository.save(client);
        return true;
    }

    public boolean isSubscribed(Client client, Integer id) {
        for (ServiceProfile s : client.getSubscriptions()) {
            if (s.getId().equals(id))
                return true;
        }
        return false;
    }

    public boolean unsubscribe(Client client, Integer id) {
        if (!isSubscribed(client, id))
            return true;
        for (ServiceProfile s : client.getSubscriptions()) {
            if (s.getId().equals(id)) {
                client.getSubscriptions().remove(s);
                clientRepository.save(client);
                return true;
            }
        }
        return false;
    }

    public List<User> getClientSubscribedToServiceProfile(Integer serviceProfileId) {
        List<User> clients = new ArrayList<>();
        for (Integer clientId : clientRepository.findClientIdBySubscription(serviceProfileId)) {
            clients.add(clientRepository.getById(clientId));
        }
        return clients;
    }

    public List<ServiceProfile> getClientSubscriptions(Client client) {
        return new ArrayList<>(client.getSubscriptions());
    }

    public Client findByToken(String token) {
        String email = tokenUtils.getEmailFromToken(token);
        return findByEmail(email);
    }
}
