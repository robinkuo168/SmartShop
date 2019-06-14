package tw.com.pershing.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tw.com.pershing.domain.Carousel;
import tw.com.pershing.repository.CarouselRepo;
import tw.com.pershing.service.CarouselService;

@RestController
@RequestMapping("/carousel")
public class CarouselController {
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	CarouselRepo carouselMananger;
	
	@Autowired
	CarouselService carouselService;

	@RequestMapping(value = "/findById")
	public List<Carousel> findCarouselById(@RequestParam(value = "id") String carouselId) {
		return carouselService.getCarouselListById(carouselId);
	}

	@RequestMapping(value = "/list")
	public List<Carousel> getCarouselList() {
		return carouselService.getCarouselList();
	}
}
