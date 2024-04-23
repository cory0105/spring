package dw.gameshop.service;

import dw.gameshop.dto.ReviewDto;
import dw.gameshop.model.Review;
import dw.gameshop.repository.ReviewRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ReviewService {
    ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public Review saveReview(Review review){
        review.setCreatedAt(LocalDateTime.now());
        return reviewRepository.save(review);
    }

    public List<Review> getReviewAll(){
        return reviewRepository.findAll();
    }

    public List<ReviewDto> getReviewAllByDto(){
        List<Review> reviewList = reviewRepository.findAll();
        List<ReviewDto> reviewDtoList = new ArrayList<>();
        for (int i=0; i<reviewList.size(); i++){
            ReviewDto reviewDto = new ReviewDto();
            reviewDto.setReviewPoint(reviewList.get(i).getPoint());
            reviewDto.setReviewText(reviewList.get(i).getReviewText());
            reviewDto.setGameId(reviewList.get(i).getGame().getId());
            reviewDto.setGameName(reviewList.get(i).getGame().getTitle());
            reviewDto.setUserId(reviewList.get(i).getUser().getUserId());
            reviewDtoList.add(reviewDto);
        }
        return reviewDtoList;
    }

    public List<ReviewDto> getReviewAllByReviewDto(){
        List<Review> reviewList = reviewRepository.findAll();
        List<ReviewDto> reviewDtoList = new ArrayList<>();
        for (int i=0; i<reviewList.size(); i++){
            ReviewDto reviewDto = new ReviewDto();
            reviewDtoList.add(reviewDto.toReviewDtoFromReview(reviewList.get(i)));
        }
        return reviewDtoList;
    }
}
