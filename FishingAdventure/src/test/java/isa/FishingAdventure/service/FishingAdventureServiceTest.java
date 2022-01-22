package isa.FishingAdventure.service;

import isa.FishingAdventure.model.FishingAdventure;
import isa.FishingAdventure.model.FishingInstructor;
import isa.FishingAdventure.repository.FishingAdventureRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@SpringBootTest
public class FishingAdventureServiceTest {

    @Mock
    private FishingAdventureRepository fishingAdventureRepositoryMock;

    @Mock
    private FishingAdventure fishingAdventureMock;

    @Mock
    private FishingInstructorService instructorServiceMock;

    @InjectMocks
    private FishingAdventureService fishingAdventureService;

    @Test
    public void testFindAll() {
        when(fishingAdventureRepositoryMock.findAll()).thenReturn(Arrays.asList(new FishingAdventure(), new FishingAdventure()));

        List<FishingAdventure> adventures = fishingAdventureService.findAll();

        assertThat(adventures).hasSize(2);

        verify(fishingAdventureRepositoryMock, times(1)).findAll();
        verifyNoMoreInteractions(fishingAdventureRepositoryMock);
    }

    @Test
    public void getByIdTest() {
        when(fishingAdventureRepositoryMock.getById(1)).thenReturn(fishingAdventureMock);

        FishingAdventure adventure = fishingAdventureService.getById(1);

        assertEquals(adventure, fishingAdventureMock);
        verify(fishingAdventureRepositoryMock, times(1)).getById(1);
        verifyNoMoreInteractions(fishingAdventureRepositoryMock);
    }

    @Test
    public void findFishingAdventuresByFishingInstructorTest() {
        FishingInstructor instructor = new FishingInstructor("john@gmail.com", "John", "Smith",
                "", "", null, null, null);
        FishingAdventure adventure = new FishingAdventure("Adventure", "", 0, 0,
                null, null, null, 0, instructor, null, null);
        adventure.setDeleted(false);
        when(fishingAdventureRepositoryMock.findByFishingInstructor(instructor)).thenReturn(Arrays.asList(adventure));

        List<FishingAdventure> adventures = fishingAdventureService.findByFishingInstructor(instructor);

        assertThat(adventures).hasSize(1);

        verify(fishingAdventureRepositoryMock, times(1)).findByFishingInstructor(instructor);
        verifyNoMoreInteractions(fishingAdventureRepositoryMock);
    }

}
