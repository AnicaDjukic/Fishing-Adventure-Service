package isa.FishingAdventure.service;

import isa.FishingAdventure.model.FishingAdventure;
import isa.FishingAdventure.model.Review;
import isa.FishingAdventure.model.ServiceProfile;
import isa.FishingAdventure.repository.ReviewRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.MessagingException;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ReviewServiceTest {

    @Mock
    private ReviewRepository reviewRepositoryMock;

    @Mock
    private ServiceProfileService profileServiceMock;

    @Mock
    private EmailService emailServiceMock;

    @InjectMocks
    private ReviewService reviewService;

    @Test
    public void getAllUnreviewedReviewsTest() {
        Review review1 = new Review();
        review1.setRejected(false);
        review1.setApproved(false);
        Review review2 = new Review();
        review2.setRejected(true);
        review2.setApproved(false);
        Review review3 = new Review();
        review3.setRejected(false);
        review3.setApproved(true);

        when(reviewRepositoryMock.findAll()).thenReturn(Arrays.asList(review1, review2, review3));

        int allReviewsCount = reviewService.findAll().size();

        assertThat(reviewService.getAllUnreviewedReviews()).hasSize(allReviewsCount - 2);
    }

    @Test
    public void rejectReviewTest() {
        Review review = new Review("", 4, 4, false, false);

        when(reviewRepositoryMock.findAll()).thenReturn(Arrays.asList(review));

        reviewService.rejectReview(review);

        assertThat(review.isRejected()).isTrue();

    }

    @Test
    public void getAllApprovedReviewsForService() {
        Review review1 = new Review("", 5, 4, false, false);
        Review review2 = new Review("", 5, 4, true, false);
        Review review3 = new Review("", 5, 2, false, false);

        when(reviewRepositoryMock.findAll()).thenReturn(Arrays.asList(review1, review2, review3));

        int allReviewsCount = reviewService.findAll().size();

        assertThat(reviewService.getAllApprovedReviewsForService(4)).hasSize(allReviewsCount - 2);
    }

    @Test
    public void approveReviewTest() throws MessagingException {
        Review review = new Review("", 4, 4, false, false);
        FishingAdventure adventure = new FishingAdventure();
        adventure.setName("Name");

        when(reviewRepositoryMock.findAll()).thenReturn(Arrays.asList(review));

        doNothing().when(profileServiceMock).recalculateServiceRating(review.getServiceId());

        when(profileServiceMock.getById(review.getServiceId())).thenReturn(adventure);
        when(emailServiceMock.createGenericEmail("Review posted", "A review has been posted for" +
                adventure.getName() + ".")).thenReturn("text");
        doNothing().when(emailServiceMock).sendEmail("", "New review posted for " + adventure.getName(), "text");

        reviewService.approveReview(review, "");

        assertThat(review.isApproved()).isTrue();

    }
}
