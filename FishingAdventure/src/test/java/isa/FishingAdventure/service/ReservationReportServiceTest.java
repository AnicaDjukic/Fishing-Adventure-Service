package isa.FishingAdventure.service;


import isa.FishingAdventure.model.ReservationReport;
import isa.FishingAdventure.repository.ReservationReportRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ReservationReportServiceTest {

    @Mock
    private ReservationReportRepository reservationReportRepositoryMock;

    @Mock
    private ReservationReport reservationReportMock;

    @InjectMocks
    private ReservationReportService reservationReportService;

    @Test
    public void findByIdTest() {
        when(reservationReportRepositoryMock.getById(1)).thenReturn(reservationReportMock);
        ReservationReport report = reservationReportService.findById(1);

        assertEquals(report, reservationReportMock);
    }

    @Test
    public void getAllReportsAwaitingReview() {
        ReservationReport report1 = new ReservationReport();
        report1.setWaitingReview(false);
        ReservationReport report2 = new ReservationReport();
        report2.setWaitingReview(true);

        when(reservationReportRepositoryMock.findAll()).thenReturn(Arrays.asList(report1, report2));

        int AllReportsCount = reservationReportService.findAll().size();

        assertThat(reservationReportService.getAllReportsAwaitingReview()).hasSize(AllReportsCount - 1);

    }



}
