package tw.com.pershing.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.com.pershing.domain.Carousel;
import tw.com.pershing.domain.Position;
import tw.com.pershing.repository.CarouselRepo;

@Service
public class CarouselService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	CarouselRepo repository;
	
	public List<Carousel> getCarouselList() {
		return repository.getCarousel();
	}
	
	public List<Carousel> getCarouselListById(String guideId) {
		return repository.findCarouselById(Integer.valueOf(guideId));
	}
	
	public boolean carouselIdExist(final Integer carouselId) {
        return repository.findCarouselById(carouselId).size() > 0;
    }
	
	public Carousel updateCarousel(final Carousel carousel) {
        final Carousel temp = new Carousel();
        
        temp.setPhotoName(carousel.getPhotoName());
        temp.setGoogleDrive(carousel.getGoogleDrive());
        temp.setSeq(carousel.getSeq());
        
        if (carouselIdExist(carousel.getCarouselId())) {
        	temp.setCarouselId(carousel.getCarouselId());
        }
        
        final Carousel returnCarousel = repository.saveCarousel(temp);
        return returnCarousel;
    }
	
	public Carousel deleteCarousel(final Carousel carousel) {
        final Carousel temp = new Carousel();
        
        temp.setGoogleDrive(carousel.getGoogleDrive());
        
        if (carouselIdExist(carousel.getCarouselId())) {
        	temp.setCarouselId(carousel.getCarouselId());
        }
        
        final Carousel returnCarousel = repository.deleteCarousel(temp);
        return returnCarousel;
    }
}
