package isa.FishingAdventure.service;

import isa.FishingAdventure.model.*;
import isa.FishingAdventure.repository.ClientRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.util.HashSet;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClientServiceTest {

    @Mock
    private ClientRepository clientRepositoryMock;

    @Mock
    private ServiceProfileService serviceProfileServiceMock;

    @InjectMocks
    private ClientService clientService;

    @Test
    public void testSubscribe() {

        ServiceProfile serviceProfile = new VacationHome();
        serviceProfile.setId(1);
        Client client = new Client();
        client.setUserId(1);
        client.setSubscriptions(new HashSet<>());

        when(serviceProfileServiceMock.getById(1)).thenReturn(serviceProfile);

        boolean subscribed = clientService.subscribe(client, 1);

        assertThat(subscribed).isEqualTo(true);
        assertThat(client.getSubscriptions().size()).isEqualTo(1);
    }
}
