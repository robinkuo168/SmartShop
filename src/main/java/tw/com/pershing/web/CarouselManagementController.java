package tw.com.pershing.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import tw.com.pershing.domain.Carousel;
import tw.com.pershing.service.CarouselService;

@Controller
public class CarouselManagementController {
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	CarouselService carouselService;

    @GetMapping("/carousel")
    public String carouselPage(Model model) {
    	model.addAttribute("carouselList", carouselService.getCarouselList());
    	model.addAttribute("user", "guest");
    	model.addAttribute("title", "首頁輪播");
    	model.addAttribute("pageTitle", "首頁輪播");
    	model.addAttribute("footer", "商業發展研究院");
    	return "carousel";
    }
    
    @PostMapping("/carousel/update")
    @ResponseBody
    public Carousel updateCarousel(@RequestBody Carousel carousel) {
    	LOGGER.debug("post update");
    	Carousel result = carouselService.updateCarousel(carousel);
        return result;
    }
    
    @PostMapping("/carousel/delete")
    @ResponseBody
    public Carousel deleteCarousel(@RequestBody Carousel carousel) {
    	LOGGER.debug("post delete");
    	Carousel result = carouselService.deleteCarousel(carousel);
        return result;
    }
}
