package isa.FishingAdventure.service;

import isa.FishingAdventure.model.Appointment;
import isa.FishingAdventure.model.Client;
import isa.FishingAdventure.model.Reservation;
import isa.FishingAdventure.repository.ReservationRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReservationServiceTest {

    @Mock
    private ReservationRepository reservationRepositoryMock;

    @InjectMocks
    private ReservationService reservationService;

    @Test
    public void testCancelReservation() throws ParseException {

        Appointment appointment = new Appointment(1, 0.0, true, new SimpleDateFormat("dd/MM/yyyy").parse("02/02/2022"), new SimpleDateFormat("dd/MM/yyyy").parse("04/02/2022"), Duration.ofDays(2), 2, 200, new HashSet<>(), false, false);
        Reservation reservation = new Reservation(1, appointment, new Client());

        when(reservationRepositoryMock.findAll()).thenReturn(Arrays.asList(reservation));

        boolean cancelledSuccess = reservationService.cancelReservation(1);

        assertThat(cancelledSuccess).isEqualTo(true);
        assertThat(reservation.getCanceled()).isEqualTo(true);
        assertThat(appointment.getCancelled()).isEqualTo(true);
    }

    @Test
    public void testIsReservationCancelled() throws ParseException {

        Appointment appointment = new Appointment(1, 0.0, true, new SimpleDateFormat("dd/MM/yyyy").parse("02/02/2022"), new SimpleDateFormat("dd/MM/yyyy").parse("04/02/2022"), Duration.ofDays(2), 2, 200, new HashSet<>(), false, true);
        Reservation reservation = new Reservation(1, appointment, new Client());
        reservation.setCanceled(true);

        when(reservationRepositoryMock.findAll()).thenReturn(Arrays.asList(reservation));

        boolean cancelled = reservationService.cancelReservation(1);

        assertThat(cancelled).isEqualTo(true);
        assertThat(reservation.getCanceled()).isEqualTo(true);
        assertThat(appointment.getCancelled()).isEqualTo(true);
    }
}
