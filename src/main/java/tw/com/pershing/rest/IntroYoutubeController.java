package tw.com.pershing.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tw.com.pershing.domain.IntroYoutube;
import tw.com.pershing.repository.IntroYoutubeRepo;
import tw.com.pershing.service.IntroYoutubeService;

@RestController
@RequestMapping("/introYoutube")
public class IntroYoutubeController {
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	IntroYoutubeRepo introMananger;
	
	@Autowired
	IntroYoutubeService introService;
	

	@RequestMapping(value = "/findById")
	public List<IntroYoutube> findIntroYoutubeById(@RequestParam(value = "id") String introId) {
		return introService.getIntroYoutubeListById(introId);
	}

	@RequestMapping(value = "/list")
	public List<IntroYoutube> getIntroYoutubeList() {
		return introService.getIntroYoutubeList();
	}
}
