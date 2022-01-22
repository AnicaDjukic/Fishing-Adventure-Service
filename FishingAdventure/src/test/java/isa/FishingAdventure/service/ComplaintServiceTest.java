package isa.FishingAdventure.service;

import isa.FishingAdventure.model.Complaint;
import isa.FishingAdventure.repository.CompliantRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ComplaintServiceTest {

    @Mock
    private CompliantRepository compliantRepositoryMock;

    @InjectMocks
    private ComplaintService complaintService;

    @Test
    public void getUnansweredComplaintsTest() {
        Complaint complaint1 = new Complaint();
        complaint1.setReplySent(false);
        Complaint complaint2 = new Complaint();
        complaint2.setReplySent(true);
        Complaint complaint3 = new Complaint();
        complaint3.setReplySent(true);

        when(compliantRepositoryMock.findAll()).thenReturn(Arrays.asList(complaint1, complaint2, complaint3));

        int allComplaintsCount = complaintService.findAll().size();

        assertThat(complaintService.getUnansweredComplaints()).hasSize(allComplaintsCount - 2);

    }
}
